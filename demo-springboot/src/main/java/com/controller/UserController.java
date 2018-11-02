package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @EnableAutoConfiguration
// @ComponentScan
@SpringBootApplication
public class UserController {

	@RequestMapping(value = "/user.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getUser() {
		System.out.println("Spring boot");
	}

	@RequestMapping(value = "/hello", method = { RequestMethod.POST, RequestMethod.GET })
	public String getStr(Model m) {
		m.addAttribute("now", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()));
		return "Hello,Spring boot!!! ";
	}

	public static void main(String[] args) {
		SpringApplication.run(UserController.class);
	}
}
