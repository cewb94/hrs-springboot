/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.dto;

import java.util.List;

/**
 *
 * @author Yaqoub Alshatti
 */

public class CountryResponse {
    private boolean error;
    private String msg;
    private List<Country> data;

    public CountryResponse() { }

    public CountryResponse(boolean error, String msg, List<Country> data) {
        this.error = error;
        this.msg = msg;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Country> getData() {
        return data;
    }

    public void setData(List<Country> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CountryResponse{" +
               "error=" + error +
               ", msg='" + msg + '\'' +
               ", data=" + data +
               '}';
    }
}