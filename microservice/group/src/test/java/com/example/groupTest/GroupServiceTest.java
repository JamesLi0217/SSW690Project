package com.example.groupTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.UserdemoApplication;
import com.example.demo.dao.impl.GroupDaoImpl;

/*
 * spring boot test class
 * @RunWith: JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
 * @SpringJUnit4ClassRunner: let junit combine with springboot
 * @SpringBootTest(classes = {UserdemoApplication.class})   : current application
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={UserdemoApplication.class})
public class GroupServiceTest {
	
	@Autowired
	private GroupDaoImpl groupDaoImpl;
	
	@Test
	public void testcreateGroup() {
		this.groupDaoImpl.createGroup(group);
	}
}
