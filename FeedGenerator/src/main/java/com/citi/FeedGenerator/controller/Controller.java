package com.citi.FeedGenerator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller
public class Controller {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
