package net.ddns.iiiedug02.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
import net.ddns.iiiedug02.exception.NotLoginException;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.service.MemberService;

public class UniversalTool {

    @Autowired
    private MemberService ms;

    /**
     * 檢查當前使用者是否具有角色
     * 
     * @param principal: Principa物件
     * @param role: 角色名稱，參考資料表 member_roles
     * @return Boolean: 是否有角色
     * 
     * @author Anna
     */
    public boolean hasRole(Principal principal, String role) {
        // 如果有登入過，可以取得 principal 物件，否則 principal 物件為 null
        if (principal != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // 得到登入的使用者的角色(role)
            Set<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
            return roles.contains("ROLE_" + role);
        }
        return false;
    }

    /**
     * 產生一組UUID
     * 
     * @param longth: 指定UUID的長度
     * @return UUID
     * 
     * @author Anna
     */
    public String generateUUID(int longth)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 這裡使用 SHA-256 雜湊(hash)函數
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes("StandardCharsets.UTF_8"));
        // 轉成 16 進制字串
        String hex = new String(Hex.encode(salt.digest()));
        // 運用 longth 參數回傳字串長度
        return hex.substring(0, longth);
    }

    /**
     * @param principal: Principa物件
     * @return String: 當前使用者的username
     * 
     * @author Anna
     */
    public String getUsername(Principal principal) {
        String username = "";
        if (principal instanceof UserDetails)
            username = ((UserDetails) principal).getUsername();
        else
            username = principal.getName();
        return username;
    }

    /**
     * 將yyyy-MM-dd的日期格式轉換成SQL的Date物件
     * 
     * @param strDate: yyyy-MM-dd格式的日期文字
     * @return Date: java.sql.Date的物件(能夠進入資料庫的日期物件)
     * 
     * @author nilm
     */
    public java.sql.Date strToSqlDate(String strDate) throws ParseException {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = format.parse(str);
        return new java.sql.Date(d.getTime());
    }

    /**
     * 取得已登入的MemberBean
     * 
     * @param session: HttpSession物件
     * @return principal: Principal物件)
     * 
     * @author nilm
     */
    public Member getLoiginBean(HttpSession session, Principal principal) throws NotLoginException {
        Member loginBean = (Member) session.getAttribute("loginBean");
        if (loginBean != null) {
            return loginBean;
        }
        if (principal != null) {
            loginBean = ms.findByUsername(principal.getName());
            session.setAttribute("loginBean", loginBean);
            return loginBean;
        }
        throw new NotLoginException();
    }
}
