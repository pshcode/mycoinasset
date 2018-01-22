package com.github.pshcode.mycoinasset.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pshcode.mycoinasset.api.LineNotifyApiService;
import com.github.pshcode.mycoinasset.model.MyCoinHistory;

/**
 * @author SungHoon, Park
 */
@Service
public class MyCoinDailySumPriceService {
	@Autowired
	private MyCoinHistoryService myCoinHistoryService;

	@Autowired
	private LineNotifyApiService lineNotifyApiService;

	@Transactional
	public void myCoinDailySumPrice() {
		List<MyCoinHistory> myCoinHistories = myCoinHistoryService.addTodayMyCoinHistories();
		lineNotifyApiService.notifyMessage(myCoinHistories);
	}
}
