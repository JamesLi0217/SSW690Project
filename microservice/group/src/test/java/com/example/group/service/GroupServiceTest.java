package com.example.group.service;

import static org.junit.Assert.*;

import java.util.ServiceConfigurationError;

import javax.transaction.Transactional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.group.UserdemoApplication;
import com.example.group.model.Group;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserdemoApplication.class)
public class GroupServiceTest {
	@Autowired
	private GroupService groupService;
	private static Group group;
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		group = new Group(666, "test");
		int [] usersList = new int[] {1,2,3};
		group.setUsersList(usersList);
	}

	@Test
	public void testGroupService() {
		fail("Not yet implemented");
	}

	@Test
	@Transactional      // 自动滚回不往数据库中添加新的东西
	public void testCreateGroup() throws Exception {
		assertEquals(1, groupService.createGroup(group));
	}

	@Test
	public void testGetGroupName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddGroupMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckoutComfirm() {
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

	@Test
	public void testCheckout() {
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

}
