package com.github.pshcode.mycoinasset.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pshcode.mycoinasset.model.MyCoin;
import com.github.pshcode.mycoinasset.service.CoinMarketCapApiService;
import com.github.pshcode.mycoinasset.service.MyCoinService;

/**
 * @author SungHoon, Park
 */
@Controller
@RequestMapping("/mycoin")
public class MyCoinController {
	@Autowired
	private MyCoinService myAssetService;

	@Autowired
	private CoinMarketCapApiService coinMarketCapApiService;

	@GetMapping
	public String main(Model model) throws IOException {
		model.addAttribute("myCoins", myAssetService.getMyCoins());
		model.addAttribute("tickers", coinMarketCapApiService.getTickers());

		return "mycoin/main";
	}

	@PostMapping("/add")
	@ResponseBody
	public void add(MyCoin myCoin) {
		myAssetService.addMyCoin(myCoin);
	}

	@PostMapping("/modify/{symbol}")
	@ResponseBody
	public void modifyAmount(@PathVariable("symbol") String symbol, @RequestParam("amount") String amount) {
		myAssetService.modifyAmount(symbol, amount);
	}

	@DeleteMapping("/delete/{symbol}")
	@ResponseBody
	public void delete(@PathVariable("symbol") String symbol) {
		myAssetService.deleteMyCoin(symbol);
	}
}
