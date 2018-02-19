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
	private String id;
	private String coinName;
	private String amount;
	private String useYn;
	private String remark;
	private String createYmdt;
}
