package com.github.pshcode.mycoinasset.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pshcode.mycoinasset.api.CoinMarketCapApiService;
import com.github.pshcode.mycoinasset.mapper.MyCoinHistoryMapper;
import com.github.pshcode.mycoinasset.model.MyCoin;
import com.github.pshcode.mycoinasset.model.MyCoinHistory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SungHoon, Park
 */
@Service
@Slf4j
public class MyCoinHistoryService {
	@Autowired
	private MyCoinService myCoinService;

	@Autowired
	private MyCoinHistoryMapper myCoinHistoryMapper;

	@Autowired
	private CoinMarketCapApiService coinMarketCapApiService;

	public List<MyCoinHistory> getMyCoinHistoryList() {
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		return myCoinHistoryMapper.selectMyCoinHistoryList(today);
	}

	@Transactional
	public void addAllMyCoinHistory() {
		List<MyCoin> myCoinList = myCoinService.getMyCoinList();
		int seq = myCoinHistoryMapper.selectNowHistorySeq();

		for (MyCoin myCoin : myCoinList) {
			String priceKrw = "";

			try {
				priceKrw = coinMarketCapApiService.getCurrentCoinPriceKrw(myCoin.getId());
			} catch (Exception e) {
				log.error("coinMarKetCapApi failed. myCoin=" + myCoin, e);
			}

			if (StringUtils.isNotEmpty(priceKrw)) {
				MyCoinHistory myCoinHistory = new MyCoinHistory(myCoin, priceKrw);
				myCoinHistory.setSeq(seq);

				myCoinHistoryMapper.insertMyCoinHistory(myCoinHistory);
			}
		}
	}
}
