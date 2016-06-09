/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ehab
 */

public class DtoMake {
    
    String make_id;
    String make_display;
    String make_is_common;
    String make_country;

    public String getMake_id() {
        return make_id;
    }

    public void setMake_id(String make_id) {
        this.make_id = make_id;
    }

    public String getMake_display() {
        return make_display;
    }

    public void setMake_display(String make_display) {
        this.make_display = make_display;
    }

    public String getMake_is_common() {
        return make_is_common;
    }

    public void setMake_is_common(String make_is_common) {
        this.make_is_common = make_is_common;
    }

    public String getMake_country() {
        return make_country;
    }

    public void setMake_country(String make_country) {
        this.make_country = make_country;
    }

    @Override
    public String toString() {
        return make_display;
    }

    

}
