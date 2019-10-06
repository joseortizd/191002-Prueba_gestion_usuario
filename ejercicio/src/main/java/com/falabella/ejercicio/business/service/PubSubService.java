package com.falabella.ejercicio.business.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.falabella.ejercicio.client.dto.ApiResponseDTO;
import com.falabella.ejercicio.client.dto.UserDTO;

@Service
public class PubSubService {	
	public void postMessageToApi(UserDTO userDTO) {
		createResponseEntity("/v1/users", userDTO);
		
	}
    private void createResponseEntity(String uri, UserDTO userDTO) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
        HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(userDTO, headers);
        restTemplate.exchange(
			createURLWithPort(uri),
			HttpMethod.POST, entity, ApiResponseDTO.class);
    }
    private String createURLWithPort(String uri) {
    	return "http://localhost:8080" + uri;
    }
}
