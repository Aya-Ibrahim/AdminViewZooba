/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import controller.Handler;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pojo.Make;

/**
 *
 * @author yoka
 */
@ManagedBean(name="vehicle")
@SessionScoped
public class VehiclesBean {
 ArrayList<Make> vehicles;
Handler handler=new Handler();
    public ListDataModel getVehicles() {
        return data;
    }
        ListDataModel data ;
        
    /**
     * Creates a new instance of VehiclesBean
     */
    public VehiclesBean() {
//     vehicles=new ArrayList<>();
//     vehicles.add("e1");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
//     vehicles.add("e2");
     
     List<Make> listmakes=handler.getVehicle();
    
     vehicles=new ArrayList<>(listmakes);
        data=new ListDataModel(vehicles) ;
//      
System.out.println("vehicles : "+listmakes.get(0));
        }
    
    
    }
    

