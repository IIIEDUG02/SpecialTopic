package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

/*
 * 購物車相關的Controller
 * 
 * @author Nilm
 */
@Controller
@RequestMapping("ShoppingCart")
public class ShoppigCartController {

	@Autowired
	private UniversalTool utool;
	@Autowired
	private ShoppingCartService shoppigCartService;
	@Autowired
	private ClassBeanService classBeanService;

	/*
	 * RESTfull API，取的購物車清單
	 * 
	 * @author Nilm
	 */
	@GetMapping("api/getList")
	@ResponseBody
	// @CrossOrigin(origins = "*")
	public List<ShoppingCart> getShoppingCart(HttpSession session, Principal p) {
		List<ShoppingCart> result;
		try {
			Member loginBean = utool.getLoiginBean(session, p);
			result = shoppigCartService.findAllByUid(loginBean.getUid());
		} catch (Exception e) {
			result = new ArrayList<ShoppingCart>(0);
		}

		return result;
	}

	/*
	 * 導向購物車頁面，傳遞購物車清單、總價
	 * 
	 * @author Nilm
	 */
	@GetMapping("getInfo")
	public String getShoppingCart(HttpSession session, Principal p, Model m) {
		Member loginBean = utool.getLoiginBean(session, p);

		List<ShoppingCart> scl = shoppigCartService.findAllByUid(loginBean.getUid());
		int sum = 0;
		for (ShoppingCart sc : scl) {
			sum = sum + sc.getClassBean().getPrice();
		}
		m.addAttribute("shoppingCartList", scl);
		m.addAttribute("sum", sum);

		return "tradeRecord/shopping_cart_info";
	}

	/*
	 * RESTfull API，刪除購物車品項
	 * 
	 * @author Nilm
	 */
	@DeleteMapping("api/{cid}")
	@ResponseBody
	public ClassBean deleteByCid(HttpSession session, Principal p, @PathVariable int cid) {

		Member loginBean = utool.getLoiginBean(session, p);
		if (null == loginBean) {
			throw new NotLoginException();
		}
		ClassBean cb = classBeanService.findById(cid);
		ShoppingCart sc = shoppigCartService.findByUidAndClassBean(loginBean.getUid(), cb);
		if (null != sc) {
			shoppigCartService.deleteById(sc.getId());
			return cb;
		}
		return null;

	}

	/*
	 * RESTfull API，新增購物車品項
	 * 
	 * @author Nilm
	 */
	@PostMapping("api/{cid}")
	@ResponseBody
	public ClassBean saveById(HttpSession session, Principal p, @PathVariable int cid) {
		Member loginBean = utool.getLoiginBean(session, p);
		if (null == loginBean) {
			return null;
		}

		ShoppingCart sci = new ShoppingCart();
		ClassBean classBean = classBeanService.findById(cid);
		sci.setUid(loginBean.getUid());
		sci.setClassBean(classBean);
		shoppigCartService.save(sci);
		return classBean;
	}
}
