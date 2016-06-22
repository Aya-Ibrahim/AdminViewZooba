/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author Ehab
 */
public class DtoModel implements Serializable{
    
    String model_name;
    String model_make_id;

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getModel_make_id() {
        return model_make_id;
    }

    public void setModel_make_id(String model_make_id) {
        this.model_make_id = model_make_id;
    }
}
