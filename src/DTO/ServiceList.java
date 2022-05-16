/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author HP
 */
public class ServiceList {
    public ArrayList<Services> serviceList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    //Tim service theo ID
    public int searchServiceByID(int ID) {
        if (serviceList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }
    
    //Tim service theo ID tra ve object
    public Services searchServiceObjectByID(int ID) {
        if (serviceList.isEmpty()) {
            return null;
        }
        for (Services o : serviceList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }
    
    //Input Service
    public void addNewService() {
        int id, price;
        String name;
        int pos;

        do {
            id = MyToys.getAnInteger("Input service's ID: (integer only)", "Invalid ID!", 0);
            pos = searchServiceByID(id);
            if (pos >= 0) {
                System.out.println("The service ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        name = MyToys.getString("Input service's name: ", "Invalid name!");
        price = MyToys.getAnInteger("Input service's price: (integer only)", "Invalid price!", 0);
        serviceList.add(new Services(id, name, price));
        System.out.println("A service is added successfully.");
    }
    
    //Remove a service
    public void removeService() {
        int id;
        id = MyToys.getAnInteger("Input service's ID: (integer only)", "Invalid ID!", 0);
        if (searchServiceObjectByID(id) != null) {
            serviceList.remove(searchServiceObjectByID(id));
            System.out.println("Service removed successfully!");
        } else {
            System.out.println("Service not found");
        }
    }
    
    //Xuat list
    public void printAscendingByID() {
        if (serviceList.isEmpty()) {
            System.out.println("The Service list is empty. Nothing to print");
            return;
        }
        
        Comparator ascID = new Comparator<Services>() {
            @Override
            public int compare(Services o1, Services o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(serviceList, ascID);

        String show = String.format("|%-8s|%-5s|%-20s|%-12s|",
                "Service", "ID", "Name", "Price");
        System.out.println(show);
        System.out.println("--------------------------------------------------");
        for (Services o : serviceList) {
            o.outputService();
        }
    }
    
    //Update price
    public void updatePrice() {
        int id;
        int tmpPrice;
        Services x;
        id = MyToys.getAnInteger("Input Service's ID: (integer only)", "Invalid ID!", 0);
        x = searchServiceObjectByID(id);
        System.out.println("===========================================");
        if (x == null) {
            System.out.println("Not Found!!!!");
        } else {
            System.out.println("Here is the Service before updating");
            x.outputService();
            System.out.println("You are required to input a new price: ");
            tmpPrice = MyToys.getAnInteger("Input this service's price: (integer only)", "Invalid Price!", 0);
            x.setPrice(tmpPrice);
            System.out.println("The price info is updated successfully!");
        }
    }
}
