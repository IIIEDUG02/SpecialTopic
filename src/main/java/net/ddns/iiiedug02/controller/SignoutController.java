package net.ddns.iiiedug02.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class SignoutController {

	@GetMapping("/signout/success")
	public String signout() {
	    return "redirect:/";
	}//RETURN "redirect:xxxx";
}
