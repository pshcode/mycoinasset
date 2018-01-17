package com.github.pshcode.mycoinasset.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pshcode.mycoinasset.service.CoinMarketCapApiService;

/**
 * @author SungHoon, Park
 */
@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private CoinMarketCapApiService coinMarketCapApiService;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) throws IOException {

		return "home";
	}
}
