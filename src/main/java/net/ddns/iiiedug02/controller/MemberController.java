package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ddns.iiiedug02.annotation.LogInfo;
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

		MemberInformation mbi = new MemberInformation();
		mbi.setEmail(params.get("email"));

		mb = new Member();
		MemberRole mrb = new MemberRole();
		mrb.setRole("normal");
		mrb.setMember(mb);
		List<MemberRole> rs = new ArrayList<>(1);
		rs.add(mrb);

		mb.setUsername(params.get("username"));
		mb.setPassword(params.get("password"));
		mb.setActivated((short) 0);
		mb.setRoles(rs);

		mb.setMemberInformation(mbi);
		mbi.setMember(mb);
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
	@LogInfo
	public String editInformation(Model m, HttpSession session, Principal principal) {

		Member mb = ut.getLoiginBean(session, principal);
		m.addAttribute("mb", mb);
		return "member/memberInformation";
	}

	@GetMapping("/member/editInformation/{uid}")
	public String editInformationAdmin(Model m, HttpSession session, Principal principal,@PathVariable("uid") int uid) {

		if (ut.hasRole(principal, "admin")) {

			Member mb = ms.findByUid(uid);
			m.addAttribute("mb", mb);
			

			return "member/memberInformation";
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
		mbi.setIdentitycard(params.get("identitycard"));
		mbi.setPassportname(params.get("passportname"));
		mbi.setGender(Integer.parseInt(params.get("gender")));
		mbi.setPhone(params.get("phone"));
		mbi.setBirthday(params.get("birthday"));
		//如果輸入值是空白,不應該set password
		if (params.get("password").length() != 0) {
			mb.setPassword(params.get("password"));
		}

		mb.setMemberInformation(mbi);
		mbi.setMember(mb);
		
		if (params.get("password").length() <= 20 && params.get("password").length() != 0) {
			ms.save(mb);
		} else {
			ms.update(mb);
		}

		session.setAttribute("registerBean", mb);
		return "redirect:/";
	}

	@GetMapping("/member/membermanage")//{username}
	public String MemberDelete() { //@PathVariable("username") String username
		//ms.deleteByUsername(username);

		
		return "/member/membermanage";

	}

	@PostMapping(path = "/creatembphoto")
	public String createphoto(@RequestParam("photo") MultipartFile mf, Map<String, String> params,Principal principal, HttpServletRequest request, Model m,
			HttpSession session, @SessionAttribute Member member, Principal p)
			throws IllegalStateException, IOException {

		String pattern = "yyyy-MM-dd-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Random random = new Random();
		int rNumber = 10000 + random.nextInt(90000);
		//取得後綴
		String type = FilenameUtils.getExtension(mf.getOriginalFilename());
		if (type.isEmpty()) {
			return "no photo";
		}
		String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;

		String tempDir = resourceLoader.getResource("classpath:static/").getFile().toString()+"/memberphoto/";
	
		File tempDirFile = new File(tempDir);
		tempDirFile.mkdirs();

		String saveFilePath = tempDir + fileName;
		File saveFile = new File(saveFilePath);

		mf.transferTo(saveFile);
		
		Member mb = ut.getLoiginBean(session, principal);
		
		MemberInformation mbi = new MemberInformation();
		
		mbi.setPhoto("/SpecialTopic/memberphoto/" + fileName);
		mbi.setAddress(params.get("address"));
		mbi.setEmail(params.get("email"));
		mbi.setFullname(params.get("fullname"));
		mbi.setJob(params.get("job"));
		mbi.setPhone(params.get("phone"));
		mbi.setGender(Integer.parseInt(params.get("gender")));
		
		mb.setPassword(params.get("password"));
		m.addAttribute("mb", mb);
		
		
		return "member/memberInformation/";
	}
}