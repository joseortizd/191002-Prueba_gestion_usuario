/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falabella.ejercicio.business.service;

import com.falabella.ejercicio.business.util.ConverterUser;
import com.falabella.ejercicio.client.dto.UserDTO;
import com.falabella.ejercicio.client.exception.UserException;
import com.falabella.ejercicio.data.entity.UserEntity;
import com.falabella.ejercicio.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joseortiz
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
   
    public UserDTO create(UserDTO userDTO) throws UserException{
        if (userDTO == null) {
            throw new UserException("userDTO is null");
        }
        if (userDTO.getAddress() == null || userDTO.getDocument() == null || 
            userDTO.getEmail() == null || userDTO.getLastName() == null || 
            userDTO.getName() == null || userDTO.getPhoneNumber() == null) {
            throw new UserException("userDTO needs all fields");
        }
        UserEntity userEntity = ConverterUser.userDtoToEntity(userDTO);
        this.userRepository.save(userEntity);
        return userDTO; 
    }
}
