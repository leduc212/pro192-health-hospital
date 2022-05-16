/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import util.MyToys;

/**
 *
 * @author HP
 */
public class Services {
    int id;
    String name;
    int price;
    
    //constructors

    public Services() {
    }

    public Services(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    //getters&setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    //methods
    public void inputService(){
        id = MyToys.getAnInteger("Input service's ID: (integer only)", "Invalid ID!", 0);
        name = MyToys.getString("Input service's name: ", "Invalid name!");
        price = MyToys.getAnInteger("Input service's price: (integer only)", "Invalid price!", 0);
    }
    
    public void outputService() {
        String show = String.format("|%-8s|%-5d|%-20s|%-12d|",
                "Service", getId(), getName(), getPrice());
        System.out.println(show);
    }
}
