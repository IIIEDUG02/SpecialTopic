package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.ddns.iiiedug02.helpers.CommentHelper;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.Comment;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.CommentService;
import net.ddns.iiiedug02.model.service.MemberService;

/**
 * @author Anna
 * 
 * 留言功能是以 REST API 的方式來實現，在前端有需要時(lazy load)才發請求來要資料，
 * 同時也為了與課程頁面的 controller 邏輯切開，以方便獨立實作。
 * 
 * 目前身份驗證都還是以 Principal 物件來做檢查，之後可改成以 JWT 來做驗證。
 * 若之後開的 API 比較多可以使用 OpenAPI(Swagger) 來產生文件。
 *
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
  @Autowired
  private CommentService commentService;
  
  @Autowired
  private ClassBeanService classBeanService;
  
  @Autowired
  private MemberService memberService;
  
  
  private final List<String> TYPE = Arrays.asList("course");
  private CommentHelper commentHelper = CommentHelper.getInstance();
  
  
  /**
   * 根據課程編號與留言種類來取得該課程的所有留言。
   * 
   * 使用 Time-based paging 方式來實作分頁，原因是傳統的 Offset-based paging ("numbered pages")
   * 在呼叫 API 期間時，若有其他操作進來(create, delete)會造成回傳結果被改變。
   * 
   * 詳見文章: https://www.mixmax.com/engineering/api-paging-built-the-right-way 
   * 
   * URL example:
   * GET: domain:port/comment/comments?cbt=1649439013&cid=2&type=course&limit=10
   * 
   * @param cbt: Created Before Timestamp，以該時間點做分頁的條件
   * @param cid: 該課程的編號
   * @param type: 留言的種類
   * @param limit: 每頁的資料筆數，由前端傳進來
   * @return 符合上述條件的所有留言的 JSON
   */
  @GetMapping("/comments")
  public ResponseEntity<Object> retrieveCommentsByCidAndType(@RequestParam Long cbt, @RequestParam int cid, 
      @RequestParam String type, @RequestParam int limit, Principal p) {
    Page<Comment> pages = commentService.getCommentsByCidAndCommentType(cbt, cid, type, limit);
    List<Comment> comments = pages.getContent();
    List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
    Member member = null;
    
    if (p != null) {
      member = memberService.findByUsername(p.getName());
    }
    
    // 因可能查出來結果為零筆資料
    if (comments.size() != 0)
      data = commentHelper.getResponseBody(comments, member);
    
    if (member != null) {
      Map<String, Object> result = new LinkedHashMap<String, Object>();
      
      // 放入登入者的基本資訊
      result.put("username", member.getMemberInformation().getFullname());
      result.put("avatar", member.getMemberInformation().getPhoto());
      result.put("result", data);
      result.put("hasNext", (pages.getTotalPages() == pages.getNumber() + 1) ? 0 : 1);
      
      // 找出父留言中，時間最早的那一筆留言，來當作下次查詢的一個時間點依據
      result.put("cbt", comments.get(comments.size() - 1).getTimestamp());
      
      return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
    
    return new ResponseEntity<Object>(data, HttpStatus.OK);
  }
  
  
  /**
   * 新增留言，以 AJAX 請求方式發資料過來，新增成功後會將該留言返回給前端。
   * 流程上會做一些資料驗證，如果資料無效則會主動拋出 throw new NoSuchElementException 異常
   * 會由相對應的 handler 來做處理。
   * 
   * @param comment: POST 請求的 payload，會直接反序列化進來轉換成 Comment 物件
   * @param cid: 課程編號，表示在哪個課程頁面上留言
   * @param p: Principal 物件，之後可改成 JWT 做驗證
   * @return 新增成功的 Comment 會被回傳
   */
  @PostMapping("/create/{cid}")
  public ResponseEntity<Object> createComment(@Valid @RequestBody Comment comment, @PathVariable int cid, Principal p) {
    if (!TYPE.contains(comment.getType()))
      throw new NoSuchElementException("留言種類錯誤！");
    
    ClassBean classBean = classBeanService.findById(cid);
    if (classBean == null)
      throw new NoSuchElementException("找不到此課程！");
    else
      comment.setClassBean(classBean);
    
    // 如果不為 null，表示該則留言是某個留言的子留言，否則表示是一個父留言
    if (comment.getUuid() != null) {
      Comment parentComment = commentService.getCommentByUuid(comment.getUuid());
      
      // 設定此留言的父留言
      if (parentComment != null)
        comment.setParentComment(parentComment);
      else
        throw new NoSuchElementException("找不到目標留言！");
    }
    
    // 不管是不是子留言都要更新 UUID，不使用任何 hash 直接取前 24 個字
    comment.setUuid(UUID.randomUUID().toString().replace("-", "").substring(0, 24));
    comment.setMember(memberService.findByUsername(p.getName()));
    comment.setTimestamp(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    
    Comment c = commentService.create(comment);
    Map<String, Object> body = new LinkedHashMap<>();
    
    body.put("status", HttpStatus.CREATED.value());
    body.put("result", commentHelper.getResponseBody(c, classBean));
    
    return new ResponseEntity<Object>(body, HttpStatus.CREATED);
  }
  
  /**
   * 更新留言，使用局部更新，因為只會要更新留言的內容，其他東西都不會動，因此寫了 SQL 語句來更新，
   * 否則一次更新留言物件所有內容成本太高。
   * 
   * @param fields: 前端傳過來的 JSON(裡面只有 content 一個欄位)
   * @param uuid: 要更新的留言 uuid
   * @param p: Principal Object
   * @return 簡易的 response({status: 200, result: "OK"})
   */
  @PatchMapping("/{uuid}")
  public ResponseEntity<Object> updateComment(@RequestBody Map<String, String> fields, @PathVariable String uuid, Principal p) {
    
    if (p == null)
      throw new AccessDeniedException("您無此操作權限！");
    
    if (fields.size() == 0)
      return commentHelper.generateResponse(HttpStatus.BAD_REQUEST.value(), "資料錯誤！");
    
    String content = fields.get("content");
    
    if (content == null || content.trim().length() == 0)
      return commentHelper.generateResponse(HttpStatus.BAD_REQUEST.value(), "留言內容不可空白！");
    
    Member loggedInUser = memberService.findByUsername(p.getName());
    Comment comment = commentService.getCommentByUuid(uuid);
    
    if (comment == null)
      throw new NoSuchElementException("找不到目標留言！");
    
    Member member = comment.getMember();
    
    if (loggedInUser.getUid() != member.getUid())
      throw new AccessDeniedException("您無此操作權限！");
    
    // 更新留言
    commentService.updateContentById(content, comment.getId());
    
    Map<String, Object> body = new LinkedHashMap<>();
    
    body.put("status", HttpStatus.OK.value());
    body.put("result", "OK");
    
    return new ResponseEntity<Object>(body, HttpStatus.OK);
  }
 
  /**
   * 某些屬性不可為空，若為空則會引發 MethodArgumentNotValidException 異常
   * 並由此 method 補捉到後做處理，主要會回傳一些錯誤訊息與 HTTP 狀態碼(status code)
   * 
   * 回傳格式:
   *  {
   *    "status": 400,
   *    "message": [
   *      "錯誤訊息1"
   *      "錯誤訊息2"...
   *    ]
   *  }
   *  
   * @param e 引發異常的 Exception 物件會自動被傳入
   * @return 錯誤訊息與狀態碼
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    // 收集最主要的錯誤訊息(default message)，否則原始錯誤訊息太長(因為包涵 tracking)
    List<String> messages = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(x -> x.getDefaultMessage())
        .collect(Collectors.toList());
    
    return commentHelper.generateResponse(HttpStatus.BAD_REQUEST.value(), messages);
  }
  
  /**
   * 上面的 method 會主動拋出 NoSuchElementException 由此 method 來做處理
   * 回傳給前端訊息
   * 
   * @param e: 自動會被傳入的 NoSuchElementException 物件
   * @return 錯誤訊息與狀態碼
   */
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> NoSuchElementExceptionHandler(NoSuchElementException e) {
    return commentHelper.generateResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
  }
  
  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseEntity<Object> NoSuchElementExceptionHandler(AccessDeniedException e) {
    return commentHelper.generateResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
  }
}
