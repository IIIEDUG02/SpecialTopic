package net.ddns.iiiedug02.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import net.ddns.iiiedug02.exception.NotLoginException;
import net.ddns.iiiedug02.helpers.ArticleHelper;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassDetailsBean;
import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.bean.ClassOnlineBean;
import net.ddns.iiiedug02.model.bean.CurriculumBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.ClassOnlineService;
import net.ddns.iiiedug02.model.service.CurriculumService;
import net.ddns.iiiedug02.model.service.MemberService;

@Controller
public class ClassController {

  @Autowired
  private ClassBeanService cbs;

  @Autowired
  private MemberService ms;

	@Autowired
	private ClassOnlineService cos;
	
	@Autowired
	private CurriculumService cus;

  @Autowired
  private ClassManagementService cms;

  @GetMapping(value = "/upload")
  public String toJsp() {
    return "class/uploadPhoto";
  }

  // Singleton pattern(單例模式): 保證物件只會 new 一次(不會有多個物件)
  private ArticleHelper articleHelper = ArticleHelper.getInstance();
  private String ROLE = "ROLE_admin";

  // 進入後臺管理課程
  @GetMapping("/instructor") // admin
  public String dispatch(HttpServletRequest request, Principal principal, Model model) {
    // 進入頁面需admin認證
    if (!articleHelper.hasRole(principal, ROLE)) {
      return "member/loginPage";
    }
    List<ClassBean> cbList = cbs.findAll();

		return "backstage/teacherpage";
	}
	// 進入編輯課程頁面
	@GetMapping(value = "/classUpdate/{cid}")
	public String updatePage(@PathVariable int cid, Model model) {
		ClassBean cb = cbs.findById(cid);
		model.addAttribute("classBean", cb);
		return "backstage/classEdit";
	}

