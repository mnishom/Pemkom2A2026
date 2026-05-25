/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ituhn.pemkom2.util;

import com.ituhn.pemkom2.services.AuthService;

/**
 *
 * @author mnish
 */
public class UserInjector {
    public static void main(String[] args) {
        AuthService userService = new AuthService();
        userService.registerUser("M. Nishom", "uhn", "123"); 
    }
}
