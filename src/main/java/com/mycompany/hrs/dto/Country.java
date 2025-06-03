/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Yaqoub Alshatti
 */
public class Country {

    private String name;

    @JsonProperty("Iso2")
    private String iso2;

    @JsonProperty("Iso3")
    private String iso3;

    public Country() { }

    public Country(String name, String iso2, String iso3) {
        this.name = name;
        this.iso2 = iso2;
        this.iso3 = iso3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    @Override
    public String toString() {
        return "Country{" +
               "name='" + name + '\'' +
               ", iso2='" + iso2 + '\'' +
               ", iso3='" + iso3 + '\'' +
               '}';
    }
}