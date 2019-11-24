package com.example.bill.service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.bill.UserdemoApplication;
import com.example.bill.model.Group;
import com.example.bill.service.GroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserdemoApplication.class)
public class GroupServiceTest {

	@Autowired
	private GroupService groupService;
	private static Group group;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		group = new Group(666, "test2");
		int [] usersList = new int[] {1,2,3};
		group.setUsersList(usersList);
	}

	@Test
	public void testGroupService() {
		fail("Not yet implemented");
	}

	@Test
	@Transactional     //rollback
	public void testCreateGroup() throws Exception {
		assertEquals(0, groupService.createGroup(group));
	}

	@Test
	@Transactional
	public void testAddGroupMember() {
		int [] usersList = new int[] {5,6,7};
		assertEquals(1, groupService.addGroupMember(3,usersList));
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

	@Test
	public void testGetIndivitualTotalBalance() {
		fail("Not yet implemented");
	}

}
