package net.ddns.iiiedug02.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.MPteacher;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.MPteacherService;
import net.ddns.iiiedug02.model.service.MemberService;



@Controller
public class MPteacherController {
    @Autowired
    private MPteacherService mpteacherService;

    @Autowired
    private ClassBeanService classbeanService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ClassManagementService classMService;

    @GetMapping("/mpteacherquerybyid.controller")
    public MPteacher processQueryByIdAction(Integer id) {
        return mpteacherService.findById(id);
    }

    @GetMapping("/mpteachersavetop5")
    public String processSaveTop5(Model m) {
        List<Map<String, Integer>> cList = classMService.getMonthTop5Class(2022, 3);

        List<MPteacher> mpteacherList = new ArrayList<MPteacher>();
        for (Map<String, Integer> c : cList) {
            ClassBean cbBean = classbeanService.findById(c.get("cid"));
            Member mb = memberService.findByUid(cbBean.getUid());
            MPteacher mpteacher = new MPteacher();
            mpteacher.setMemberInformation(mb.getMemberInformation());
            mpteacher.setMonth(3);
            mpteacher.setMonthAmount(c.get("countcid"));
            mpteacherService.insert(mpteacher);


            mpteacherList.add(mpteacher);
        }

        m.addAttribute("mpteacherList", mpteacherList);
        return "success/Success2";
    }

    @GetMapping("/mpteacherfindtop5")
    @ResponseBody
    public List<MemberInformation> processFindTop5(Model m) {
        List<MemberInformation> memberinfoList = new ArrayList<MemberInformation>();

        List<MPteacher> mpteacherList = mpteacherService.findAll();
        for (MPteacher mp : mpteacherList) {
            MemberInformation mbinfo = mp.getMemberInformation();
            mbinfo.setMember(null);
            memberinfoList.add(mbinfo);
        }

        return memberinfoList;



    }

}
