package com.github.pshcode.mycoinasset.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SungHoon, Park
 */
@Alias("myCoinHistory")
@Getter
@Setter
public class MyCoinHistory {
	private String createDate;
	private String id;
	private String amount;
	private String price;
	private String sumPrice;
}
