package com.proha.TriveniStores.controller;

import lombok.val;

enum RequestMap {
    SAVE("save"),
    GET("id"),
    UPDATE("update"),
    DELETE("delete"),
    USER_REGISTER("/register"),
    CHARSET("application/json;charset=utf-8");

    private String value;

    RequestMap(String value) {
        this.value = value;
    }

    String getValue(){
        return this.value;
    }
}