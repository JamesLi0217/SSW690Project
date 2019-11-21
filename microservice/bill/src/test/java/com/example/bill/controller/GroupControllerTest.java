package com.example.bill.controller;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.PageAttributes.MediaType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GroupControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	/*
	@Test
	public void testHello() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	            .param("name","Tom"))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
		        .andDo(MockMvcResultHandlers.print());
	}
	*/

	@Test
	public void testGetGroup() throws Exception{
		String requestBody = "{\"usersList\":[3,13157],\"groupName\":\"kayyy\",\"groupId\":3,\"totalAmount\":5.0,\"checkStateId\":0,\"billsList\":[]}";  
	  
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group/3")
	            .contentType(org.springframework.http.MediaType.APPLICATION_JSON).content(requestBody)  
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON)) //执行请求  
	            .andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON))  
	            .andExpect(jsonPath("$.groupId").value(3)); 
	   
	}

	@Test
	public void testGetGroupName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group/name/3")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("kayyy"))
		        .andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCheckout() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.
				post("/group/checkout/9")
		        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
		        .andDo(print())
		        .andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void testgetIndivitualTotalBalance() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group/balance/3/3")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("0.0"))
		        .andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCheckoutComfirm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group/checkout/3/3")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("0"))
		        .andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCancelCheckoutComfirm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.
				post("/group/checkout/cancel/9/6")
		        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("2"))
		        .andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetUserCancelCheckout() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group/checkout/cancel/9")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("6"))
		        .andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetTotalCheckoutState() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group/checkout/3")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("0"))
		        .andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetUserCheckoutComfirm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group/checkout/9/6")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("0"))
		        .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testgetTrasfer() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/group/checkout/trans/9/6")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("{}"))
		        .andDo(MockMvcResultHandlers.print());
	}

}
