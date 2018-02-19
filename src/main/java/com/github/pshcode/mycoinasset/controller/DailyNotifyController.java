package com.github.pshcode.mycoinasset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pshcode.mycoinasset.api.LineNotifyApiService;
import com.github.pshcode.mycoinasset.model.MyCoinHistory;
import com.github.pshcode.mycoinasset.service.MyCoinHistoryService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SungHoon, Park
 */
@Controller
@Slf4j
public class DailyNotifyController {
	@Autowired
	private MyCoinHistoryService myCoinHistoryService;

	@Autowired
	private LineNotifyApiService lineNotifyApiService;

	@RequestMapping("/dailyNotify")
	@ResponseBody
	@Scheduled(cron = "0 0 21 * * *")
	public String dailyNotify() {
		myCoinHistoryService.addAllMyCoinHistory();

		List<MyCoinHistory> myCoinHistoryList = myCoinHistoryService.getMyCoinHistoryList();
		lineNotifyApiService.notifyMessage(myCoinHistoryList);

		return "OK";
	}
}
