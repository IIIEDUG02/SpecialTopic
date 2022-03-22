package net.ddns.iiiedug02.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import net.ddns.iiiedug02.helpers.ArticleHelper;
import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.model.bean.TagBean;
import net.ddns.iiiedug02.model.service.ArticalService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.TagService;
import net.ddns.iiiedug02.util.SqlDateUtil;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path="/articles")
public class ArticleController {
	@Autowired
	private ArticalService articleService;

	@Autowired
	private TagService tagService;

	@Autowired
	private MemberService memberService;
	
	// Singleton pattern(單例模式): 保證物件只會 new 一次(不會有多個物件)
	private ArticleHelper articleHelper = ArticleHelper.getInstance();
	
	// 含有 admin 角色的人才能新增文章
	private String ROLE = "ROLE_admin";
	
	@GetMapping("")
	public String list(Model model) {

		return "article/articles";
	}
	
	// 進入 "發佈文章" 頁面
	// Principal principal: 如果使用者已經登入，這個物件才會存在，否則會是 null
	@GetMapping("/create")
	public String create(HttpServletRequest request, Principal principal, Model model) {
		// 判斷使用者是否有管理員角色，才可以進入發表文章頁面，否則直接返回文章列表頁面
		if (articleHelper.hasRole(principal, ROLE)) {
			// 從資料庫撈出所有的標籤
			List<TagBean> tags = tagService.findAll();
			
			// model 物件能夠將資料傳入到 jsp 裡面
			// 放入 model，之後 jsp 可以取得 tags 物件
			model.addAttribute("tags", tags);

			return "article/createArticle";
		}
		
		// 如果使用者沒有登入或是使用者沒有管理員角色，則返回文章列表頁面
		return "articles";
	}
}
