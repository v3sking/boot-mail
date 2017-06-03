package com.lhy.boot.web;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {
	
	@RequestMapping("")
	public String index(ModelMap map,HttpSession session) {
		System.out.println("...................");
		session.setAttribute("user", "123");
		map.put("attr", "<a href='#'>hello world 2017!</a>");
		
		map.put("today", Calendar.getInstance());
		return "index";
		
	}
}
