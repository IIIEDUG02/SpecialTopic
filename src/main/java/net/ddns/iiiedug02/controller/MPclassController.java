package net.ddns.iiiedug02.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.MPclass;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.YPclass;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.MPclassService;
import net.ddns.iiiedug02.model.service.MemberService;



@Controller
public class MPclassController {
    @Autowired
    private MPclassService mpclassService;

    @Autowired
    private ClassManagementService classMService;
    
    @Autowired
    private MemberService memberService;


    @GetMapping("/mpclassquerybyid.controller")
    public MPclass processQueryByIdAction(Integer id) {
        return mpclassService.findById(id);
    }

    @GetMapping("/mpclasssavetop5")
    public String processSaveTop5(Model m) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH) +1;
        List<Map<String, Integer>> cList = classMService.getMonthTop5Class(year, month);

        List<MPclass> mpclassList = new ArrayList<MPclass>();
        for (Map<String, Integer> c : cList) {
            MPclass mpclass = new MPclass();
            mpclass.setClassID(c.get("cid"));
            mpclass.setMonth(month);
            mpclass.setMonthAmount(c.get("countcid"));
            mpclassService.insert(mpclass);


            mpclassList.add(mpclass);
        }

        m.addAttribute("mpclassList", mpclassList);
        return "success/Success4";
    }

    // @GetMapping("/ypclassfindtop5")
    // @ResponseBody
    // public List<ClassBean> processFindTop5(Model m) {
    // List<ClassBean> classBeanList = new ArrayList<ClassBean>();
    //
    // List<YPclass> YPclassList = ypclassService.findAll();
    // for (YPclass c : YPclassList) {
    // ClassBean classbean = classBeanService.findById(c.getClassID());
    //// classbean.setClassDetailsBean(null);
    // classbean.setCurriculumbean(null);
    //
    // classBeanList.add(classbean);
    // }
    //
    // return classBeanList;
    //
    // }

//    @GetMapping("/mpclassfindtop5")
//    @ResponseBody
//    public List<ClassBean> processFindTop5(Model m) {
//        List<ClassBean> classBeanList = new ArrayList<ClassBean>();
//
//        List<MPclass> mpclassList = mpclassService.findAll();
//        for (MPclass c : mpclassList) {
//            ClassBean classBean = c.getClassBean();
//            classBean.setCurriculumbean(null);
//
//            classBeanList.add(classBean);
//        }
//
//        return classBeanList;
//
//    }
    
    @GetMapping("/mpclassfindtop5")
    @ResponseBody
    // public ResponseEntity<List<Map<String, Object>>> processFindTop5(Model m) {
    public List<Map<String, Object>> processFindTop5(Model m) {

        List<MPclass> MPclassList = mpclassService.findAll();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for (MPclass c : MPclassList) {
            Map<String, Object> classTeacherInfo = new HashMap<String, Object>();
            ClassBean classBean = c.getClassBean();
            Member teacher = memberService.findByUid(classBean.getUid());

            classBean.setCurriculumbean(null);

            classTeacherInfo.put("class", classBean);
            classTeacherInfo.put("teacher", teacher.getMemberInformation());
            result.add(classTeacherInfo);
        }

        // return ResponseEntity.ok(result);
        return result;

    }

}
