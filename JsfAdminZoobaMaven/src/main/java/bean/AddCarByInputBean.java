/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import facadePkg.DataLayer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pojo.Make;
import pojo.Model;
import pojo.Trim;
import pojo.Year;

/**
 *
 * @author Ehab
 */
@ManagedBean(name = "serviceByInput")
@SessionScoped
public class AddCarByInputBean implements Serializable {

    private String makeSelected;
    private String modelSelected;
    private String yearSelected;
    private String trimSelected;

    public void insertVehicle() {

        DataLayer dataLayer = new DataLayer();
        Make make = new Make(makeSelected, makeSelected);
        Model model = new Model(make, modelSelected, modelSelected);
        Year year = new Year(Integer.parseInt(yearSelected));
        Trim trim = new Trim(trimSelected);
        dataLayer.insertVehicle(make, model, year, trim);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Vehicle", "Vehicle Inserted"));
    }

    public List<String> getYears(String str) {

        List<String> returnedYears = new ArrayList<>();
//        YearDao yearDao = new YearDao();
//        List<Year> lsYears = yearDao.findAll();
//        
//        lsYears.stream().forEach((year) -> {
//            returnedYears.add(year.toString());
//        });
        return returnedYears;
    }

    public List<String> getMakes(String str) {

        List<String> returnedMakes = new ArrayList<>();
//        MakeDao makeDao = new MakeDao();
//        List<Make> lsMakes = makeDao.getMakeByName(str);
//        
//        lsMakes.stream().forEach((make) -> {
//            returnedMakes.add(make.getName());
//        });
        return returnedMakes;
    }

    public List<String> getModels(String str) {

        List<String> returnedModels = new ArrayList<>();
//        ModelDao modelDao = new ModelDao();
//        List<Model> lsModels = modelDao.getModelByMakeAndName(makeSelected, str);
//        
//        lsModels.stream().forEach((model) -> {
//            returnedModels.add(model.getName());
//        });
        return returnedModels;
    }

    public String getMakeSelected() {
        return makeSelected;
    }

    public void setMakeSelected(String makeSelected) {
        this.makeSelected = makeSelected;
    }

    public String getModelSelected() {
        return modelSelected;
    }

    public void setModelSelected(String modelSelected) {
        this.modelSelected = modelSelected;
    }

    public String getYearSelected() {
        return yearSelected;
    }

    public void setYearSelected(String yearSelected) {
        this.yearSelected = yearSelected;
    }

    public String getTrimSelected() {
        return trimSelected;
    }

    public void setTrimSelected(String trimSelected) {
        this.trimSelected = trimSelected;
    }

}
