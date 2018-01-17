package com.github.pshcode.mycoinasset.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pshcode.mycoinasset.model.Ticker;

/**
 * @author SungHoon, Park
 */
@Service
public class CoinMarketCapApiService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	String tickerUrl = "https://api.coinmarketcap.com/v1/ticker/?convert=KRW";

	public Ticker[] getTickers() throws IOException {
		String json = restTemplate.getForObject(tickerUrl, String.class);
		Ticker[] tickers = objectMapper.readValue(json, Ticker[].class);
		Arrays.sort(tickers, Comparator.comparing(Ticker::getSymbol));

		return tickers;
	}
}
