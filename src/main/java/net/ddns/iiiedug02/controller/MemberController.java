package net.ddns.iiiedug02.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.bean.MemberRole;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.util.UniversalTool;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;

	@Autowired
	private UniversalTool ut;

	@Autowired
    private ResourceLoader resourceLoader;

	@PostMapping(value = "/registerAction1", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String registerAction1(@RequestParam Map<String, String> params, HttpSession session, Model m,
			HttpServletRequest request) throws ParseException {

		Member mb = ms.findByUsername(params.get("username"));
		if (null != mb) {
			m.addAttribute("errMsg", "帳號已註冊");
			return "member/registerPage1";
		}
		mb = new Member();

		mb.setUsername(params.get("username"));
		mb.setPassword(params.get("password"));
		mb.setActivated((short) 0);

		ms.save(mb);
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

	@GetMapping("/member/editInformation")
	public String editInformation(@RequestParam Map<String, String> params, Model m, HttpSession session,
			Principal principal) throws ParseException {

		Member mb = ut.getLoiginBean(session, principal);
		m.addAttribute("mb", mb);
		return "member/memberInformation";
	}

	@GetMapping("/member/editInformation/{uid}")
	public String editInformationAdmin(Model m, HttpSession session, Principal principal, PathVariable pathVariable) {

		if (ut.hasRole(principal, "admin")) {

//			Member mb = ut.getUsername(mb, uid);
//			m.addAttribute("mb", mb);

			return "member/memberInformation/{uid}";
		} else {
			return null;
		}
	}

	@PostMapping(value = "/memberUpdateInformation", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String UpdateInformation(@RequestParam Map<String, String> params, HttpSession session, Model m,
			HttpServletRequest request) throws ParseException {

		Member mb = ms.findByUsername(params.get("username"));

		MemberInformation mbi = mb.getMemberInformation();
		mbi.setAddress(params.get("address"));
		mbi.setEmail(params.get("email"));
		mbi.setFullname(params.get("fullname"));
		mbi.setJob(params.get("job"));
		mbi.setPhone(params.get("phone"));
		mb.setPassword(params.get("password"));

		// mb.setRoles(null);

		mb.setMemberInformation(mbi);
		mbi.setMember(mb);

		System.out.println(mb.getRoles().get(0));

		ms.save(mb);

		session.setAttribute("registerBean", mb);
		return "redirect:/";
	}

	public String processDelete(Principal p) {

		if (ut.hasRole(p, "admin")) {
//			ms.deleteByUsername(params.get("username"));
		}
		return "delete OK";
	}

	@PostMapping(path = "/creatembphoto")
	public String createphoto(@RequestParam("photo") MultipartFile mf, Map<String, String> params, HttpServletRequest request, Model m,
			HttpSession session, @SessionAttribute Member member, Principal p)
			throws IllegalStateException, IOException {

		String pattern = "yyyy-MM-dd-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Random random = new Random();
		int rNumber = 10000 + random.nextInt(90000);

		String type = FilenameUtils.getExtension(mf.getOriginalFilename());
		if (type.isEmpty()) {
			return "no photo";
		}
		String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;

		String tempDir = resourceLoader.getResource("classpath:static/").getFile().toString()+"/classvideo/";
	
		File tempDirFile = new File(tempDir);
		tempDirFile.mkdirs();

		String saveFilePath = tempDir + fileName;
		File saveFile = new File(saveFilePath);

		mf.transferTo(saveFile);
		
		Member mb = new Member();
		MemberInformation mbi = new MemberInformation();
		
		mbi.setPhoto("/SpecialTopic/memberphoto/" + fileName);
		mbi.setAddress(params.get("address"));
		mbi.setEmail(params.get("email"));
		mbi.setFullname(params.get("fullname"));
		mbi.setJob(params.get("job"));
		mbi.setPhone(params.get("phone"));
		
		mb.setPassword(params.get("password"));
		
		
		return "member/memberInformation/";
	}
}