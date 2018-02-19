package com.github.pshcode.mycoinasset.api;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pshcode.mycoinasset.model.CoinMarketCapApiResult;

/**
 * @author SungHoon, Park
 */
@Service
public class CoinMarketCapApiService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("#{commonProps['coinmarketcap.url']}")
	private String coinMarketCapUrl;

	public String getCurrentCoinPriceKrw(String id) throws IOException {
		String apiUrl = String.format(coinMarketCapUrl, id);
		String json = restTemplate.getForObject(apiUrl, String.class);

		CoinMarketCapApiResult[] coinMarketCapApiResults = objectMapper.readValue(json, CoinMarketCapApiResult[].class);

		if (ArrayUtils.isEmpty(coinMarketCapApiResults)) {
			return "";
		}

		return coinMarketCapApiResults[0].getPriceKrw();
	}
}
