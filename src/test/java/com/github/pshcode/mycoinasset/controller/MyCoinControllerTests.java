package com.github.pshcode.mycoinasset.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pshcode.mycoinasset.config.MyCoinAssetApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyCoinAssetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MyCoinControllerTests {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void bigDecimalConverting() throws Exception {
		String input = "0.0024302100";
		BigDecimal amount = new BigDecimal(input);

		assertEquals("0.0024302100", amount.toString());
	}

	@Test
	public void main() throws Exception {
		mockMvc.perform(get("/mycoin"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("myCoins"));
	}

	@Test
	public void add() throws Exception {
		mockMvc.perform(post("/mycoin/add")
			.param("id", "tron")
			.param("symbol", "TRX")
			.param("name", "Tron")
			.param("amount", "45000.214501")
		).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void modifyAmount() throws Exception {
		mockMvc.perform(post("/mycoin/modify/tron")
			.param("amount", "111.214501")
		).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void deleteMyCoin() throws Exception {
		mockMvc.perform(delete("/mycoin/delete/tron"))
			.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/mycoin/test"));
	}
}