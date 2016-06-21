/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import facadePkg.DataLayer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pojo.ModelFeaturesValues;
import pojo.VehicleModel;

/**
 *
 * @author yoka
 */
@ManagedBean(name = "vehicle", eager = true)
@SessionScoped
public class VehiclesBean implements Serializable {

    ArrayList<String> vehicles;
// Handler handler=new Handler();
    DataLayer handler = new DataLayer();
    private String name;
    private String action;
    private transient DataModel<VehicleModel> model = new ListDataModel<>(handler.getVehicles());
    private transient DataModel<ModelFeaturesValues> list = new ListDataModel<>();

    public DataModel<ModelFeaturesValues> getList() {
        return list;
    }

    public void setList(DataModel<ModelFeaturesValues> list) {
        this.list = list;
    }

    public DataModel<VehicleModel> getModel() {
        return model;
    }

    public void setModel(DataModel<VehicleModel> model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<String> vehicles) {
        this.vehicles = vehicles;
    }

    public void deleteVehicle(int id) {

        boolean flag = handler.deleteVehicle(id);
        if (!flag) {
//          FacesContext context = FacesContext.getCurrentInstance();
//          context.addMessage( null, new FacesMessage( "This vehicle model is associated to a car" ));  
        } else {
            model = new ListDataModel<>(handler.getVehicles());
        }
    }

    public String showPage() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("id");
        System.out.println("Hellooooooo" + action);
        int n = Integer.parseInt(action);
        list = new ListDataModel<>(handler.showFeatures(n));
        return "ShowFeatures";
    }

    public void updateFeatures(ModelFeaturesValues m) {
        handler.updateModelFeatureValues(m);
        int n = Integer.parseInt(action);
        list = new ListDataModel<>(handler.showFeatures(n));
    }

    public void onload() {
        model=new ListDataModel<>(handler.getVehicles());
    }
}
