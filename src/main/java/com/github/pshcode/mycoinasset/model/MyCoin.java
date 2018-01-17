package com.github.pshcode.mycoinasset.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SungHoon, Park
 */
@Alias("myCoin")
@Getter
@Setter
public class MyCoin {
	private String symbol;
	private String name;
	private String amount;
	private String createDate;
	private String useYn;
}
