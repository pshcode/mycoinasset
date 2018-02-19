package com.github.pshcode.mycoinasset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.github.pshcode.mycoinasset.model.MyCoinHistory;

/**
 * @author SungHoon, Park
 */
@Repository
@Mapper
public interface MyCoinHistoryMapper {
	List<MyCoinHistory> selectMyCoinHistoryList(String searchYmd);

	int selectNowHistorySeq();

	void insertMyCoinHistory(MyCoinHistory myCoinHistory);
}