    return "backstage/teacherpage";
  }

  // 進入編輯課程頁面
  @GetMapping(value = "/classUpdate/{cid}")
  public String updatePage(@PathVariable int cid, Model model) {
    ClassBean cb = cbs.findById(cid);
    model.addAttribute("classBean", cb);
    return "backstage/classEdit";
  }

  // 更新課程
  @PostMapping(value = "/Action")
  public String updateAction(@RequestBody MultiValueMap<String, String> formData) {

		CurriculumBean ccb = new CurriculumBean();
		ccb.setCid(Integer.parseInt(formData.get("cid").get(0)));
		ccb.setVideo_path("123");
		ccb.setChapter("123");
		ccb.setClassbean(cb);
    ClassBean cb = new ClassBean();
    cb.setCid(Integer.parseInt(formData.get("cid").get(0)));
    cb.setUid(Integer.parseInt(formData.get("uid").get(0)));
    cb.setClassType(formData.get("classType").get(0));
    cb.setPhoto(formData.get("photo").get(0));
    cb.setPrice(Integer.parseInt(formData.get("price").get(0)));
    cb.setTitle(formData.get("title").get(0));

    ClassDetailsBean cdb = new ClassDetailsBean();
    cdb.setCid(Integer.parseInt(formData.get("cid").get(0)));
    cdb.setDescript(formData.get("descript").get(0));
    cdb.setLength_min(Integer.parseInt(formData.get("length_min").get(0)));
    cdb.setGoal(formData.get("goal").get(0));
    cdb.setNeed_tool(formData.get("needed_tool").get(0));
    cdb.setStu_required(formData.get("stu_required").get(0));
    cdb.setVideo(formData.get("video").get(0));

    CurriculumBean ccb = new CurriculumBean();
    ccb.setCid(Integer.parseInt(formData.get("cid").get(0)));
    ccb.setVideo_path("123");
    ccb.setChapter("123");
    ccb.setClassbean(cb);

    Set<CurriculumBean> ccbSet = new HashSet<>();
    ccbSet.add(ccb);

		return "redirect:/seeteacherclass";
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

	// 計算全部上線課程總數
	@ResponseBody
	@GetMapping(value = "/countclass")
	public int processCountClass() {
		return cbs.countClass();
	}
	
	// 尋找全部上線課程
	@GetMapping(path = "/allonlineclass")
	@ResponseBody
	public List<ClassBean> findAllOnlineClass() {
		List<ClassOnlineBean> coList = cos.findAll();
		List<ClassBean> cbList = new ArrayList<ClassBean>();
		for (ClassOnlineBean co : coList) {
			ClassBean cb = cbs.findById(co.getCid());
			cb.setClassDetailsBean(null);
			cb.setCurriculumbean(null);
			cbList.add(cb);
		}
		return cbList;
	}

	// 課程圖片上傳
	@PostMapping(path = "/uploadphoto")
	@ResponseBody
	public String uploadPhoto(@RequestParam("myPhoto") MultipartFile mf, HttpServletRequest request, Model m)
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
		if (type.isEmpty()) {
			return "no image";
		}
		String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;

		String tempDir = request.getSession().getServletContext().getRealPath("/") + "../resources/static/classphoto//";
		File tempDirFile = new File(tempDir);
		tempDirFile.mkdirs();

		String saveFilePath = tempDir + fileName;
		File saveFile = new File(saveFilePath);

		mf.transferTo(saveFile);

		m.addAttribute("photopath", "/SpecialTopic/classphoto/" + fileName);

		return saveFilePath;
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

  // 計算全部上線課程總數
  @ResponseBody
  @GetMapping(value = "/countclass")
  public int processCountClass() {
    return cbs.countClass();
  }

  // 檔案上傳
  @PostMapping(path = "/uploadphoto")
  @ResponseBody
  public String uploadPhoto(@RequestParam("myPhoto") MultipartFile mf, HttpServletRequest request,
      Model m) throws IllegalStateException, IOException {

    String pattern = "yyyy-MM-dd-HH-mm-ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    Random random = new Random();
    int rNumber = 10000 + random.nextInt(90000);
    // 取副檔名方法一
    // String oName = mf.getOriginalFilename();
    // int oNameLenghth = oName.length();
    // String fileName =
    // simpleDateFormat.format(new Date()) + "-" + rNumber + oName.substring(oNameLenghth-4,
    // oNameLenghth);
    String type = FilenameUtils.getExtension(mf.getOriginalFilename());
    if (type.isEmpty()) {
      return "no image";
    }
    String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;

    String tempDir = request.getSession().getServletContext().getRealPath("/")
        + "../resources/static/classphoto//";
    File tempDirFile = new File(tempDir);
    tempDirFile.mkdirs();

    String saveFilePath = tempDir + fileName;
    File saveFile = new File(saveFilePath);

    mf.transferTo(saveFile);

    m.addAttribute("photopath", "/SpecialTopic/classphoto/" + fileName);

    return saveFilePath;
  }

  // 尋找全部上線課程
  @GetMapping(path = "/allonlineclass")
  @ResponseBody
  public List<ClassBean> findAllOnlineClass() {
    List<ClassOnlineBean> coList = cos.findAll();
    List<ClassBean> cbList = new ArrayList<ClassBean>();
    for (ClassOnlineBean co : coList) {
      ClassBean cb = cbs.findById(co.getCid());
      cb.setClassDetailsBean(null);
      cb.setCurriculumbean(null);
      cbList.add(cb);
    }
    return cbList;
  }

  // 查學生未完成的課程
  @GetMapping(path = "/viewClassesList/personal")
  public String getUncompleteClass(Principal p, Model m) {
    Member loginBean = ms.findByUsername(p.getName());
    List<ClassManagementBean> cmbList = cms.findByUid(loginBean.getUid());
    List<ClassBean> completeList = new ArrayList<ClassBean>();
    List<ClassBean> uncompleteList = new ArrayList<ClassBean>();

    if (!cmbList.isEmpty()) {
      for (ClassManagementBean cmb : cmbList) {
        ClassBean cb = cbs.findById(cmb.getCid());
        if (cmb.getStatus() == 1) {
          completeList.add(cb);
        } else {
          uncompleteList.add(cb);
        }
      }
      m.addAttribute("uncompleteList", uncompleteList);
      m.addAttribute("completeList", completeList);
    }
    return "class/student";
  }

}
