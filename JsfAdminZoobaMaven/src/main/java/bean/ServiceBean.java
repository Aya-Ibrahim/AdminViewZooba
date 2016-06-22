/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.Service;
import facadePkg.DataLayer;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Riham
 */
@ManagedBean(name="services")
@SessionScoped
public class ServiceBean implements Serializable{
    DataLayer handler=new DataLayer();
    private List<Service> serviceList;
    private String name;
    List<String> names=new ArrayList();
 private DataModel<Service> services;
  
    public ServiceBean() {
     serviceList=handler.getServices();
     
      services=new ListDataModel<>(serviceList);
    }

     public List<String> getServiceNames() {
       serviceList=handler.getServices();
       for(Service s:serviceList)
       {
            name=s.getName();
            names.add(name);
       }     
       return names;
    }

    public DataModel<Service> getServices() {
        return services;
    }

    public void setServices(DataModel<Service> services) {
        this.services = services;
    }
}
