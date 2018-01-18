package com.github.pshcode.mycoinasset.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pshcode.mycoinasset.api.CoinMarketCapApiService;
import com.github.pshcode.mycoinasset.mapper.MyCoinHistoryMapper;
import com.github.pshcode.mycoinasset.model.CoinInfo;
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

	public List<MyCoinHistory> getMyCoinHistories(String createDate) {
		return myCoinHistoryMapper.selectMyCoinHistories(createDate);
	}

	@Transactional
	public List<MyCoinHistory> addTodayMyCoinHistories() {
		List<MyCoin> myCoins = myCoinService.getMyCoins();
		List<MyCoinHistory> myCoinHistories = new ArrayList<>();

		for (MyCoin myCoin : myCoins) {
			try {
				CoinInfo coinInfo = coinMarketCapApiService.getCurrentCoinInfo(myCoin.getId());

				if (coinInfo != null) {
					MyCoinHistory myCoinHistory = makeMyCoinHistory(myCoin, coinInfo);
					myCoinHistoryMapper.insertMyCoinHistory(myCoinHistory);
					myCoinHistories.add(myCoinHistory);
				}
			} catch (Exception e) {
				log.error("coinMarketCap Api failed. coin=" + myCoin, e);
			}
		}

		return myCoinHistories;
	}

	private MyCoinHistory makeMyCoinHistory(MyCoin myCoin, CoinInfo coinInfo) {
		BigDecimal amount = new BigDecimal(myCoin.getAmount());
		BigDecimal price = new BigDecimal(coinInfo.getPriceKrw());
		BigDecimal sumPrice = amount.multiply(price);

		MyCoinHistory myCoinHistory = new MyCoinHistory();
		myCoinHistory.setId(myCoin.getId());
		myCoinHistory.setAmount(myCoin.getAmount());
		myCoinHistory.setPrice(price.toString());
		myCoinHistory.setSumPrice(sumPrice.toString());

		return myCoinHistory;
	}
}
