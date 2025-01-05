package com.home.test.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.home.test.service.ExcelService;

@Controller
public class MainController {
	
	@Autowired ExcelService service;
	
	
	
	@GetMapping("main")
	public String mainPage() {
		System.out.println("test");
		return "home";
	}
	@GetMapping("map")
	public String mapPage() {
		System.out.println("test");
		return "page/map";
	}
	@GetMapping("excel")
	public String excelPage() {
		System.out.println("test");
		return "page/excel";
	}
	@GetMapping("excelDownload")
	public void excelDownload(HttpServletResponse res) {
		service.excelDownload(res, service.sampleData());
	}
}
