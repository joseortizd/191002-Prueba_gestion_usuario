/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falabella.ejercicio.business.util;

import com.falabella.ejercicio.client.dto.UserDTO;
import com.falabella.ejercicio.data.entity.UserEntity;
import java.util.Date;

/**
 *
 * @author joseortiz
 */
public class ConverterUser {
    
    public static UserEntity userDtoToEntity (UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setDocument(userDTO.getDocument());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setName(userDTO.getName());
        userEntity.setCreatedAt(new Date());
        userEntity.setUpdatedAT(new Date());
        return userEntity;
    }
}
