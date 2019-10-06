/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falabella.ejercicio.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.falabella.ejercicio.data.entity.UserEntity;

import org.springframework.stereotype.Repository;
/**
 *
 * @author joseortiz
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
        UserEntity findByDocument(String document);
}
