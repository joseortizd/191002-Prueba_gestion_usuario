/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falabella.ejercicio.client.controller;

import com.falabella.ejercicio.business.service.UserService;
import com.falabella.ejercicio.client.dto.ApiResponseDTO;
import com.falabella.ejercicio.client.dto.UserDTO;
import com.falabella.ejercicio.client.exception.UserException;
import io.micrometer.core.lang.Nullable;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author joseortiz
 */
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
        @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created ", response = ApiResponseDTO.class),
        @ApiResponse(code = 400, message = "Bad Request")})
        @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        ResponseEntity<ApiResponseDTO> create(@Nullable @RequestBody UserDTO userDTO) {
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            try {
                this.userService.create(userDTO);
                apiResponseDTO.setMessage("Created");
                return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
            } catch (UserException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                apiResponseDTO.setMessage(ex.getMessage());
                return new ResponseEntity<>(apiResponseDTO, HttpStatus.BAD_REQUEST);
            }
        }

}
