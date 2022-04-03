package net.ddns.iiiedug02.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService ms;

    @PostMapping(value = "/registerAction1",
            produces = "application/x-www-form-urlencoded;charset=UTF-8")
    public String registerAction1(@RequestParam Map<String, String> params, HttpSession session,
            Model m, HttpServletRequest request) {

        Member mb = ms.findByUsername(params.get("username"));
        if (null != mb) {
            m.addAttribute("errMsg", "帳號已註冊");
            return "member/registerPage1";
        }

        mb = new Member();
        mb.setUsername(params.get("username"));
        mb.setPassword(params.get("password"));
        mb.setActivated((short) 0);
        session.setAttribute("registerBean", mb);
        return "redirect:/";
    }


    @PostMapping(value = "/registerAction2")
    public String registerAction2(@RequestBody MemberInformation mdb, HttpSession session,
            Model m) {
        Member mb = (Member) session.getAttribute("registerBean");
        mdb.setMember(mb);
        mb.setMemberInformation(mdb);
        ms.createMemberBean(mb);
        return "redirect:/";
    }

    @GetMapping("/countmember.controller")
    @ResponseBody
    public int countMemberAction() {
        return ms.countMember();
    }

    @GetMapping("/signout/success")
    public String signout() {
        return "redirect:/";
    }
}
