/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import DTO.DayAndTime;
import DTO.ServiceAndTime;
import facadePkg.DataLayer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pojo.Address;
import pojo.Days;
import pojo.Make;
import pojo.ServiceProvider;
import pojo.ServiceProviderPhone;

/**
 *
 * @author Ehab
 */
@ManagedBean(name = "enterProviderDetailsBean", eager = true)
@SessionScoped
public class EditServiceProviderDetailsBean {

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
    private String phone;
    private String[] selectedMakes;
    private String[] selectedDays;
    private String[] selectedServices;
    private Date from;
    private Date to;
    private Date serviceFrom;
    private Date serviceTo;
    private List<ServiceAndTime> serviceAndTimes;
    private List<DayAndTime> dayAndTimes;
    private int i;
    private int y;

    public List<DayAndTime> getDayAndTimes() {
        return dayAndTimes;
    }

    public void setDayAndTimes(List<DayAndTime> dayAndTimes) {
        this.dayAndTimes = dayAndTimes;
    }

    public List<ServiceAndTime> getServiceAndTimes() {
        return serviceAndTimes;
    }

    public void setServiceAndTimes(List<ServiceAndTime> serviceAndTimes) {
        this.serviceAndTimes = serviceAndTimes;
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

        DataLayer dataLayer = new DataLayer();
        dataLayer.insertAddressForServiceProvider(address);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Address", "Address Inserted"));
    }

    public void insertPhone() {
        serviceProviderPhone = new ServiceProviderPhone(serviceProvider, phone);
        DataLayer dataLayer = new DataLayer();
        dataLayer.insertPhoneForServiiceProvider(serviceProviderPhone);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Phone", "Phone Inserted"));
    }

    public List<String> getMakes() {

        List<String> returnedMakes;
        DataLayer dataLayer = new DataLayer();
        returnedMakes = dataLayer.getAllMakesAsStrings();
        return returnedMakes;

    }

    public void insertMakes() {
        DataLayer dataLayer = new DataLayer();
        dataLayer.getMakesFromStringArray(selectedMakes, serviceProvider);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Makes", "Makes Inserted"));
    }

    public List<String> getDays() {

        List<String> returnedDays;
        DataLayer dataLayer = new DataLayer();
        returnedDays = dataLayer.getAllDaysAsString();

        return returnedDays;
    }

    public void insertSchedule() {

        DataLayer dataLayer = new DataLayer();
        dataLayer.insertSchedule(dayAndTimes, serviceProvider);

    }

    public List<String> getServices() {
        List<String> returnedServices;
        DataLayer dataLayer = new DataLayer();
        returnedServices = dataLayer.getAllServicesAsString();
        return returnedServices;
    }

    public void addToDayList() {
        for (String dayName : selectedDays) {
            DayAndTime dayAndTime = new DayAndTime(dayName, from, to, y++);
            if (!dayAndTimes.contains(dayAndTime)) {
                dayAndTimes.add(dayAndTime);
            }
        }

    }

    public void deleteFromDayList(int index) {
        Iterator<DayAndTime> list = dayAndTimes.iterator();

        while (list.hasNext()) {

            DayAndTime value = list.next();
            if (value.getIndex() == index) {
                list.remove();
            }
        }
    }

    public void addToServiceList() {

        for (String serviceName : selectedServices) {
            ServiceAndTime serviceAndTime = new ServiceAndTime(serviceName, serviceFrom, serviceTo, i++);
            if (!serviceAndTimes.contains(serviceAndTime)) {
                serviceAndTimes.add(serviceAndTime);
            }
        }
    }

    public void deleteFromServiceList(int index) {
        Iterator<ServiceAndTime> list = serviceAndTimes.iterator();

        while (list.hasNext()) {

            ServiceAndTime value = list.next();
            if (value.getIndex() == index) {
                list.remove();
            }
        }
    }

    public void insertServices() {
        DataLayer dataLayer = new DataLayer();
        dataLayer.setServicesForServiceProvider(serviceAndTimes, serviceProvider);
    }

    public void initialize() {
        serviceAndTimes = new ArrayList<>();
        dayAndTimes = new ArrayList<>();
        address = null;
        make = null;
        day = null;
        city = "";
        country = "";
        street = "";
        landmark = "";
        others = "";
        postalCode = "";
        longitude = 0F;
        latitude = 0F;
        phone = null;
        selectedMakes = null;
        selectedDays = null;
        selectedServices = null;
        from = null;
        to = null;
        serviceFrom = null;
        serviceTo = null;
    }
}
