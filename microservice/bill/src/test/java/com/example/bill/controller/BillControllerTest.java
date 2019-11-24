package com.example.bill.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.annotation.Rollback;
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

public class BillControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;

	
	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetBillGeneral() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/bill/general/17")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON)) //执行请求  
        		.andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON))  
        		.andExpect(jsonPath("$.groupId").value(0))
        		.andExpect(jsonPath("$.date").value(20150101))
        		.andExpect(jsonPath("$.amount").value(150.0))
        		.andExpect(jsonPath("$.billName").value("s"))
        		.andExpect(jsonPath("$.payerId").value(0))
        		.andExpect(jsonPath("$.billId").value(17))
        		.andExpect(jsonPath("$.addState").value(1));
	}

	@Test
	public void testGetBill() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/bill/7")
	            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().json("{\"groupId\":0,\"date\":20190302,\"amount\":50.0,\"billName\":\"food\",\"description\":\"lot sdad here\",\"receiptImg\":\"my hhhh\",\"usersList\":[1,3,4],\"payerId\":3,\"billId\":7,\"addState\":0}"))
	            .andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Transactional
	public void testCreateBill() throws Exception {
		
		String requestBody = "{\"groupId\":12,\"date\":20191202,\"amount\":114.0,\"billName\":\"food\",\"description\":\"lot sdad here\",\"receiptImg\":\"mydd\",\"usersList\":[1,3,4],\"payerId\":3}";  
	    mockMvc.perform(post("/bill")  
	            .contentType(MediaType.APPLICATION_JSON).content(requestBody))  
	            .andDo(print())
	            .andExpect(status().isOk());
	}
  
	@Test
	public void testGetTotalAddState() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/bill/add/17/3")
	            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().string("1"))
	            .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@Transactional
	public void testBillAddComfirm() throws Exception {
		mockMvc.perform(post("/bill/add/19/3") ) 
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(MockMvcResultMatchers.content().string("-1"));
	}

	@Test
	@Transactional
	public void testGetUserAddComfirm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/bill/add/19/3") ) 
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("1"));
	}

	@Test
	@Transactional
	public void testCancelAddComfirm() throws Exception {
		mockMvc.perform(post("/bill/add/19/3") ) 
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("-1"));
	}

	@Test
	public void testGetUserCancelAdd() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/bill/cancel/19") ) 
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string("1"));
	}

}
