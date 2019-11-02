package com.example.demo.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello
{
	@RequestMapping("/lll")
	public String hello() {
		return "hello";
	}
}
