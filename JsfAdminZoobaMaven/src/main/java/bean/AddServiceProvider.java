/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import facadePkg.DataLayer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import pojo.ServiceProvider;

/**
 *
 * @author Ehab
 */
@ManagedBean(name = "addSerivceProviderBean")
@SessionScoped
public class AddServiceProvider {

    private String name;
    private String email;
    private String webSite;

    @ManagedProperty(value = "#{editProviderDetailsBean}")
    private EditServiceProviderDetailsBean editProviderDetailsBean;

    public EditServiceProviderDetailsBean getEditProviderDetailsBean() {
        return editProviderDetailsBean;
    }

    public void setEditProviderDetailsBean(EditServiceProviderDetailsBean editProviderDetailsBean) {
        this.editProviderDetailsBean = editProviderDetailsBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String toEditPage() {

        DataLayer dataLayer = new DataLayer();
        ServiceProvider newServiceProvider = new ServiceProvider();
        newServiceProvider.setEmail(email);
        newServiceProvider.setName(name);
        newServiceProvider.setWebsite(webSite);

        dataLayer.insertServiceProvider(newServiceProvider);

        ServiceProvider serviceProvider = dataLayer.getServiceProviderByName(name);

        editProviderDetailsBean.setServiceProvider(serviceProvider);

        return "EditServiceProvider";
    }

}
