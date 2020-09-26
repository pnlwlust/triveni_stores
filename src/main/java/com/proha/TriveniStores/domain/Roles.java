package com.proha.TriveniStores.domain;

public enum Roles {
    ADMIN("ADMIN"), USER("USER");

    public String type;
    Roles(String type){
       this.type = type;
    }
}
