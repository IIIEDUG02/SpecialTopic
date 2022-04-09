package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.exception.NotLoginException;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ShoppingCartService;
import net.ddns.iiiedug02.util.UniversalTool;

@Controller
@RequestMapping("ShoppingCart")
public class ShoppigCartController {

    @Autowired
    private UniversalTool utool;

    @Autowired
    private ShoppingCartService shoppigCartService;

    @Autowired
    private ClassBeanService classBeanService;

    @GetMapping("api/getList")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<ShoppingCart> getShoppingCart(HttpSession session, Principal p) {
        Member loginBean = utool.getLoiginBean(session, p);
        if (null == loginBean) {
            throw new NotLoginException();
        }
        return shoppigCartService.findAllByUid(loginBean.getUid());
    }

    @GetMapping("getInfo")
    public String getShoppingCart(HttpSession session, Principal p, Model m) {
        Member loginBean = utool.getLoiginBean(session, p);
        if (null == loginBean) {
            throw new NotLoginException();
        }

        List<ShoppingCart> scl = shoppigCartService.findAllByUid(loginBean.getUid());
        int sum = 0;
        for (ShoppingCart sc : scl) {
            sum = sum + sc.getClassBean().getPrice();
        }
        m.addAttribute("shoppingCartList", scl);
        m.addAttribute("sum", sum);

        return "tradeRecord/shopping_cart_info";
    }

    @DeleteMapping("api/{cid}")
    @ResponseBody
    public String deleteByCid(HttpSession session, Principal p, @PathVariable int cid) {

        Member loginBean = utool.getLoiginBean(session, p);
        if (null == loginBean) {
            throw new NotLoginException();
        }
        ClassBean cb = classBeanService.findById(cid);
        ShoppingCart sc = shoppigCartService.findByUidAndClassBean(loginBean.getUid(), cb);
        if (null != sc) {
            shoppigCartService.deleteById(sc.getId());
            return "success";
        }
        return "fail";

    }

    @PostMapping("api/{cid}")
    @ResponseBody
    public Object saveById(HttpSession session, Principal p, @PathVariable int cid) {
        Member loginBean = utool.getLoiginBean(session, p);
        if (null == loginBean) {
            return "fail";
        }

        ShoppingCart sci = new ShoppingCart();
        ClassBean classBean = classBeanService.findById(cid);
        sci.setUid(loginBean.getUid());
        sci.setClassBean(classBean);
        return shoppigCartService.save(sci);
    }
}
