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
public class PetList {

    public ArrayList<Pet> petList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //Tim pet theo ID
    public int searchPetByID(int ID) {
        if (petList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < petList.size(); i++) {
            if (petList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }

    //Tim pet theo ID tra ve object
    public Pet searchPetObjectByID(int ID) {
        if (petList.isEmpty()) {
            return null;
        }
        for (Pet o : petList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }

    //Input Pet
    public void addNewPet(OwnerList o) {
        int id;
        String name, birthday, gender;
        int ownerID;
        int pos;

        do {
            id = MyToys.getAnInteger("Input pet's ID: (integer only)", "Invalid ID!", 0);
            pos = searchPetByID(id);
            if (pos >= 0) {
                System.out.println("The pet ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        ownerID = MyToys.getAnInteger("Input the ID of this pet's owner: (integer only)", "Invalid ID!", 0);
        pos = o.searchOwnerByID(ownerID);
        if (pos >= 0) {
            System.out.println("Added the Owner of this pet!");
        } else {
            System.out.println("Can't find the Owner with this ID! Please add a new owner in the 'Owner Management' menu first");
            return;
        }

        name = MyToys.getString("Input pet's name: ", "Invalid name!");
        gender = MyToys.getId("Input pet's gender: (M/F)", "Invalid gender!", "^[M|F|m|f]$");
        birthday = MyToys.getDate("Input pet's birthday: (dd/mm/yyyy)", "Invalid date!");

        Owner owner = o.searchOwnerObjectByID(ownerID);
        petList.add(new Pet(id, name, birthday, gender, owner));
        System.out.println("A Pet profile is added successfully.");
    }

    //Remove a pet
    public void removePet() {
        int id;
        id = MyToys.getAnInteger("Input pet's ID: (integer only)", "Invalid ID!", 0);
        if (searchPetObjectByID(id) != null) {
            petList.remove(searchPetObjectByID(id));
            System.out.println("Pet removed successfully!");
        } else {
            System.out.println("Pet not found");
        }
    }

    //Xuat list
    public void printAscendingByID() {
        if (petList.isEmpty()) {
            System.out.println("The Pet list is empty. Nothing to print");
            return;
        }

        Comparator ascID = new Comparator<Pet>() {
            @Override
            public int compare(Pet o1, Pet o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(petList, ascID);

        String show = String.format("|%-8s|%-5s|%-20s|%-7s|%-15s|%-10s|",
                "Pet", "ID", "Name", "Gender", "Birthday", "Owner's ID");
        System.out.println(show);
        System.out.println("------------------------------------------------------------------------");
        for (Pet o : petList) {
            o.outputPet();
        }
    }
}
