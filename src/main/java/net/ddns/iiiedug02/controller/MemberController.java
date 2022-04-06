package net.ddns.iiiedug02.controller;

import java.text.ParseException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.util.UniversalTool;

@Controller
public class MemberController {

    @Autowired
    private MemberService ms;
    
    @Autowired
    private  UniversalTool ut;

    @PostMapping(value = "/registerAction1",
            produces = "application/x-www-form-urlencoded;charset=UTF-8")
    public String registerAction1(@RequestParam Map<String, String> params, HttpSession session,
            Model m, HttpServletRequest request) throws ParseException {

        Member mb = ms.findByUsername(params.get("username"));
        if (null != mb) {
            m.addAttribute("errMsg", "帳號已註冊");
            return "member/registerPage1";
        }
        
        MemberInformation mbi = new MemberInformation();
        mbi.setAddress(params.get("address"));
        mbi.setBirthday(ut.strToSqlDate(params.get("birthday")));
        mbi.setEmail(params.get("email"));
        mbi.setFullname(params.get("fullname"));
        mbi.setGender(Integer.parseInt(params.get("gender")));
        mbi.setIdentitycard(params.get("identitycard"));
        mbi.setJob(params.get("job"));
        mbi.setPassportname(params.get("passportname"));
        mbi.setPhone(params.get("phone"));
        mbi.setPhoto(params.get("photo"));

        mb = new Member();
        mb.setUsername(params.get("username"));
        mb.setPassword(params.get("password"));
        mb.setActivated((short) 0);
        
        mb.setMemberInformation(mbi);
        mbi.setMember(mb);
        ms.createMemberBean(mb);
        session.setAttribute("registerBean", mb);
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
