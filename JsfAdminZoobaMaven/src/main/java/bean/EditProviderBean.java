/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import facadePkg.DataLayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.Address;
import pojo.Days;
import pojo.Make;
import pojo.ServiceProvider;
import pojo.ServiceProviderCalendar;
import pojo.ServiceProviderPhone;
import pojo.ServiceProviderServices;

/**
 *
 * @author Ehab
 */
@ManagedBean(name = "editProvider", eager = true)
@SessionScoped
public class EditProviderBean {

    private ServiceProvider serviceProvider;
    private Address address;
    private ServiceProviderPhone serviceProviderPhone;
    private Make make;
    private Days day;

    private String city;
    private String country;
    private String street;
    private String landmark;
    private String others;
    private String postalCode;
    private Float longitude;
    private Float latitude;
    private int id;
    private String phone;
    private String[] selectedMakes;
    private String[] selectedMakesBeforeEdit;
    private String[] selectedDays;
    private String[] selectedDaysBeforeEdit;
    private String[] selectedServices;
    private String[] selectedServicesBeforeEdit;
    private Date from;
    private Date to;
    private Date serviceFrom;
    private Date serviceTo;

    public String[] getSelectedMakesBeforeEdit() {
        return selectedMakesBeforeEdit;
    }

    public void setSelectedMakesBeforeEdit(String[] selectedMakesBeforeEdit) {
        this.selectedMakesBeforeEdit = selectedMakesBeforeEdit;
    }

    public String[] getSelectedDaysBeforeEdit() {
        return selectedDaysBeforeEdit;
    }

    public void setSelectedDaysBeforeEdit(String[] selectedDaysBeforeEdit) {
        this.selectedDaysBeforeEdit = selectedDaysBeforeEdit;
    }

    public String[] getSelectedServicesBeforeEdit() {
        return selectedServicesBeforeEdit;
    }

    public void setSelectedServicesBeforeEdit(String[] selectedServicesBeforeEdit) {
        this.selectedServicesBeforeEdit = selectedServicesBeforeEdit;
    }

    public Date getServiceFrom() {
        return serviceFrom;
    }

    public void setServiceFrom(Date serviceFrom) {
        this.serviceFrom = serviceFrom;
    }

    public Date getServiceTo() {
        return serviceTo;
    }

    public void setServiceTo(Date serviceTo) {
        this.serviceTo = serviceTo;
    }

