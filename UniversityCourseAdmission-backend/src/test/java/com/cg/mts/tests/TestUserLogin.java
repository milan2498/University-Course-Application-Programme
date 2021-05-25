package com.cg.mts.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;



@SpringBootTest
class TestUserLogin {

	/*
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	@Test
	public void saveUserTest() {
		User user = new User("Paul","pass@707","admin",true);
		User ret = repository.save(user);
		assertSame(user, ret);
	}
	
	@Test
	public void userLoginTest() {
		UserModel userModel = new UserModel("root", "pass@123");
		assertTrue(service.signIn(userModel));
	}
	
	@Test
	public void loginWithInvalidPasswordTest() {
		UserModel userModel = new UserModel("root", "pass@651");
		assertFalse(service.signIn(userModel));
	}
	
	@Test
	public void loginWithInvalidUserNameTest() {
		UserModel userModel = new UserModel("invalidName", "pass@354");
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
	        service.signIn(userModel);
	    });
		
		assertNotNull(exception);
	}
	
	@Test
	public void logoutWithInvalidUserNameTest() {
		
		String userName = "invalidName";
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			service.logOut(userName);
		});
		
		assertNotNull(exception);
		
	}
	
	@Test
	public void logoutTest() {
		String userName = "root";
		assertTrue(service.logOut(userName));
	}
	*/

}
