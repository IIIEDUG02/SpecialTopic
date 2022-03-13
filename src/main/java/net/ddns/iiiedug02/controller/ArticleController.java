package net.ddns.iiiedug02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.model.service.ArticalService;
import net.ddns.iiiedug02.util.SqlDateUtil;

@Controller
@RequestMapping(path="/ac")
@ResponseBody
public class ArticleController {

	@Autowired
	  private ArticalService articalService;
	
	private SqlDateUtil dutil = new SqlDateUtil();
	
	@RequestMapping("/create")
	public ArticleBean ad() {
		
		ArticleBean c = new ArticleBean();
		c.setContext("１２３");
		c.setPageViews(0);
		c.setTitle("asd");
		c.setUserid(1);
		c.setPublicTime(dutil.strToDate("2002-01-20"));//如何輸入日期?
	    return articalService.insert(c);
	  }
	
	@RequestMapping("/read")
	public ArticleBean casb() {
	    return articalService.getById(1);
	  }
	
	@RequestMapping("/update")
	public ArticleBean asd() {
		ArticleBean c = new ArticleBean();
		c.setId(1);
		c.setContext("jhrtjkhsdjkfghdsfjkgdfjkg");
		c.setPageViews(0);
		c.setTitle("asd");
		c.setUserid(1);
		c.setPublicTime(null);
	    return articalService.update(c);
	  }
	
	@RequestMapping("/delete")
	public void dasd() {
	    articalService.delete(1);
	  }
	
}
