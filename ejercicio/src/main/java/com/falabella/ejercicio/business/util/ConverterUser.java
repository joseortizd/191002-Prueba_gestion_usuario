/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falabella.ejercicio.business.util;

import com.falabella.ejercicio.client.dto.UserDTO;
import com.falabella.ejercicio.data.entity.UserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setStatus(true);
        return userEntity;
    }
    
    public static UserDTO userEntityToDTO (UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setStatus(userEntity.getStatus());
        userDTO.setName(userEntity.getName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setDocument(userEntity.getDocument());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        return userDTO;
    }
    
    public static List<UserDTO> userEntityToDTOList(List<UserEntity> usersEntities) {
        List<UserDTO> usersDTO = new ArrayList();
        for (UserEntity userEntity : usersEntities) {
            usersDTO.add(userEntityToDTO(userEntity));
        }
        return usersDTO;
    }
}
