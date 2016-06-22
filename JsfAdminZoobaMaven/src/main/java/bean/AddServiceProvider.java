/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import facadePkg.DataLayer;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import pojo.ServiceProvider;

/**
 *
 * @author Ehab
 */
@ManagedBean(name = "addSerivceProviderBean")
@RequestScoped
public class AddServiceProvider {

    private String name;
    private String email;
    private String webSite;
    private String mainBranchName;
    private List<String> serviceProviders;

    @ManagedProperty(value = "#{enterProviderDetailsBean}")
    private EditServiceProviderDetailsBean editProviderDetailsBean;

    public AddServiceProvider() {
        DataLayer dataLayer = new DataLayer();
        serviceProviders = dataLayer.getAllServiceProviders();
    }

    public EditServiceProviderDetailsBean getEditProviderDetailsBean() {
        return editProviderDetailsBean;
    }

    public void setEditProviderDetailsBean(EditServiceProviderDetailsBean editProviderDetailsBean) {
        this.editProviderDetailsBean = editProviderDetailsBean;
    }

    public List<String> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<String> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public String getMainBranchName() {
        return mainBranchName;
    }

    public void setMainBranchName(String mainBranchName) {
        this.mainBranchName = mainBranchName;
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
        ServiceProvider mainServicerovider = new ServiceProvider();
        mainServicerovider.setName(mainBranchName);
        dataLayer.insertServiceProvider(newServiceProvider, mainServicerovider);

        ServiceProvider serviceProvider = dataLayer.getServiceProviderByName(name);
        editProviderDetailsBean.initialize();
        editProviderDetailsBean.setServiceProvider(serviceProvider);

        return "EditServiceProvider";
    }

}
