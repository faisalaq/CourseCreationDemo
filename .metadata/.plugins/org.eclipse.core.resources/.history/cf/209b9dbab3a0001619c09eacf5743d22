package com.coursecreation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {

	@RequestMapping("/")
	public String root(){
		
		return "redirect:/courses";
	}
	
	@RequestMapping("courses")
	public String  courses(){
		
		return "courses";
	}
	
}
