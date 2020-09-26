package com.proha.TriveniStores.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/")
     public String index(){
         return "First Triveni Stores app";
     }

     @RequestMapping("/loggedIn")
    public String afterLogin(){
        return "User Logged in";
     }
}