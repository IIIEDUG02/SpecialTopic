package net.ddns.iiiedug02.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttribute;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.service.ShoppingCartService;

@Controller
public class ShoppigCartController {

  @Autowired
  private ShoppingCartService shoppigCartService;

  public List<ShoppingCart> getShoppingCart(@SessionAttribute("loginBean") Member loginBean) {
    return shoppigCartService.findByUid(loginBean.getUid());
  }
}
