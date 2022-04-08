package net.ddns.iiiedug02.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.ShoppingCartService;

@Controller
public class MultiController {

    @Autowired
    private ClassBeanService cbs;

    @Autowired
    private MemberService ms;

    @Autowired
    private ShoppingCartService scs;

    @Autowired
    private ClassManagementService cms;

    @GetMapping("viewClass/{cid}")
    public String viewClass(@PathVariable("cid") int cid, Model m, Principal p,
            RedirectAttributes attr) {


        // 課程資訊
        ClassBean cb = cbs.findById(cid);
        if (cb == null) {
            attr.addAttribute("msg", "找不到課程資料");
            return "redirect:/class/list";
        }
        m.addAttribute("classBean", cb);

        if (p != null) {
            Member loginBean = ms.findByUsername(p.getName());
            // 個人購課紀錄
            ClassManagementBean cmb = cms.findByUidAndCid(loginBean.getUid(), cid);
            if (null != cmb) {
                m.addAttribute("classManagerBean", cmb);
            } else {
                // 購課車
                ShoppingCart sc = scs.findByUidAndClassBean(loginBean.getUid(), cb);
                m.addAttribute("ShoppingCart", sc);
            }
        }

        return "class/viewClass";
    }
}
