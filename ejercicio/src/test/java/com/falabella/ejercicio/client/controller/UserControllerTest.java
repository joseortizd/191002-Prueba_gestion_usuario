/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falabella.ejercicio.client.controller;

import com.falabella.ejercicio.EjercicioApplication;
import com.falabella.ejercicio.client.dto.UserDTO;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author joseortiz
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = EjercicioApplication.class, 
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        )
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        
	@Test
	public void testAcreateUserRightFields() throws Exception {
		HttpEntity<UserDTO> entity = new HttpEntity<>(createUserDTO(), headers);
		ResponseEntity<String> response = createResponseEntity("/users");
                assertTrue("Body responses contains Created",response.getBody().contains("Created"));
                assertTrue("Status code is 201", response.getStatusCodeValue() == 201);
	}
  	@Test
	public void testBcreateExistingUser() throws Exception {
		HttpEntity<UserDTO> entity = new HttpEntity<>(createUserDTO(), headers);
		ResponseEntity<String> response = createResponseEntity("/users");
                assertTrue("Body responses contains Bad Request",response.getBody().contains("Bad Request"));
                assertTrue("Status code is 400", response.getStatusCodeValue() == 400);
	}      
        @Test
        public void testCcreateUserWithoutFullFields() throws Exception {
            	HttpEntity<UserDTO> entity = new HttpEntity<>(createUncompleteUserDTO(), headers);
		ResponseEntity<String> response = createResponseEntity("/users");
		assertTrue("Body responses contains Bad Request",response.getBody().contains("Bad Request"));
                assertTrue("Status code is 400", response.getStatusCodeValue() == 400);
        }

        private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

        private ResponseEntity<String> createResponseEntity(String uri) {
            HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(createUserDTO(), headers);
            ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort(uri),
				HttpMethod.POST, entity, String.class);
            return response;
        }
                
        private UserDTO createUserDTO() {
            UserDTO user = new UserDTO();
            user.setAddress("Avenida 15");
            user.setDocument("2020");
            user.setEmail("ortizdjose@gmail.com");
            user.setLastName("Ortiz");
            user.setName("Jose");
            user.setPhoneNumber("9512323112");
            return user;
        }
        private UserDTO createUncompleteUserDTO() {
            UserDTO user = new UserDTO();
            user.setAddress("Avenida 15");
            user.setPhoneNumber("9512323112");
            return user;
        }
}
