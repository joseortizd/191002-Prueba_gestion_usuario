package com.falabella.ejercicio.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.falabella.ejercicio.client.dto.ApiResponseDTO;
import com.falabella.ejercicio.cliente.publisher.publisher.PubsubOutboundGateway;

@RestController
@RequestMapping(value = "/v1/message")
public class PubsubController {
    @Autowired
    private PubsubOutboundGateway messagingGateway;
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO> publishMessage(@RequestBody String message) {
        messagingGateway.sendToPubsub(message);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setMessage("Success");
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }
}