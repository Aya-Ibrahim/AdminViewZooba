/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import DTO.TypeAndUnit;
import facadePkg.DataLayer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ehab
 */
@ManagedBean(name = "addServicesBean")
@SessionScoped
public class ServicesBean implements Serializable {

    private String name;
    private String selectedType;
    private String selectedUnit;
    private int index;
    private List<TypeAndUnit> typeAndUnits;
    TypeAndUnit tau;
    int i;

    public ServicesBean() {
        typeAndUnits = new ArrayList<>();
        i = 0;

    }

    public List<TypeAndUnit> getTypeAndUnits() {
        return typeAndUnits;
    }

    public void setTypeAndUnits(List<TypeAndUnit> typeAndUnits) {
        this.typeAndUnits = typeAndUnits;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(String selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void insertService() {
        DataLayer dataLayer = new DataLayer();

        dataLayer.insertService(name, typeAndUnits);

    }

    public List<String> getUnits() {
        List<String> result = null;
        DataLayer dataLayer = new DataLayer();
        result = dataLayer.getAllUnits();
        return result;
    }

    public void addToTypeList() {
        tau = new TypeAndUnit();
        tau.setTypeName(selectedType);
        tau.setUnitName(selectedUnit);
        tau.setIndex(i++);
        typeAndUnits.add(tau);
    }

    public void deleteFromTypeList(int index) {
        Iterator<TypeAndUnit> list = typeAndUnits.iterator();

        while (list.hasNext()) {

            TypeAndUnit value = list.next();
            if (value.getIndex() == index) {
                list.remove();
            }
        }
    }
}
