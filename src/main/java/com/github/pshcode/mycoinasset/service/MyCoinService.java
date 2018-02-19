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

	public List<MyCoin> getMyCoinList() {
		List<MyCoin> myCoinList = myCoinMapper.selectMyCoinList();

		for (MyCoin myCoin : myCoinList) {
			String amount = StringUtils.stripEnd(myCoin.getAmount(), "0");
			myCoin.setAmount(amount);
		}

		return myCoinList;
	}
}
