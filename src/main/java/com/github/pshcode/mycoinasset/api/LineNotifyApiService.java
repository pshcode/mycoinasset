package com.github.pshcode.mycoinasset.api;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.github.pshcode.mycoinasset.model.MyCoinHistory;

/**
 * @author SungHoon, Park
 */
@Service
public class LineNotifyApiService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NumberFormat krwNumberFormat;

	@Value("#{commonProps['line.notify.url']}")
	private String lineNotifyUrl;

	@Value("#{commonProps['line.notify.token']}")
	private String lineNotifyToken;

	public boolean notifyMessage(List<MyCoinHistory> myCoinHistories) {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("message", makeMessage(myCoinHistories));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + lineNotifyToken);

		HttpEntity<Object> httpEntity = new HttpEntity<>(multiValueMap, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(lineNotifyUrl, HttpMethod.POST, httpEntity, String.class);

		return false;
	}

	private String makeMessage(List<MyCoinHistory> myCoinHistories) {
		StringBuilder messageBUilder = new StringBuilder();

		for (MyCoinHistory myCoinHistory : myCoinHistories) {
			double price = Double.parseDouble(myCoinHistory.getPrice());
			double sumPrice = Double.parseDouble(myCoinHistory.getSumPrice());

			messageBUilder.append("\n");
			messageBUilder.append("[").append(myCoinHistory.getId()).append("]");
			messageBUilder.append("\n");
			messageBUilder.append("수량: ").append(myCoinHistory.getAmount());
			messageBUilder.append("\n");
			messageBUilder.append("가격: ").append(krwNumberFormat.format(price)).append("원");
			messageBUilder.append("\n");
			messageBUilder.append("합계: ").append(krwNumberFormat.format(sumPrice)).append("원");
			messageBUilder.append("\n");
		}

		return messageBUilder.toString();
	}
}
