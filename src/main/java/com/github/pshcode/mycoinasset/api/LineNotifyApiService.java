package com.github.pshcode.mycoinasset.api;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.github.pshcode.mycoinasset.model.MyCoinHistory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SungHoon, Park
 */
@Service
@Slf4j
public class LineNotifyApiService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NumberFormat priceNumberFormat;

	@Value("#{commonProps['line.notify.url']}")
	private String lineNotifyUrl;

	@Value("#{commonProps['line.notify.token']}")
	private String lineNotifyToken;

	public void notifyMessage(List<MyCoinHistory> myCoinHistories) {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("message", makeMessage(myCoinHistories));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + lineNotifyToken);

		HttpEntity<Object> httpEntity = new HttpEntity<>(multiValueMap, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(lineNotifyUrl, HttpMethod.POST, httpEntity, String.class);

		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			log.error("lineNotify failed.");
		}
	}

	private String makeMessage(List<MyCoinHistory> myCoinHistoryList) {
		StringBuilder messageBuilder = new StringBuilder();

		for (MyCoinHistory myCoinHistory : myCoinHistoryList) {
			double price = Double.parseDouble(myCoinHistory.getPrice());
			double totalPrice = Double.parseDouble(myCoinHistory.getTotalPrice());

			messageBuilder.append("\n");
			messageBuilder.append("[").append(myCoinHistory.getId()).append("]");
			messageBuilder.append("\n");
			messageBuilder.append("설명: ").append(myCoinHistory.getRemark());
			messageBuilder.append("\n");
			messageBuilder.append("수량: ").append(myCoinHistory.getAmount());
			messageBuilder.append("\n");
			messageBuilder.append("가격: ").append(priceNumberFormat.format(price)).append("원");
			messageBuilder.append("\n");
			messageBuilder.append("합계: ").append(priceNumberFormat.format(totalPrice)).append("원");
			messageBuilder.append("\n");
		}

		return messageBuilder.toString();
	}
}
