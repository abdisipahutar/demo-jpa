package com.uangteman;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.uangteman.entity.Category;
import com.uangteman.repo.CategoryRepo;
import com.uangteman.service.Matematika;
import com.uangteman.service.UserService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoJpaApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoJpaApplicationTests {

	@Autowired
	private Matematika mtk;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	int port;
	
	@Test
	public void testGetCategory() {
		ResponseEntity<ArrayList> entity = this.testRestTemplate
				.getForEntity("http://localhost:"+this.port+"/category", ArrayList.class);
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
	
	@Test
	public void contextLoads() {
		Assert.assertEquals(20, mtk.add(10,10));
	}
	
	
	@Test
	public void loginTest() throws Exception {
		String email = "abdi@gmail.com";
		String password = "5f4dcc3b5aa765d61d8327deb882cf99";
		Assert.assertNotNull(userService.login(email, password));
	}
	
	@Test
	public void categoryTest() {
		Category category = new Category();
		category.setName("Sample");
		
		Category result = categoryRepo.save(category);
		Assert.assertNotNull(result);
		
		result.setName("Sample Update");
		result = categoryRepo.save(result);
		Assert.assertEquals("Sample Update", result.getName());
		
		categoryRepo.delete(result);
		Assert.assertEquals(null, categoryRepo.findOne(result.getId()));
	}

}
