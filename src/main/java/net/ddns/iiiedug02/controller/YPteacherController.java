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
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.bean.YPteacher;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.YPteacherService;

@Controller
public class YPteacherController {
    @Autowired
    private YPteacherService ypteacherService;

    @Autowired
    private ClassBeanService classbeanService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ClassManagementService classMService;


    @GetMapping("/ypteacherquerybyid.controller")
    public YPteacher processQueryByIdAction(Integer id) {
        return ypteacherService.findById(id);
    }

    @GetMapping("/ypteachersavetop5")
    public String processSaveTop5(Model m) {
        List<Map<String, Integer>> cList = classMService.getYearTop5Class(2022);

        List<YPteacher> ypteacherList = new ArrayList<YPteacher>();
        for (Map<String, Integer> c : cList) {
            ClassBean cbBean = classbeanService.findById(c.get("cid"));
            Member mb = memberService.findByUid(cbBean.getUid());
            YPteacher ypteacher = new YPteacher();
            ypteacher.setMemberInformation(mb.getMemberInformation());
            ypteacher.setYear(2022);
            ypteacher.setYearAmount(c.get("countcid"));
            ypteacherService.insert(ypteacher);


            ypteacherList.add(ypteacher);
        }

        m.addAttribute("ypteacherList", ypteacherList);
        return "success/Success";
    }

    @GetMapping("/ypteacherfindtop5")
    @ResponseBody
    public List<MemberInformation> processFindTop5(Model m) {
        List<MemberInformation> memberinfoList = new ArrayList<MemberInformation>();

        List<YPteacher> YPteacherList = ypteacherService.findAll();
        for (YPteacher y : YPteacherList) {
            MemberInformation mbinfo = y.getMemberInformation();
            mbinfo.setMember(null);
            memberinfoList.add(mbinfo);
        }

        return memberinfoList;



    }

}
