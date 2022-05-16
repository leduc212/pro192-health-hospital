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
public class Pet {
    int id;
    String name;
    String birthday;
    String gender;
    Owner owner;
    //constructors

    public Pet(int id, String name, String birthday, String gender, Owner owner) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.owner = owner;
    }

    public Pet() {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    
    
    //methods
    public void inputPet(){
        id = MyToys.getAnInteger("Input pet's ID: (integer only)", "Invalid ID!", 0);
        name = MyToys.getString("Input pet's name: ", "Invalid name!");
        gender = MyToys.getId("Input pet's gender: (M/F)", "Invalid gender!", "^[M|F|m|f]$");
        birthday = MyToys.getDate("Input pet's birthday: (dd/mm/yyyy)", "Invalid date!");
    }
    
    public void outputPet(){
        String show = String.format("|%-8s|%-5d|%-20s|%-7s|%-15s|%-10s|",
                "Pet", getId(), getName(), getGender(),getBirthday(),getOwner().getId());
        System.out.println(show);
    }
    
}
