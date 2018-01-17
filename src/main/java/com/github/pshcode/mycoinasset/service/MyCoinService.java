package com.github.pshcode.mycoinasset.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pshcode.mycoinasset.mapper.MyCoinMapper;
import com.github.pshcode.mycoinasset.model.MyCoin;

/**
 * @author SungHoon, Park
 */
@Service
public class MyCoinService {
	@Autowired
	private MyCoinMapper myCoinMapper;

	public List<MyCoin> getMyCoins() {
		List<MyCoin> myCoins = myCoinMapper.selectMyCoins();

		for (MyCoin myCoin : myCoins) {
			String amount = StringUtils.stripEnd(myCoin.getAmount(), "0");
			myCoin.setAmount(amount);
		}

		return myCoins;
	}

	public void addMyCoin(MyCoin myCoin) {
		myCoinMapper.insertMyCoin(myCoin);
	}

	public int modifyAmount(String symbol, String amount) {
		MyCoin myCoin = new MyCoin();
		myCoin.setSymbol(StringUtils.upperCase(symbol));
		myCoin.setAmount(amount);

		return myCoinMapper.updateAmount(myCoin);
	}

	public int deleteMyCoin(String symbol) {
		return myCoinMapper.deletMyCoin(symbol);
	}
}
