package net.ddns.iiiedug02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.model.service.ArticalService;
import net.ddns.iiiedug02.util.SqlDateUtil;

@Controller
@RequestMapping(path = "/ac")
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
    c.setPublishTime(dutil.strToDate("2002-01-20"));
    return articalService.insert(c);
  }

  @RequestMapping("/read")
  public ArticleBean casb() {
    return articalService.getById(2);
  }

  @RequestMapping("/update")
  public ArticleBean asd() {
    ArticleBean c = new ArticleBean();
    c.setId(1);
    c.setContext("jhrtjkhsdjkfghdsfjkgdfjkg");
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

}
