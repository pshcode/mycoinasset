package com.github.pshcode.mycoinasset.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pshcode.mycoinasset.model.CoinInfo;

/**
 * @author SungHoon, Park
 */
@Service
public class CoinMarketCapApiService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private final static String COIN_INFO_URL = "https://api.coinmarketcap.com/v1/ticker/%s/?convert=KRW";

	public CoinInfo getCurrentCoinInfo(String id) throws IOException {
		String coinUrl = String.format(COIN_INFO_URL, id);
		String json = restTemplate.getForObject(coinUrl, String.class);

		CoinInfo[] coinInfos = objectMapper.readValue(json, CoinInfo[].class);

		return coinInfos.length == 1 ? coinInfos[0] : null;
	}
}
