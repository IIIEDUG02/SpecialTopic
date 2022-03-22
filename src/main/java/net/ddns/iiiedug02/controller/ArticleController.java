package net.ddns.iiiedug02.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.model.service.ArticalService;
import net.ddns.iiiedug02.util.SqlDateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path="/articles")
@ResponseBody
public class ArticleController {

	@Autowired
	  private ArticalService articalService;
	
	private SqlDateUtil dutil = new SqlDateUtil();
	
	@RequestMapping("/create")
	public ArticleBean ad() {
		
		ArticleBean c = new ArticleBean();
		c.setContent("１２３");
		c.setPageViews(0);
		c.setTitle("asd");
		c.setUserid(1);
		c.setPublishTime(dutil.strToDate("2002-01-20"));
	    return articalService.insert(c);
	  }
	
	@GetMapping("/createArticle")
	public String createArticle(String title, String content) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
		System.out.println("現在時間:" + dtf.format(LocalDateTime.now()));
		System.out.println("文章標題:" + title);
		System.out.println("文章內容:" + content);
		
		
		ArticleBean article = new ArticleBean();
		article.setContent(content);
		article.setPageViews(0);
		article.setTitle(title);
		article.setUserid(1);
		article.setPublishTime(dutil.strToDate(dtf.format(LocalDateTime.now())));
		articalService.insert(article);
		
		return "Success";
	  }
	
	@GetMapping(path="/about")
		public String about() {
		   return "about";
		  }

	
	@RequestMapping(path="/read")
	public ArticleBean casb() {
	    return articalService.getById(8);
	  }
	
	@RequestMapping(path="/retrieve")
	public String retrieve(int id) {
		ArticleBean article = articalService.getById(id);
	    return article.getContent();
	  }
	
	@RequestMapping("/update")
	public ArticleBean asd() {
		ArticleBean c = new ArticleBean();
		c.setId(1);
		c.setContent("jhrtjkhsdjkfghdsfjkgdfjkg");
		c.setPageViews(0);
		c.setTitle("asd");
		c.setUserid(1);
		c.setPublishTime(null);
	    return articalService.update(c);
	  }
	
	@RequestMapping("/delete")
	public void dasd() {
	    articalService.delete(1);
	  }
	

	@RequestMapping("/all")
	public List<ArticleBean> getAll() {
		List<ArticleBean> articleList = articalService.getAll();
				
	    return articleList;
	  }
}
