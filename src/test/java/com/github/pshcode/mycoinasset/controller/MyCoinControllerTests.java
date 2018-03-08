package com.github.pshcode.mycoinasset.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pshcode.mycoinasset.config.MyCoinAssetApplication;

@Ignore
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
}