    public String[] getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(String[] selectedServices) {
        this.selectedServices = selectedServices;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    public String[] getSelectedDays() {
        return selectedDays;
    }

    public void setSelectedDays(String[] selectedDays) {
        this.selectedDays = selectedDays;
    }

    public String[] getSelectedMakes() {
        return selectedMakes;
    }

    public void setSelectedMakes(String[] selectedMakes) {
        this.selectedMakes = selectedMakes;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ServiceProviderPhone getServiceProviderPhone() {
        return serviceProviderPhone;
    }

    public void setServiceProviderPhone(ServiceProviderPhone serviceProviderPhone) {
        this.serviceProviderPhone = serviceProviderPhone;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void insertAddress() {

        address = new Address(serviceProvider, city, country, street, landmark, others, postalCode, longitude, latitude);
        address.setId(id);
        DataLayer dataLayer = new DataLayer();
        dataLayer.insertAddressForServiceProvider(address);
    }

    public void insertPhone() {
        serviceProviderPhone = new ServiceProviderPhone(serviceProvider, phone);
        DataLayer dataLayer = new DataLayer();
        dataLayer.insertPhoneForServiiceProvider(serviceProviderPhone);
    }

    public List<String> getMakes() {

        List<String> returnedMakes;
        DataLayer dataLayer = new DataLayer();
        returnedMakes = dataLayer.getAllMakesAsStrings();
        return returnedMakes;

    }

    public void insertMakes() {
        DataLayer dataLayer = new DataLayer();
//        List<String> makeListBefore = (List<String>) Arrays.asList(selectedMakesBeforeEdit);
//        for (String makeName : selectedMakes) {
//            if (!makeListBefore.contains(makeName)) {
//                dataLayer.insertMakeForServiceProvider(makeName, serviceProvider);
//            }
//        }
//        List<String> makeListAfter = (List<String>) Arrays.asList(selectedMakes);
//        for (String makeName : selectedMakesBeforeEdit) {
//            if (!makeListAfter.contains(makeName)) {
//                dataLayer.deleteMakeForServiceProvider(makeName, serviceProvider);
//            }
//        }
//        selectedMakesBeforeEdit = selectedMakes.clone();
        dataLayer.getMakesFromStringArray(selectedMakes, serviceProvider);
    }

    public List<String> getDays() {

        List<String> returnedDays;
        DataLayer dataLayer = new DataLayer();
        returnedDays = dataLayer.getAllDaysAsString();

        return returnedDays;
    }

    public void insertSchedule() {

        DataLayer dataLayer = new DataLayer();
//        dataLayer.insertSchedule(da, serviceProvider);

    }

    public List<String> getServices() {
        List<String> returnedServices;
        DataLayer dataLayer = new DataLayer();
        returnedServices = dataLayer.getAllServicesAsString();
        return returnedServices;
    }

    public void insertServices() {
        DataLayer dataLayer = new DataLayer();
        List<String> serviceListBefore = (List<String>) Arrays.asList(selectedServicesBeforeEdit);
        for (String serviceName : selectedServices) {
            if (!serviceListBefore.contains(serviceName)) {
                dataLayer.insertServiceForServiceProvider(serviceName, serviceProvider, serviceFrom, serviceTo);
            }
        }
        List<String> serviceListAfter = (List<String>) Arrays.asList(selectedServices);
        for (String serviceName : selectedServicesBeforeEdit) {
            if (!serviceListAfter.contains(serviceName)) {
                dataLayer.deleteServiceForServiceProvider(serviceName, serviceProvider, serviceFrom, serviceTo);
            }
        }
        selectedServicesBeforeEdit = selectedServices.clone();

    }

    public void fillData() {
        city = serviceProvider.getAddress().getCity();
        country = serviceProvider.getAddress().getCountry();
        landmark = serviceProvider.getAddress().getLandmark();
        others = serviceProvider.getAddress().getOthers();
        postalCode = serviceProvider.getAddress().getPostalCode();
        longitude = serviceProvider.getAddress().getLongitude();
        latitude = serviceProvider.getAddress().getLatitude();
        id = serviceProvider.getAddress().getId();
        phone = new ArrayList<>(serviceProvider.getServiceProviderPhones()).get(0).getPhone();

        selectedMakes = new String[50];
//        selectedMakesBeforeEdit = new String[50];
        selectedDays = new String[50];
        selectedDaysBeforeEdit = new String[50];
        selectedServices = new String[50];
        selectedServicesBeforeEdit = new String[50];

        List<Make> makeListEdit = new ArrayList<>(serviceProvider.getMakes());
        for (int i = 0; i < makeListEdit.size(); i++) {
            selectedMakes[i] = makeListEdit.get(i).getName();
        }
        List<ServiceProviderCalendar> dayListEdit = new ArrayList<>(serviceProvider.getServiceProviderCalendars());
        for (int i = 0; i < dayListEdit.size(); i++) {
            selectedDays[i] = dayListEdit.get(i).getDays().getName();
        }
        List<ServiceProviderServices> serviceListEdit = new ArrayList<>(serviceProvider.getServiceProviderServiceses());
        for (int i = 0; i < serviceListEdit.size(); i++) {
            selectedServices[i] = serviceListEdit.get(i).getService().getName();
        }
        selectedDaysBeforeEdit = selectedDays.clone();
//        selectedMakesBeforeEdit = selectedMakes.clone();
        selectedServicesBeforeEdit = selectedServices.clone();
    }
}
