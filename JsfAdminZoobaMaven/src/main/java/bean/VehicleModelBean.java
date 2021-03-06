/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import facadePkg.DataLayer;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pojo.VehicleModel;

/**
 *
 * @author Riham
 */
@ManagedBean(name = "vehiclemodel")
@SessionScoped
public class VehicleModelBean implements Serializable {

    DataLayer handler = new DataLayer();
    String vehicleModelId;
    private DataModel<VehicleModel> model = new ListDataModel<>();

    public DataModel<VehicleModel> getModel() {
        return model;
    }

    public void setModel(DataModel<VehicleModel> model) {
        this.model = model;
    }

    public String goToEdit() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        vehicleModelId = params.get("id");
        System.out.println("Hellooooooo" + vehicleModelId);
        int n = Integer.parseInt(vehicleModelId);
        model = new ListDataModel<>(handler.getVehicleModel(n));
        return "Edit-Car";
    }

    public void updateVehicleModel(VehicleModel v) {
        handler.updateVehicleModel(v);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Edit Vehicle", "Vehicle updated "));
    }

}
