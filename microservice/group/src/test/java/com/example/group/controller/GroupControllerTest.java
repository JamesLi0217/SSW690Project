package com.example.group.controller;

import static org.junit.Assert.fail;

import java.awt.PageAttributes.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
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

	@Test
	public void testCreateGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGroup() {
		fail("Not yet implemented");
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
	public void testAddGroupMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckout() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckoutComfirm() {
		fail("Not yet implemented");
	}

	@Test
	public void testCancelCheckoutComfirm() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserCancelCheckout() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalCheckoutState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserCheckoutComfirm() {
		fail("Not yet implemented");
	}

}
