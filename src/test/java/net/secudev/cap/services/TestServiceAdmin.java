package net.secudev.cap.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import net.secudev.cap.application.IAdminService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceAdmin {

	@Autowired
	IAdminService adminService;

	@Test
	public void testEvent() {
		adminService.newUserCreated("New User Created");
	}

}
