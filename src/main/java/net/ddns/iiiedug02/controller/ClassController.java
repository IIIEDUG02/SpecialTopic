package net.ddns.iiiedug02.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import net.ddns.iiiedug02.exception.NotLoginException;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassOnlineBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassOnlineService;
import net.ddns.iiiedug02.model.service.MemberService;

@Controller
public class ClassController {

  @Autowired
  private ClassBeanService cbs;

  @Autowired
  private MemberService ms;
  
  @Autowired
  private ClassOnlineService cos;
  
  @GetMapping(value = "/upload")
  public String toJsp() {
	  return "uploadPhoto";
  }
  @GetMapping(value = "/uploadvideo")
  public String toJsp1() {
	  return "uploadvideo";
  }
  @GetMapping(value = "/play")
  public String playVideo() {
	  return "video";
  }
  @GetMapping(value = "/test")
  public String test() {
	  return "creatCurriculum";
  }

  // show課程
  @GetMapping(value = "/seeteacherclass")
  public String processShowClass(Principal p, Model m) {
    if (null == p) {
      throw new NotLoginException();
    }
    Member teacher = ms.findByUsername(p.getName());
    List<ClassBean> teacherclass = cbs.findAllByUid(teacher.getUid());
    m.addAttribute("teacherclass", teacherclass);

    return "teacherpage";

  }

  // 刪除課程
//  @GetMapping(value = "/deleteclass/{cid}")
//  public void deleteClass(@PathVariable int cid) {
//    cbs.deleteById(cid);
//  }
  
  //不同課程類型總數
  @ResponseBody
  @GetMapping(value = "/countclass")
  public int processCountClass() {
	  return cbs.countClass();
  }
  
  //檔案上傳
  @PostMapping(path = "/uploadphoto")
  @ResponseBody
  public String uploadPhoto(@RequestParam("myPhoto") MultipartFile mf, HttpServletRequest request,Model m)
      throws IllegalStateException, IOException {

    String pattern = "yyyy-MM-dd-HH-mm-ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    Random random = new Random();
    int rNumber = 10000 + random.nextInt(90000);
//    取副檔名方法一    
//    String oName = mf.getOriginalFilename();
//    int oNameLenghth = oName.length();
//    String fileName =
//        simpleDateFormat.format(new Date()) + "-" + rNumber + oName.substring(oNameLenghth-4, oNameLenghth);
    String type = FilenameUtils.getExtension(mf.getOriginalFilename());

    String fileName =
    		simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;

    String tempDir = request.getSession().getServletContext().getRealPath("/") + "PhotoDir//";
    File tempDirFile = new File(tempDir);
    tempDirFile.mkdirs();

    String saveFilePath = tempDir + fileName;
    File saveFile = new File(saveFilePath);
    mf.transferTo(saveFile);
    
    m.addAttribute("photopath","SpecialTopic/../PhotoDir/"+fileName);
    
    return saveFilePath;
  }
  
  
  	//尋找全部上線課程
  @GetMapping(path = "/allonlineclass")
  @ResponseBody
  public List<ClassBean> findAllOnlineClass() {
	  List<ClassOnlineBean> coList = cos.findAll();
	  List<ClassBean> cbList = new ArrayList<ClassBean>();
	  for(ClassOnlineBean co:coList) {
		  ClassBean cb = cbs.findById(co.getCid());
		  cb.setClassDetailsBean(null);
		  cb.setCurriculumbean(null);
		  cbList.add(cb);
	  }
	  return cbList;
	  
  }
  
  


}
