package net.ddns.iiiedug02.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import net.ddns.iiiedug02.exception.RoleNotFoundException;
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
import net.ddns.iiiedug02.util.UniversalTool;

@Controller
@SessionAttributes({"classbean"})
public class ClassController {
    @Autowired
    private CurriculumService cus;

    @Autowired
    private ClassBeanService cbs;

    @Autowired
    private MemberService ms;

    @Autowired
    private ClassOnlineService cos;

    @Autowired
    private ClassManagementService cms;


    @Autowired
    private UniversalTool utool;

    /*
     * 進入後臺管理課程，區分admin跟normal身份
     */
    @GetMapping("class/list")
    public String dispatch(HttpServletRequest request, Principal principal, Model model) {
        Member loginBean = ms.findByUsername(principal.getName());
        if (utool.hasRole(principal, "admin")) {
            List<ClassBean> cbList = cbs.findAll();
            model.addAttribute("allCbList", cbList);
            return "class/classList";
        } else if (utool.hasRole(principal, "normal")) {
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
                model.addAttribute("uncompleteList", uncompleteList);
                model.addAttribute("completeList", completeList);
            }
            return "class/classList";
        }
        throw new RoleNotFoundException();
    }

    // 進入編輯課程頁面
    @GetMapping(value = "class/update/{cid}")
    public String updatePage(@PathVariable int cid, Model model) {
        ClassBean cb = cbs.findById(cid);
        model.addAttribute("classBean", cb);
        return "backstage/classEdit";
    }
    
	// 創建class
	@PostMapping(value = "/createclass", produces = "text/html;charset=utf-8")
	public String createProcessClass(@RequestParam Map<String, String> formData, Model model) {
		ClassBean cb = new ClassBean();
		cb.setClassType(formData.get("classtype"));
		cb.setTitle(formData.get("classtitle"));
		cb.setPrice(Integer.parseInt(formData.get("classprice")));
		cb.setUid(Integer.parseInt(formData.get("teacherid")));
		model.addAttribute("classbean", cb);

		return "class/createclassdetails";
	}

	// 創建classdetails
	@PostMapping(value = "/createclassdetails")
	public String createProcessClassDetails(@RequestParam Map<String, String> formData, HttpSession session) {
		ClassDetailsBean cdb = new ClassDetailsBean();
		cdb.setDescript(formData.get("descript"));
		cdb.setGoal(formData.get("goal"));
		cdb.setNeed_tool(formData.get("needed_tool"));
		cdb.setStu_required(formData.get("stu_required"));
		cdb.setVideo(formData.get("video"));
		cdb.setLength_min(Integer.parseInt(formData.get("length_min")));
		ClassBean cb = (ClassBean) session.getAttribute("classbean");
		cb.setClassDetailsBean(cdb);
		cdb.setClassbean(cb);
		cbs.insert(cb);
		return "class/createcurriculum";
	}

	// 創建curriculum
	@PostMapping(path = "/createcurriculum")
	public String uploadvideo(@RequestParam("myVideo") MultipartFile mf, HttpServletRequest request, Model m,
			 HttpSession session) throws IllegalStateException, IOException {

		String pattern = "yyyy-MM-dd-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Random random = new Random();
		int rNumber = 10000 + random.nextInt(90000);

		String type = FilenameUtils.getExtension(mf.getOriginalFilename());
		if (type.isEmpty()) {
			return "no video";
		}
		String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;

		String tempDir = request.getSession().getServletContext().getRealPath("/") + "../resources/static/classvideo//";
		File tempDirFile = new File(tempDir);
		tempDirFile.mkdirs();

		String saveFilePath = tempDir + fileName;
		File saveFile = new File(saveFilePath);

		mf.transferTo(saveFile);

		CurriculumBean cub = new CurriculumBean();

		ClassBean cb = (ClassBean) m.getAttribute("classBean");
		cub.setClassbean(cb);
		cub.setChapter(request.getParameter("chapter"));
		cub.setVideo_path("/SpecialTopic/classvideo/" + fileName);
		cus.insert(cub);

		return "class/editCurriculum";
	}
    

    // 計算全部上線課程總數
    @ResponseBody
    @GetMapping(value = "/countclass")
    public int processCountClass() {
        return cbs.countClass();
    }

    // 課程圖片上傳 api
    @PostMapping(path = "class/api/uploadphoto")
    @ResponseBody
    public String uploadPhoto(@RequestParam("myPhoto") MultipartFile mf, HttpServletRequest request,
            Model m) throws IllegalStateException, IOException {

        String pattern = "yyyy-MM-dd-HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Random random = new Random();
        int rNumber = 10000 + random.nextInt(90000);

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
        return "...";
    }

    // 尋找全部上線課程,api
    @GetMapping(path = "class/allonline")
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

    @GetMapping("class/curriculums/{cid}")
    public String classtest(Model m, @PathVariable("cid") int cid) {
        ClassBean cb = cbs.findById(cid);
        List<CurriculumBean> cusList = cus.findAllByClassbean(cb);
        m.addAttribute("CurriculumList", cusList);
        m.addAttribute("cid", cid);
        return "class/curriculum-nilm";
    }

    @GetMapping("class/curriculums/api/{cid}")
    @ResponseBody
    public List<CurriculumBean> getCurListJson(Model m, @PathVariable("cid") int cid) {
        ClassBean cb = cbs.findById(cid);
        return cus.findAllByClassbean(cb);
    }

    @DeleteMapping("class/delete/{cid}")
    @ResponseBody
    public boolean deleteClass(HttpServletRequest request, Principal principal,
            @PathVariable("cid") int cid) {
        if (utool.hasRole(principal, "admin")) {
            return cbs.deleteById(cid);
        }
        return false;
    }
    
    @DeleteMapping("class/delete/curriculum/{cuid}")
    @ResponseBody
    public boolean deleteCurriculum(HttpServletRequest request, Principal principal,
            @PathVariable("cuid") int cuid) {
        if (utool.hasRole(principal, "admin")) {
            return cus.deleteById(cuid);
        }
        return false;
    }

    @GetMapping("class/editCurriculum/{cid}")
    public String editCurriculum(HttpServletRequest request, Principal principal,
            @PathVariable("cid") int cid, Model m) {
        if (utool.hasRole(principal, "admin")) {
            ClassBean cb = cbs.findById(cid);
            List<CurriculumBean> cubList = cus.findAllByClassbean(cb);
            m.addAttribute("cubList", cubList);
            m.addAttribute("classbean", cb);
            return "class/editCurriculum";
        } else {
            m.addAttribute("errMsg", "您無權限執行此操作");
            return "index";
        }

    }
    
    @GetMapping("class/showClassType")
    public List<String> showClassType(HttpServletRequest request, Model m) {
        List<String> classTypeList = cbs.findAllClassType();
        m.addAttribute("classTypeList",classTypeList);
        return classTypeList;
    }
    
}
