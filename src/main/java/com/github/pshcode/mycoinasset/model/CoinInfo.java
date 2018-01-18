package com.github.pshcode.mycoinasset.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author SungHoon, Park
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinInfo implements Comparable {
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("symbol")
	private String symbol;
	@JsonProperty("price_krw")
	private String priceKrw;
	@JsonProperty("percent_change_24h")
	private String percentChange24h;

	@Override
	public int compareTo(Object obj) {
		CoinInfo coinInfo = (CoinInfo)obj;

		return coinInfo.getId().compareTo(getId());
	}
}
