package com.github.pshcode.mycoinasset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.github.pshcode.mycoinasset.model.MyCoin;

/**
 * @author SungHoon, Park
 */
@Repository
@Mapper
public interface MyCoinMapper {
	List<MyCoin> selectMyCoins();

	void insertMyCoin(MyCoin myCoin);

	int updateAmount(MyCoin myCoin);

	int deletMyCoin(String symbol);
}
