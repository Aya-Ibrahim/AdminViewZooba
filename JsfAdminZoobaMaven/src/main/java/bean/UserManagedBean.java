/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import facadePkg.DataLayer;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pojo.User;

/**
 *
 * @author yoka
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserManagedBean {

    private DataModel<User> users;

    DataLayer handler = new DataLayer();

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
        List<User> list = handler.getAllUsers();
        users = new ListDataModel<>(list);
    }

    public DataModel<User> getUsers() {
        return users;
    }

    public void setUsers(DataModel<User> users) {
        this.users = users;
    }

    public void susppendUser() {
        User u = users.getRowData();
        int result;
        if (u.getSuspended() == 0) {
            result = handler.suspendUser(u);
        } else {
            result = handler.unsuspendUser(u);
        }

        users = new ListDataModel<>(handler.getAllUsers());
    }

}
