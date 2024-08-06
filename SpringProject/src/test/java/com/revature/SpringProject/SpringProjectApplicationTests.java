package com.revature.SpringProject;

import com.revature.SpringProject.controllers.AccountController;
import com.revature.SpringProject.controllers.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SpringProjectApplicationTests {

	@Autowired
	private AccountController accController;
	@Autowired
	private BookController bookController;

	@Test
	void contextLoads() {
		assertThat(accController).isNotNull();
		assertThat(bookController).isNotNull();
	}

}
