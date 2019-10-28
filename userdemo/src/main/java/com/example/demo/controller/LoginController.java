package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String init(Model model) {
		model.addAttribute("msg", "Please Enter Your Login Details");
		return "This is login page";
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public String submit(Model model, @ModelAttribute("loginBean") LoginBean loginBean) {
//		if (loginBean != null && loginBean.getUserName() != null & loginBean.getPassword() != null) {
//			if (loginBean.getUserName().equals("chandra") && loginBean.getPassword().equals("chandra123")) {
//				model.addAttribute("msg", loginBean.getUserName());
//				return "success";
//			} else {
//				model.addAttribute("error", "Invalid Details");
//				return "login";
//			}
//		} else {
//			model.addAttribute("error", "Please enter Details");
//			return "login";
//		}
//	}
}
