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
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.ddns.iiiedug02.exception.RoleNotFoundException;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassDetailsBean;
import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.bean.ClassOnlineBean;
import net.ddns.iiiedug02.model.bean.CurriculumBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.ClassOnlineService;
import net.ddns.iiiedug02.model.service.CurriculumService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.ShoppingCartService;
import net.ddns.iiiedug02.util.UniversalTool;

@Controller
@SessionAttributes({ "classbean" })
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
	private ResourceLoader resourceLoader;

	@Autowired
	private ClassManagementService cms;

	@Autowired
	private UniversalTool utool;

	@Autowired
	private ShoppingCartService scs;

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
	public String createProcessClass(@RequestParam Map<String, String> formData, Model model,
			@RequestParam("photopath") MultipartFile mf) throws IOException {

		String pattern = "yyyy-MM-dd-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Random random = new Random();
		int rNumber = 10000 + random.nextInt(90000);

		String type = FilenameUtils.getExtension(mf.getOriginalFilename());
		if (type.isEmpty()) {
			return "no photo";
		}
		String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;
		String tempDir = resourceLoader.getResource("classpath:static/").getFile().toString() + "/classphoto/";
		File tempDirFile = new File(tempDir);
		tempDirFile.mkdirs();

		String saveFilePath = tempDir + fileName;
		File saveFile = new File(saveFilePath);

		mf.transferTo(saveFile);

		ClassBean cb = new ClassBean();
		cb.setPhoto("/SpecialTopic/classphoto/" + fileName);
		cb.setClassType(formData.get("classtype"));
		cb.setTitle(formData.get("classtitle"));
		cb.setPrice(Integer.parseInt(formData.get("classprice")));
		cb.setUid(Integer.parseInt(formData.get("teacherid")));
		model.addAttribute("classbean", cb);

		return "class/createclassdetails";
	}

	// 創建classdetails
	@PostMapping(value = "/createclassdetails")
	public String createProcessClassDetails(@RequestParam Map<String, String> formData, HttpSession session,
			Model model) {
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
		List<ClassBean> cbList = cbs.findAll();
		model.addAttribute("allCbList", cbList);
		return "class/classList";
	}

	// 創建curriculum
	@PostMapping(path = "/createcurriculum")
	public String createCurriculum(@RequestParam("myVideo") MultipartFile mf, HttpServletRequest request, Model m,
			HttpSession session, @SessionAttribute ClassBean classbean, Principal p)
			throws IllegalStateException, IOException {

		String pattern = "yyyy-MM-dd-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Random random = new Random();
		int rNumber = 10000 + random.nextInt(90000);

		String type = FilenameUtils.getExtension(mf.getOriginalFilename());
		if (type.isEmpty()) {
			return "no video";
		}
		String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;
		String tempDir = resourceLoader.getResource("classpath:static/").getFile().toString() + "/classvideo/";
		// String tempDir = request.getSession().getServletContext().getRealPath("/") +
		// "/classvideo//";
		File tempDirFile = new File(tempDir);
		tempDirFile.mkdirs();

		String saveFilePath = tempDir + fileName;
		File saveFile = new File(saveFilePath);

		mf.transferTo(saveFile);
		CurriculumBean cub = new CurriculumBean();

		ClassBean cb = (ClassBean) m.getAttribute("classbean");
		cub.setClassbean(cb);
		cub.setChapter(request.getParameter("chapter"));
		cub.setVideo_path("/SpecialTopic/classvideo/" + fileName);
		cus.insert(cub);

		return this.editCurriculum(request, p, cb.getCid(), m);
	}

	// 編輯curriculum
	@PostMapping(path = "/updatecurriculum")
	public String updateCurriculum(@RequestParam("myVideo") MultipartFile mf, HttpServletRequest request, Model m,
			HttpSession session, @SessionAttribute ClassBean classbean, Principal p)
			throws IllegalStateException, IOException {

		String pattern = "yyyy-MM-dd-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Random random = new Random();
		int rNumber = 10000 + random.nextInt(90000);

		String type = FilenameUtils.getExtension(mf.getOriginalFilename());
		if (type.isEmpty()) {
			return "no video";
		}
		String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;
		String tempDir = resourceLoader.getResource("classpath:static/").getFile().toString() + "/classvideo/";
		// String tempDir = request.getSession().getServletContext().getRealPath("/") +
		// "../resources/static/classvideo//";
		File tempDirFile = new File(tempDir);
		tempDirFile.mkdirs();

		String saveFilePath = tempDir + fileName;
		File saveFile = new File(saveFilePath);

		mf.transferTo(saveFile);
		CurriculumBean cub = cus.findById(Integer.parseInt(request.getParameter("cuid")));

		ClassBean cb = (ClassBean) m.getAttribute("classbean");
		cub.setClassbean(cb);
		cub.setChapter(request.getParameter("chapter"));
		cub.setVideo_path("/SpecialTopic/classvideo/" + fileName);
		cus.insert(cub);

		return this.editCurriculum(request, p, cb.getCid(), m);
	}

	// 計算全部上線課程總數
	@ResponseBody
	@GetMapping(value = "/countclass")
	public int processCountClass() {
		return cbs.countClass();
	}

	// 尋找全部上線課程,api
	@GetMapping(path = "class/allonline")
	@ResponseBody
	public List<ClassBean> findAllOnlineClass() {
		List<ClassOnlineBean> coList = cos.findAllOnlineClass();
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
	public boolean deleteClass(HttpServletRequest request, Principal principal, @PathVariable("cid") int cid) {
		if (utool.hasRole(principal, "admin")) {
			return cbs.deleteById(cid);
		}
		return false;
	}

	@DeleteMapping("class/delete/curriculum/{cuid}")
	@ResponseBody
	public boolean deleteCurriculum(HttpServletRequest request, Principal principal, @PathVariable("cuid") int cuid) {
		if (utool.hasRole(principal, "admin")) {
			return cus.deleteById(cuid);
		}
		return false;
	}

	@GetMapping("class/editCurriculum/{cid}")
	public String editCurriculum(HttpServletRequest request, Principal principal, @PathVariable("cid") int cid,
			Model m) {
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

	@GetMapping("viewClass/{cid}")
	public String viewClass(@PathVariable("cid") int cid, Model m, Principal p, RedirectAttributes attr) {

		// 課程資訊
		ClassBean cb = cbs.findById(cid);
		if (cb == null) {
			attr.addAttribute("msg", "找不到課程資料");
			return "redirect:/class/list";
		}
		m.addAttribute("classBean", cb);

		if (p != null) {
			Member loginBean = ms.findByUsername(p.getName());
			// 個人購課紀錄
			ClassManagementBean cmb = cms.findByUidAndCid(loginBean.getUid(), cid);
			if (null != cmb) {
				m.addAttribute("classManagerBean", cmb);
			} else {
				// 購課車
				ShoppingCart sc = scs.findByUidAndClassBean(loginBean.getUid(), cb);
				m.addAttribute("ShoppingCart", sc);
			}
		}

		return "class/viewClass";
	}

	@GetMapping("getClassTypeList")
	@ResponseBody
	public List<String> showClassType(HttpServletRequest request, Model m) {
		List<String> classTypeList = cbs.findAllClassType();
		return classTypeList;
	}

	@GetMapping("class/showClassType/{classType}")
	public String showOneClassType(HttpServletRequest request, Model m, @PathVariable("classType") String classtype) {
		List<ClassBean> classOneTypeList = cbs.findByClassType(classtype);
		m.addAttribute("classOneTypeList", classOneTypeList);
		return "courses";
	}

	/*
	 * 更改classmanagement status to 1
	 */
	@PostMapping("class/api/classmanagement/on/{cid}")
	@ResponseBody
	public String manageClassToOn(@PathVariable("cid") int cid, Principal principal) {
		Member loginBean = ms.findByUsername(principal.getName());
		ClassManagementBean cmb = cms.findByUidAndCid(loginBean.getUid(), cid);
		cmb.setStatus(1);
		cms.update(cmb);
		return "sucesss";
	}

	/*
	 * 更改classmanagement status to 1
	 */
	@PostMapping("class/api/classmanagement/off/{cid}")
	@ResponseBody
	public String manageClassToOff(@PathVariable("cid") int cid, Principal principal) {
		Member loginBean = ms.findByUsername(principal.getName());
		ClassManagementBean cmb = cms.findByUidAndCid(loginBean.getUid(), cid);
		cmb.setStatus(0);
		cms.update(cmb);
		return "sucesss";
	}

	/*
	 * set class to online
	 */
	@PostMapping("class/api/postToOnline/{cid}/{online}")
	public boolean postToOnline(@PathVariable("online") int online, @PathVariable("cid") int cid, Principal principal,
			HttpServletRequest request) {
		if (utool.hasRole(principal, "admin")) {
			throw new RoleNotFoundException();
		}
		if (null == cos.findByCid(cid)) {
			ClassOnlineBean cob = new ClassOnlineBean();
			cob.setCid(cid);
			cob.setOnline(1);
			cos.insert(cob);
			return true;
		} else if (1 == online) {
			ClassOnlineBean cob = cos.findByCid(cid);
			cob.setOnline(0);
			return false;
		} else {
			ClassOnlineBean cob = cos.findByCid(cid);
			cob.setOnline(0);
			return true;
		}
	}

}
