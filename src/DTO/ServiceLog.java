/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author HP
 */
public class ServiceLog {
    Pet pet;
    Services service;
    
    //constructors

    public ServiceLog(Pet pet, Services service) {
        this.pet = pet;
        this.service = service;
    }
    //getters&setters

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }
    //methods
    
}
