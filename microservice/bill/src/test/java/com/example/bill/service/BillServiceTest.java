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

class BillServiceTest {

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
	void testBillService() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBillGeneral() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateBill() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBill() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTotalAddState() {
		fail("Not yet implemented");
	}

	@Test
	void testBillAddComfirm() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUserAddComfirm() {
		fail("Not yet implemented");
	}

	@Test
	void testCancelAddComfirm() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUserCancelAdd() {
		fail("Not yet implemented");
	}

}
