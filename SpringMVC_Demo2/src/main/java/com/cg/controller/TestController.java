package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/root")
public class TestController {

	// url: http://localhost:8085/SpringMVC_Demo2/root/index

	@RequestMapping("/index")
	public String index() {
		System.out.println("index handler");
		return "index";
	}

	@ResponseBody
	@RequestMapping("/hello/{id}")
	public String helloUser(@PathVariable("id") String name) {
		return "Welcome " + name;
	}

	@RequestMapping("/studentForm")
	public ModelAndView getStudForm() {
		return new ModelAndView("studentForm");
	}

	@RequestMapping("/submitForm")
	public ModelAndView submitForm(@RequestParam("stuName") String stuName, @RequestParam("subject") String subject) {
		ModelAndView mv = new ModelAndView("submitForm");
		mv.addObject("studData", " --name: " + stuName + " --subject: " + subject);
		return mv;
	}
}
