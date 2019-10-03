/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falabella.ejercicio.client.exception;

/**
 *
 * @author joseortiz
 */
public class UserException extends Exception{
    
   public UserException() {
   }
    
   public UserException(String string) {
        super(string);
   }    
    
   public UserException(String string, Throwable thrwbl) {
        super(string, thrwbl);
   }
   
   @Override
   public String toString() {
       return super.toString(); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public synchronized Throwable getCause() {
       return super.getCause(); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public String getMessage() {
       return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
   }
   
}
