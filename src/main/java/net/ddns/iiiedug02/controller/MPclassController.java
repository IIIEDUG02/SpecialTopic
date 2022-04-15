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
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/mpclasscontrolltop5")
    public String controllFindTop5(Model m) {

        List<MPclass> mpclasscontroll = mpclassService.findAllRow();

        m.addAttribute("mpclasscontroll", mpclasscontroll);
    
        return "success/Controllpage2";

    }
    
    @GetMapping("/mpclasschangetop5")
    public String controllChangeTop5(@RequestParam("pi1") String pi1, @RequestParam("pi2") String pi2, @RequestParam("pi3") String pi3, Model m) {
  	
      Map<String, String> errors = new HashMap<String, String>();
  	m.addAttribute("errors", errors);
  	if(pi1==null || pi1.length()==0) {
  		errors.put("pi1", "請輸入課程ID");
  	}
  	
  	if(pi2==null || pi2.length()==0) {
  		errors.put("pi2", "請輸入課程ID");
  	}
  	
  	if(pi3==null || pi3.length()==0) {
  		errors.put("pi3", "請輸入課程ID");
  	}
  	if(errors!=null && !errors.isEmpty()) {
  		return "success/Controllpage2";
  	}
  	
  	mpclassService.resetmpclass();
  	mpclassService.updatempclass("1",pi1);
    mpclassService.updatempclass("2",pi2);
    mpclassService.updatempclass("3",pi3);
      
      List<MPclass> mpclassList = mpclassService.findAll();
      if(mpclassList!=null && !mpclassList.isEmpty()) {
      	m.addAttribute("mpclassList", mpclassList);
  		return "index";
  	}
      errors.put("pimsg", "請確認輸入值");
  	return "success/Controllpage2";
    }
    
    @GetMapping("/resetmpclass")
    public String resetmpclass(Model m) {
  	  List<MPclass> mpclasscontroll = mpclassService.findAllRow();
  	  Map<String, String> errors = new HashMap<String, String>();
  	  m.addAttribute("mpclasscontroll", mpclasscontroll);
  	  m.addAttribute("errors", errors);
  	  errors.put("resetmsg", "排序設定已清空");
  	  mpclassService.resetmpclass();
  	  return "index";
    }
    
    @GetMapping("/mpclassfindtop5")
    @ResponseBody
    // public ResponseEntity<List<Map<String, Object>>> processFindTop5(Model m) {
    public List<Map<String, Object>> processFindTop5(Model m) {

        List<MPclass> MPclassList = mpclassService.findAll();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if(MPclassList!=null && !MPclassList.isEmpty()) {
	        for (MPclass c : MPclassList) {
	            Map<String, Object> classTeacherInfo = new HashMap<String, Object>();
	            ClassBean classBean = c.getClassBean();
	            Member teacher = memberService.findByUid(classBean.getUid());
	
	            classBean.setCurriculumbean(null);
	
	            classTeacherInfo.put("class", classBean);
	            classTeacherInfo.put("teacher", teacher.getMemberInformation());
	            result.add(classTeacherInfo);
	        }
        }else {
        	List<MPclass> mpclassList = mpclassService.findRow();
        	for (MPclass c : mpclassList) {
	            Map<String, Object> classTeacherInfo = new HashMap<String, Object>();
	            ClassBean classBean = c.getClassBean();
	            Member teacher = memberService.findByUid(classBean.getUid());
	
	            classBean.setCurriculumbean(null);
	
	            classTeacherInfo.put("class", classBean);
	            classTeacherInfo.put("teacher", teacher.getMemberInformation());
	            result.add(classTeacherInfo);
	        }
        }
        
        
        

        // return ResponseEntity.ok(result);
        return result;

    }

}
