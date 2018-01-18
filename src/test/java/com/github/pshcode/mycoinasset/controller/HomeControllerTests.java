package com.github.pshcode.mycoinasset.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pshcode.mycoinasset.config.MyCoinAssetApplication;
import com.github.pshcode.mycoinasset.model.CoinInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyCoinAssetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HomeControllerTests {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testApiConnection() throws Exception {
		String baseUrl = "https://api.coinmarketcap.com/v1/ticker/?convert=KRW&limit=10";

		URI uri = URI.create(baseUrl);
		String result = restTemplate.getForObject(uri, String.class);

		CoinInfo[] tickers = objectMapper.readValue(result, CoinInfo[].class);

		System.out.println(tickers);
	}
}