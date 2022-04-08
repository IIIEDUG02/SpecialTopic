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
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.YPclass;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.YPclassService;



@Controller
public class YPclassController {
    @Autowired
    private YPclassService ypclassService;

    @Autowired
    private ClassManagementService classMService;

    @Autowired
    private MemberService memberService;


    @GetMapping("/ypclassquerybyid.controller")
    public YPclass processQueryByIdAction(Integer id) {
        return ypclassService.findById(id);
    }

    @GetMapping("/ypclasssavetop5")
    public String processSaveTop5(Model m) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	int year = cal.get(Calendar.YEAR);
        List<Map<String, Integer>> cList = classMService.getYearTop5Class(year);

        List<YPclass> ypclassList = new ArrayList<YPclass>();
        for (Map<String, Integer> c : cList) {
            YPclass ypclass = new YPclass();
            ypclass.setClassID(c.get("cid"));
            ypclass.setYear(year);
            ypclass.setYearAmount(c.get("countcid"));
            ypclassService.insert(ypclass);


            ypclassList.add(ypclass);
        }

        m.addAttribute("ypclassList", ypclassList);
        return "success/Success3";
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

  
  
  @GetMapping("/ypclasscontrolltop5")
//  public ResponseEntity<List<Map<String, Object>>> processFindTop5(Model m) {
  public String controllFindTop5(Model m) {

    List<YPclass> ypclasscontroll = ypclassService.findAllRow();

    m.addAttribute("ypclasscontroll", ypclasscontroll);



//    return ResponseEntity.ok(result);
    return "success/Controllpage1";

  }
  
  @GetMapping("/ypclasschangetop5")
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
		return "success/Controllpage1";
	}
	
	ypclassService.resetypclass();
	ypclassService.updateypclass("1",pi1);
    ypclassService.updateypclass("2",pi2);
    ypclassService.updateypclass("3",pi3);
    
    List<YPclass> ypclassList = ypclassService.findAll();
    if(ypclassList!=null && !ypclassList.isEmpty()) {
    	m.addAttribute("ypclassList", ypclassList);
		return "success/Success3";
	}
    errors.put("pimsg", "請確認輸入值");
	return "success/Controllpage1";
  }
  
  @GetMapping("/resetypclass")
  public String resetypclass(Model m) {
	  List<YPclass> ypclasscontroll = ypclassService.findAllRow();
	  Map<String, String> errors = new HashMap<String, String>();
	  m.addAttribute("ypclasscontroll", ypclasscontroll);
	  m.addAttribute("errors", errors);
	  errors.put("resetmsg", "排序設定已清空");
	  ypclassService.resetypclass();
	  return "success/Controllpage1";
  }
  
    @GetMapping("/ypclassfindtop5")
    @ResponseBody
    // public ResponseEntity<List<Map<String, Object>>> processFindTop5(Model m) {
    public List<Map<String, Object>> processFindTop5(Model m) {

        List<YPclass> YPclassList = ypclassService.findAll();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if(YPclassList!=null && !YPclassList.isEmpty()) {
	        for (YPclass c : YPclassList) {
	            Map<String, Object> classTeacherInfo = new HashMap<String, Object>();
	            ClassBean classBean = c.getClassBean();
	            Member teacher = memberService.findByUid(classBean.getUid());
	
	            classBean.setCurriculumbean(null);
	
	            classTeacherInfo.put("class", classBean);
	            classTeacherInfo.put("teacher", teacher.getMemberInformation());
	            result.add(classTeacherInfo);
	        }
        }else {
        	List<YPclass> ypclassList = ypclassService.findRow();
        	for (YPclass c : ypclassList) {
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
