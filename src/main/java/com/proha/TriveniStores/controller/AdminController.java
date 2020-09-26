package com.proha.TriveniStores.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @RequestMapping("/admin")
    public String index(){
        return "This is admin main page";
    }
}
