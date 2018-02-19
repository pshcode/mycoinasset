package com.github.pshcode.mycoinasset.model;

import java.math.BigDecimal;

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
	private String createYmd;
	private int seq;
	private String id;
	private String amount;
	private String price;
	private String totalPrice;
	private String coinName;
	private String remark;

	public MyCoinHistory() {
	}

	public MyCoinHistory(MyCoin myCoin, String priceKrw) {
		BigDecimal amountBD = new BigDecimal(myCoin.getAmount());
		BigDecimal price = new BigDecimal(priceKrw);
		BigDecimal totalPrice = amountBD.multiply(price);

		this.id = myCoin.getId();
		this.amount = myCoin.getAmount();
		this.price = price.toString();
		this.totalPrice = totalPrice.toString();
	}
}
