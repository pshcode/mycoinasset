package com.github.pshcode.mycoinasset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author SungHoon, Park
 */
@Controller
public class TestController {
	@RequestMapping("test")
	@ResponseBody
	public String test() {
		return "TEST";
	}
}
