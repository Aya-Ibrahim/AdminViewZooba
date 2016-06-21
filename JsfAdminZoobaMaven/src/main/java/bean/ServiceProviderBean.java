/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pojo.Make;
import pojo.ServiceProvider;
import facadePkg.DataLayer;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Riham
 */
@ManagedBean(name = "serviceprovider")
@RequestScoped
public class ServiceProviderBean implements Serializable {

    DataLayer handler = new DataLayer();
    private DataModel<Object[]> model = new ListDataModel<>(handler.findServiceProviders());
    private transient DataModel<Make> list = new ListDataModel<>();
    private String id;

    @ManagedProperty("#{services}")
    private ServiceBean bean;
    private List<String> serviceNames;

    @ManagedProperty("#{editProvider}")
    private EditProviderBean editBean;

    public EditProviderBean getEditBean() {
        return editBean;
    }

    public void setEditBean(EditProviderBean editBean) {
        this.editBean = editBean;
    }

    public DataModel<Object[]> getModel() {
        return model;
    }

    public void setModel(DataModel<Object[]> model) {
        this.model = model;
    }

    public DataModel<Make> getList() {
        return list;
    }

    public void setList(DataModel<Make> list) {
        this.list = list;
    }

    public ServiceBean getBean() {
        return bean;
    }

    public void setBean(ServiceBean bean) {
        this.bean = bean;
    }

    public List<String> getServiceNames() {
        return bean.getServiceNames();
    }

    public String showMakesPage() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        id = params.get("id");
        int n = Integer.parseInt(id);
        list = new ListDataModel<>(handler.showMakes(n));
        return "SupportedModels";
    }

    public String toEditPage() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        id = params.get("id");
        facadePkg.DataLayer dataLayer = new facadePkg.DataLayer();
        ServiceProvider serviceProvider = dataLayer.getServiceProviderById(Integer.parseInt(id));

        editBean.setServiceProvider(serviceProvider);
        editBean.fillData();
        return "EditProviderDetails";
    }

    public void onload() {
        model = new ListDataModel<>(handler.findServiceProviders());
    }

}
