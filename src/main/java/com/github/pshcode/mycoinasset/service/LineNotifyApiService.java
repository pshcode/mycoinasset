package com.github.pshcode.mycoinasset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author SungHoon, Park
 */
@Service
public class LineNotifyApiService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public boolean notifyDailyReport() {
		return false;
	}
}
