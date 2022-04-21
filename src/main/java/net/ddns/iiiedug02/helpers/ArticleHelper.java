package net.ddns.iiiedug02.helpers;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.util.UniversalTool;


public class ArticleHelper {

    @Autowired
    private UniversalTool utool;

    public boolean hasRole(Principal principal, String role) {
        String prefix = "ROLE_";

        // 如果有登入過，可以取得 principal 物件，否則 principal 物件為 null
        if (principal != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 得到登入的使用者的角色(role)
            // 例如: nilm 登入會得到 [ROLE_admin, ROLE_teacher] 兩個角色
            Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority())
                    .collect(Collectors.toSet());

            return roles.contains(prefix + role);
        }

        return false;
    }

    // 為了要幫文章產生唯一的識別碼(因為要透過網址去資料庫查出特定的某一篇文章)，所以要使用UUID(通用唯一辨識碼)
    // 因為網址不能帶中文過去，所以用 UUID 來取代
    public String generateUUID() {
        try {
            // 這裡使用 SHA-256 雜湊(hash)函數
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));

            // 轉成 16 進制字串
            String hex = new String(Hex.encode(salt.digest()));

            // 單純參考 hahow 的文章 URI，長度只有 24
            return hex.substring(0, 24);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    // 取的使用者名稱
    public String getUsername(Principal principal) {
        String username = "";

        if (principal instanceof UserDetails)
            username = ((UserDetails) principal).getUsername();
        else
            username = principal.getName();

        return username;
    }

    // 轉換成能夠進入資料庫的日期格式(YYYY-mm-dd)
    public Date getDate() throws ParseException {
        // now() 能夠取得當下時間
        LocalDate date = LocalDate.now();

        return utool.strToSqlDate(date.toString());
    }

    // 取得所有標籤的 pk(id)
    public List<Integer> getTagsId(String[] strings) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        String json = gson.toJson(strings);
        List<Integer> list = gson.fromJson(json, type);

        return list;
    }

    // 取得文章縮圖
    public Map<Integer, String> getThumbnails(List<ArticleBean> articles) {
        Map<Integer, String> thumbnails = new HashMap<Integer, String>();
        String content;

        // 使用正則將圖片取出
        Pattern pattern = Pattern.compile("src=\".*?\"");
        Matcher matcher;

        for (ArticleBean articleBean : articles) {
            content = articleBean.getContent();
            matcher = pattern.matcher(content);

            // 如果沒有圖片則使用預設圖片當縮圖
            if (matcher.find())
                thumbnails.put(articleBean.getId(), matcher.group());
            else
                thumbnails.put(articleBean.getId(), "src=\"img/articles/default.avif\"");
        }

        return thumbnails;
    }

    // 取得文章縮寫內容
    public Map<Integer, String> getAbbreviations(List<ArticleBean> articles) {
        Map<Integer, String> abbreviations = new HashMap<Integer, String>();
        String content;

        for (ArticleBean articleBean : articles) {
            content = articleBean.getContent();

            // 使用正則將 html 標籤去除
            content = content.replaceAll("<.*?>", "");

            // 將內容限制在 85 個字
            if (content.length() >= 90)
                content.substring(0, 85);

            abbreviations.put(articleBean.getId(), content);
        }

        return abbreviations;
    }
}
