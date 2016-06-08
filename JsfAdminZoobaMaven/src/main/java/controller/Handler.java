/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MakeDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import pojo.Make;

/**
 *
 * @author yoka
 */
public class Handler {
    MakeDao makeDao=new MakeDao();
   public List<Make> getVehicle()
   {
  // makeDao.findAll(Make.class);
      List<Make> arr= makeDao.findAll();
     return arr;
           
   }
}
