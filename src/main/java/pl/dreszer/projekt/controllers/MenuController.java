package pl.dreszer.projekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

	@RequestMapping("index.html")
	public String home()
	{
		return "index";
	}
	@RequestMapping("/")
	public String home2()
	{
		return "index";
	}
	@RequestMapping("uploadFile.html")
	public String file()
	{
		return "uploadFile";
	}
	//@GetMapping("register")
}
