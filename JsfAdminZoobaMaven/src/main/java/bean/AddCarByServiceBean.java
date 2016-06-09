/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import DTO.*;
import Utils.MailSender;
import abstractDao.HibernateFactory;
import com.google.gson.Gson;
import dao.CarFeaturesDao;
import dao.ModelFeatureValueDao;
import facadePkg.DataLayer;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import org.hibernate.Session;
import pojo.CarFeatures;
import pojo.Make;
import pojo.Model;
import pojo.ModelFeaturesValues;
import pojo.Trim;
import pojo.Year;

/**
 *
 * @author Ehab
 */

@ManagedBean(name = "dropDownService")
@SessionScoped
public class AddCarByServiceBean implements Serializable{

    MakesBean m;
    ModelsBean model;
    YearsBean year;
    TrimsBean trim;
    private List<DtoMake> dMake;
    private List<DtoModel> dModel;
    private String selectedMake;
    private String selectedModel;
    private String selectedYear;
    private String selectedTrim;
    private DtoYear dYear;
    private List<DtoTrim> dTrim;
    private List<String> myYears;

    public List<DtoMake> getdMake() {
        return dMake;
    }

    public void setdMake(List<DtoMake> dMake) {
        this.dMake = dMake;
    }

    public List<DtoModel> getdModel() {
        return dModel;
    }

    public void setdModel(List<DtoModel> dModel) {
        this.dModel = dModel;
    }

    public String getSelectedMake() {
        return selectedMake;
    }

    public void setSelectedMake(String selectedMake) {
        this.selectedMake = selectedMake;
    }

    public String getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(String selectedModel) {
        this.selectedModel = selectedModel;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public String getSelectedTrim() {
        return selectedTrim;
    }

    public void setSelectedTrim(String selectedTrim) {
        this.selectedTrim = selectedTrim;
    }

    public DtoYear getdYear() {
        return dYear;
    }

    public void setdYear(DtoYear dYear) {
        this.dYear = dYear;
    }

    public List<DtoTrim> getdTrim() {
        return dTrim;
    }

    public void setdTrim(List<DtoTrim> dTrim) {
        this.dTrim = dTrim;
    }

    public List<String> getMyYears() {
        return myYears;
    }

    public void setMyYears(List<String> myYears) {
        this.myYears = myYears;
    }

    @PostConstruct
    public void showYears() {

        myYears = new ArrayList<>();

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target("http://www.carqueryapi.com/api/0.3/?callback=?&cmd=getYears");

        String response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);

        String jsonString = response.substring(3, response.length() - 2);

        Gson gson = new Gson();

        year = gson.fromJson(jsonString, YearsBean.class);

        dYear = year.Years;

        for (int i = Integer.parseInt(dYear.getMin_year()); i < Integer.parseInt(dYear.getMax_year()); i++) {
            myYears.add(i + "");
        }

//        insertCarFeatures();
    }

    public void showMakes() {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target("http://www.carqueryapi.com/api/0.3/?callback=?&cmd=getMakes&year=" + selectedYear);

        String response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);

        String jsonString = response.substring(3, response.length() - 2);

        Gson gson = new Gson();

        m = gson.fromJson(jsonString, MakesBean.class);

        dMake = m.Makes;

    }

    public void showModels() {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target("http://www.carqueryapi.com/api/0.3/?callback=?&cmd=getModels&make=" + selectedMake + "&year=" + selectedYear);

        String response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);

        String jsonString = response.substring(3, response.length() - 2);

        Gson gson = new Gson();

        model = gson.fromJson(jsonString, ModelsBean.class);

        dModel = model.Models;

    }

    public void showTrims() {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target("http://www.carqueryapi.com/api/0.3/?callback=?&cmd=getTrims&make=" + selectedMake + "&model=" + selectedModel + "&year=" + selectedYear);

        String response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);

        String jsonString = response.substring(3, response.length() - 2);

        Gson gson = new Gson();

        trim = gson.fromJson(jsonString, TrimsBean.class);

        dTrim = trim.Trims;

    }

    public void insertVehicle() {

        DataLayer dataLayer = new DataLayer();
        Make make = new Make(selectedMake, selectedMake);
        Model model = new Model(make, selectedModel, selectedModel);
        Year year = new Year(Integer.parseInt(selectedYear));
        Trim trim = new Trim(selectedTrim);
        dataLayer.insertVehicle(make, model, year, trim);
//        insertCarDetails();

    }

    private void insertCarFeatures() {

        Session session = HibernateFactory.openSession();
        CarFeaturesDao carFeaturesDao = new CarFeaturesDao(session);

        Field[] fields = DtoTrim.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            CarFeatures carFeatures = new CarFeatures(fields[i].getName());
            carFeaturesDao.saveOrUpdate(carFeatures);
        }
    }

    private void insertCarDetails() {
        Session session = HibernateFactory.openSession();
        session.beginTransaction();
        CarFeaturesDao carFeaturesDao = new CarFeaturesDao(session);
        ModelFeatureValueDao modelFeatureValueDao = new ModelFeatureValueDao(session);
        CarFeatures carFeatures = null;
        DtoTrim myTrim = null;
        Field[] fields = DtoTrim.class.getDeclaredFields();
        Class noparams[] = {};
        for (DtoTrim trim : dTrim) {
            if (trim.getModel_trim().equalsIgnoreCase(selectedTrim)) {
                myTrim = trim;
            }
        }
        for (int i = 0; i < fields.length; i++) {

            try {

                String attributeName = fields[i].getName();
                carFeatures = carFeaturesDao.getCarFeaturesByName(attributeName);

                Object v = myTrim;
                String methodName = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
                Method method = DtoTrim.class.getDeclaredMethod(methodName, noparams);
                String result = (String) method.invoke(myTrim, null);

                ModelFeaturesValues modelFeaturesValues = new ModelFeaturesValues(carFeatures, result);
                modelFeatureValueDao.create(modelFeaturesValues);
                session.getTransaction().commit();
            } catch (NoSuchMethodException | SecurityException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void sendMail() {
        MailSender mailSender = new MailSender();
        mailSender.sendRestPasswordMail("ehab.m.ashraf@gmail.com", "Ay kalam Ka2enha Password");
    }
}
