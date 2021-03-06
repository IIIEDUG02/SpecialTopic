package net.ddns.iiiedug02.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.Comment;
import net.ddns.iiiedug02.model.bean.Like;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.service.LikeService;

/**
 * @author Anna
 * 
 * CommentController 的輔助類別，目前先求功能正常，之後要再做重構與修正。
 *
 */
/**
 * @author USER
 *
 */
@Component
public class CommentHelper {
  @Autowired
  private LikeService likeService;
  
  // 前端顯示的時間會做格式化
  private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


  /**
   * 判斷留言的人是不是授課老師本人
   * 
   * @param member: 留言的使用者
   * @param classBean: 為了要取出被關聯的使用者，所以 classBean 要傳進來
   * @return int: 1 表示是本人，反之則為 0
   */
  public int isisInstructor(Member member, ClassBean classBean) {
    return member.getUid() == classBean.getUid() ? 1 : 0;
  }
  
  public int isOwner(Member member, Member currentMember) {
    if (currentMember == null)
        return 0;
    
    return member.getUid() == currentMember.getUid() ? 1 : 0;
  }
  
  
  /**
   * 判斷當下登入的使用者是否對這則留言點過讚，若點過則顯示紅愛心。
   * 
   * @param comment: Comment Object
   * @param member: logged in user
   * @return int: 0 or 1
   */
  public int liked(Comment comment, Member member) {
    if (member == null)
      return 0;
    
    List<Like> list = likeService.findByCommentIdAndMembersUid(comment.getId(), member.getUid());
    
    if (list.size() == 0)
      return 0;
    
    return list.get(0).getLiked();
  }
  
  /**
   * 主要是將回傳给前端的 JSON 做處理，不回傳過多或敏感的資訊
   * 目前主要問題在於關聯到的 Member 物件會再關聯到 MemberInfomation 造成回傳過多的訊息
   * 因此在這理做處理
   * 
   * 目前回傳的資料為這些，之後可再修改:
   * {
       "title": "留言標題",
       "content": "留言內容",
       "uuid": "eece46e481d040d5b96062a ",
       "postTime": "2022/04/04 04:26:27",
       "editTime": "2022/04/04 04:26:27",
       "member": {
         "username": "最高權限",
         "avatar": null
       }
     }
   * 
   * @param comment: 要回傳給前端的單一 Comment 物件
   * @param classBean: 因要判斷授課老師，所以要將 member 從 classBean 中取出來
   * @return Map Object: 回傳給前端會自動轉成 JSON
   */
  @SuppressWarnings("unchecked")
  public Map<String, Object> getResponseBody(Comment comment, ClassBean classBean) {
    // 用來將 Comment 轉成 Map 的物件
    ObjectMapper objectMapper = new ObjectMapper();
    
    // 用來存放被處理過後的 member 資料
    Map<String, Object> member = new LinkedHashMap<>();
    
    // Java 8 date/time type java.time.ZonedDateTime not supported by default
    // 因此需要加入這句才不會出現錯誤
    objectMapper.registerModule(new JavaTimeModule());
    
    // 將 Comment 轉成 Map 後會再添加 member 的資料，最後整個就是回傳給前端的資料
    Map<String, Object> body = objectMapper.convertValue(comment, Map.class);
    MemberInformation info = comment.getMember().getMemberInformation();
    
    member.put("username", info.getFullname());
    member.put("avatar", info.getPhoto());
    body.put("member", member);
    body.put("postTime", comment.getPostTime().format(FORMATTER));
    body.put("editTime", comment.getEditTime().format(FORMATTER));
    body.put("isInstructor", isisInstructor(comment.getMember(), classBean));
    body.put("isOwner", 1);
    body.put("likeCount", comment.getLikeCount());
    
    return body;
  }
  
