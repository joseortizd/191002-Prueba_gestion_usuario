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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/v1/users")
public class UserController {
    
    @Autowired
    private UserService userService;

        @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
        
        @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        ResponseEntity<ApiResponseDTO> getAll() {
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            try {
                List<UserDTO> usuarioDTOs = this.userService.findAll();
                apiResponseDTO.setMessage("Success");
                apiResponseDTO.setData(usuarioDTOs);
                return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
            } catch (UserException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                apiResponseDTO.setMessage(ex.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        
        
    @RequestMapping(value = "/{document}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> getOne(@PathVariable("document") String document) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        try {
            UserDTO usuarioDTO = this.userService.findOneByDocument(document);
            apiResponseDTO.setMessage("Success");
            apiResponseDTO.setData(usuarioDTO);
            return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
        } catch (UserException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            apiResponseDTO.setMessage(ex.getMessage()); 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
