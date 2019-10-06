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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new UserException("Bad Request");
        }
        UserEntity userEntity = ConverterUser.userDtoToEntity(userDTO);
        try {
            this.userRepository.save(userEntity);
        } catch (Exception ex){
            throw  new UserException("Bad Request");
        }
        return userDTO; 
    }
    
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() throws UserException {
        List<UserEntity> userEntityList = this.userRepository.findAll();
        if (userEntityList == null) {
            throw new UserException("Not Found");
        }
        return ConverterUser.userEntityToDTOList(this.userRepository.findAll());
    }
    @Transactional(readOnly = true)
    public UserDTO findOneByDocument(String document) throws UserException {
        if (document == null) {
            throw new UserException("Document is null");
        }
        UserEntity usuarioEntity = this.userRepository.findByDocument(document);
        if (usuarioEntity == null) {
            throw new UserException("Not Found");
        }
        //return Converter.usuarioConverter(usuarioEntity);
        return ConverterUser.userEntityToDTO(usuarioEntity);
    }

}