  /**
   * getResponseBody 的 Overload 版本，主要是回傳多則留言
   * 這裡會需要處理父子留言的問題，來回傳一個正確的結構給前端。
   * 
   * 目前格式為:
   * {
       "username": "登入者中文名稱",
       "avatar" : "大頭貼", 
       "result": [  
         {
          "title": "留言標題",
          "content": "留言內容",
          "uuid": "c415c69ee953493ab48a001 ",
          "postTime": "2022/04/04 05:22:14",
          "editTime": "2022/04/04 05:22:14",
          * 此為子留言
          "comments": [
              {
                "title": "留言標題",
                "content": "留言內容",
                "uuid": "791cfd29804a423791d1abc ",
                "postTime": "2022/04/04 05:28:15",
                "editTime": "2022/04/04 05:28:15",
                "member": {
                  "username": "使用者中文名稱",
                  "avatar": 大頭貼
                }
              }
            ],
            "member": {
              "username": "使用者中文名稱",
              "avatar": 大頭貼
            }
          }
        ]
      }
   * 
   * @param comments: 要處理的 Comment 列表，此列表都只會是父留言，已經從資料庫用條件篩出來。
   * @return Map Object
   */
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> getResponseBody(List<Comment> comments, Member currMember) {
    List<Map<String, Object>> body = new ArrayList<Map<String,Object>>();
    List<Map<String, Object>> childrenList;
    Map<String, Object> parentMap;
    ObjectMapper objectMapper = new ObjectMapper();
    MemberInformation info;
    ClassBean classBean = comments.get(0).getClassBean();
    
    objectMapper.registerModule(new JavaTimeModule());
    
    // 循環每個父留言
    for (Comment comment : comments) {
      Map<String, Object> member = new LinkedHashMap<>();
      childrenList = new ArrayList<Map<String,Object>>();
      MemberInformation childInfo;
      
      // 取出子留言
      Set<Comment> children = comment.getComments();
      
      // 如果該父留言有子留言的話就要做處理
      if (children.size() != 0) {
        // 循環每個子留言
        for (Comment child: children) {
          Map<String, Object> childMap = objectMapper.convertValue(child, Map.class);
          Map<String, Object> childMember = new LinkedHashMap<>();
          Member m = child.getMember();
          
          childInfo = m.getMemberInformation();
          childMember.put("username", childInfo.getFullname());
          childMember.put("avatar", childInfo.getPhoto());
          childMap.put("member", childMember);
          childMap.put("postTime", child.getPostTime().format(FORMATTER));
          childMap.put("editTime", child.getEditTime().format(FORMATTER));
          childMap.put("isInstructor", isisInstructor(m, classBean));
          childMap.put("isOwner", isOwner(m, currMember));
          childMap.put("liked", liked(child, currMember));
          childMap.put("likeCount", child.getLikeCount());
            
          // 子留言的列表，因為可能有多則子留言
          childrenList.add(childMap);
        }
        
        // 對子留言用時間做排序
        childrenList.sort(new Comparator<Map<String, Object>>() {
          @Override
          public int compare(Map<String, Object> o1, Map<String, Object> o2) {
            DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy/MM/dd HH:mm:ss")
                .toFormatter(Locale.TAIWAN);
            
            LocalDateTime c1 = LocalDateTime.parse((CharSequence) o1.get("postTime"), df);
            LocalDateTime c2 = LocalDateTime.parse((CharSequence) o2.get("postTime"), df);
            
            return c1.compareTo(c2);
          }
        });
      }
      
      parentMap = objectMapper.convertValue(comment, Map.class);
      
      // 父留言會再添加子留言的屬性
      parentMap.put("comments", childrenList);
      
      Member m = comment.getMember();
      info = m.getMemberInformation();
      member.put("username", info.getFullname());
      member.put("avatar", info.getPhoto());
      parentMap.put("member", member);
      parentMap.put("postTime", comment.getPostTime().format(FORMATTER));
      parentMap.put("editTime", comment.getEditTime().format(FORMATTER));
      parentMap.put("isInstructor", isisInstructor(comment.getMember(), classBean));
      parentMap.put("isOwner", isOwner(m, currMember));
      parentMap.put("liked", liked(comment, currMember));
      parentMap.put("likeCount", comment.getLikeCount());

      body.add(parentMap);
    }

    return body;
  }
  
  /**
   * 建立 ResponseEntity 的輔助方法，方便直接設定回傳內容
   * 
   * @param statusVal: HTTP status code
   * @param message: 錯誤訊息
   * @return ResponseEntity Object
   */
  public ResponseEntity<Object> generateResponse(int statusVal, Object message) {
    Map<String, Object> body = new LinkedHashMap<>();
    
    body.put("status", statusVal);
    body.put("message", message);
    
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(body);
  }
}
