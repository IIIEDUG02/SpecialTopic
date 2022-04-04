package net.ddns.iiiedug02.helpers;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.ddns.iiiedug02.model.bean.Comment;
import net.ddns.iiiedug02.model.bean.MemberInformation;

/**
 * @author Anna
 * 
 * CommentController 的輔助類別，目前先求功能正常，之後要再做重構與修正。
 *
 */
public class CommentHelper {
  // 前端顯示的時間會做格式化
  private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
  private static CommentHelper instance;
  
  
  public static synchronized CommentHelper getInstance() {
    if (instance == null)
      instance = new CommentHelper();

    return instance;
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
   * @return Map Object: 回傳給前端會自動轉成 JSON
   */
  @SuppressWarnings("unchecked")
  public Map<String, Object> getResponseBody(Comment comment) {
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
    
    return body;
  }
  
  /**
   * getResponseBody 的 Overload 版本，主要是回傳多則留言
   * 這裡會需要處理父子留言的問題，來回傳一個正確的結構給前端。
   * 
   * 目前格式為:
   * 最外層為 list
   * [
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
   * 
   * @param comments: 要處理的 Comment 列表，此列表都只會是父留言，已經從資料庫用條件篩出來。
   * @return Map Object
   */
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> getResponseBody(List<Comment> comments) {
    List<Map<String, Object>> body = new ArrayList<Map<String,Object>>();
    List<Map<String, Object>> childrenList;
    Map<String, Object> parentMap;
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> member = new LinkedHashMap<>();
    MemberInformation info;
    
    objectMapper.registerModule(new JavaTimeModule());
    
    // 循環每個父留言
    for (Comment comment : comments) {
      childrenList = new ArrayList<Map<String,Object>>();
      
      // 取出子留言
      Set<Comment> children = comment.getComments();
      
      // 如果該父留言有子留言的話就要做處理
      if (children.size() != 0) {
        // 循環每個子留言
        for (Comment child: children) {
          Map<String, Object> childMap = objectMapper.convertValue(child, Map.class);
          Map<String, Object> childMember = new LinkedHashMap<>();
          
          info = child.getMember().getMemberInformation();
          childMember.put("username", info.getFullname());
          childMember.put("avatar", info.getPhoto());
          childMap.put("member", childMember);
          childMap.put("postTime", child.getPostTime().format(FORMATTER));
          childMap.put("editTime", child.getEditTime().format(FORMATTER));
          
          // 子留言的列表，因為可能有多則子留言
          childrenList.add(childMap);
        }
      }
      
      parentMap = objectMapper.convertValue(comment, Map.class);
      
      // 父留言會再添加子留言的屬性
      parentMap.put("comments", childrenList);

      info = comment.getMember().getMemberInformation();
      member.put("username", info.getFullname());
      member.put("avatar", info.getPhoto());
      parentMap.put("member", member);
      parentMap.put("postTime", comment.getPostTime().format(FORMATTER));
      parentMap.put("editTime", comment.getEditTime().format(FORMATTER));

      body.add(parentMap);
    }

    return body;
  }
}
