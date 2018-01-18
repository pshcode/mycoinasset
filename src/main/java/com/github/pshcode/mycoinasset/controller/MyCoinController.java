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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pshcode.mycoinasset.model.MyCoin;
import com.github.pshcode.mycoinasset.service.DailyMyCoinRecordService;
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
	private DailyMyCoinRecordService myCoinRecordService;

	@GetMapping
	public String main(Model model) throws IOException {
		model.addAttribute("myCoins", myAssetService.getMyCoins());

		return "mycoin/main";
	}

	@PostMapping("/add")
	@ResponseBody
	public void add(MyCoin myCoin) {
		myAssetService.addMyCoin(myCoin);
	}

	@PostMapping("/modify")
	@ResponseBody
	public void modify(MyCoin myCoin) {
		myAssetService.modifyMyCoin(myCoin);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") String id) {
		myAssetService.deleteMyCoin(id);
	}
}
