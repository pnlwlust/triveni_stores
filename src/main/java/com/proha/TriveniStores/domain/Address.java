package com.proha.TriveniStores.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
public class Address {

 @javax.persistence.Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long id;
 private String city;
 private String birthPlace;
 private String country;
 private String postalCode;
 private String province;
 private String street;

 private Address(){}
 public Address(String country, String province, String city, String postalCode, String street){
  this.country = country;
  this.province = province;
  this.city = city;
  this.postalCode = postalCode;
  this.street = street;

 }

}