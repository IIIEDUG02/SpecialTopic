package net.ddns.iiiedug02.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.ddns.iiiedug02.util.SqlDateUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.lang.reflect.Type;

public class ArticleHelper {
	private static ArticleHelper instance;
	private SqlDateUtil SqlDateUtil;

	private ArticleHelper() {
		this.SqlDateUtil = new SqlDateUtil();
	}

	public static synchronized ArticleHelper getInstance() {
		if (instance == null)
			instance = new ArticleHelper();

		return instance;
	}

	public boolean hasRole(Principal principal, String role) {
		// 如果有登入過，可以取得 principal 物件，否則 principal 物件為 null
		if (principal != null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			// 得到登入的使用者的角色(role)
			// 例如: nilm 登入會得到 [ROLE_admin, ROLE_teacher] 兩個角色
			Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority())
					.collect(Collectors.toSet());

			return roles.contains(role);
		}

		return false;
	}

	public String generateUUID() {
		try {
			MessageDigest salt = MessageDigest.getInstance("SHA-256");
			salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
			String hex = new String(Hex.encode(salt.digest()));

			// 單純學 hahow 的文章 URI，長度只有 24
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

	public String getUsername(Principal principal) {
		String username = "";

		if (principal instanceof UserDetails)
			username = ((UserDetails) principal).getUsername();
		else
			username = principal.getName();

		return username;
	}

	public Date getDate() {
		LocalDate date = LocalDate.now();
		
		return SqlDateUtil.strToDate(date.toString());
	}
	
	public List<Integer> getTagsId(String[] strings) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<Integer>>(){}.getType();
		String json = gson.toJson(strings);
		List<Integer> list = gson.fromJson(json, type);
		
		return list;
	}
}
