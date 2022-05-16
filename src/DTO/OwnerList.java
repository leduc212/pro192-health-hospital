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
public class OwnerList {
    public ArrayList<Owner> ownerList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    //Tim staff theo ID
    public int searchOwnerByID(int ID) {
        if (ownerList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < ownerList.size(); i++) {
            if (ownerList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }
    
    //Tim owner theo ID tra ve object
    public Owner searchOwnerObjectByID(int ID) {
        if (ownerList.isEmpty()) {
            return null;
        }
        for (Owner o : ownerList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }
    
    //Input Owner
    public void addNewOwner() {
        int id;
        String name, address;
        int pos;

        do {
            id = MyToys.getAnInteger("Input owner's ID: (integer only)", "Invalid ID!", 0);
            pos = searchOwnerByID(id);
            if (pos >= 0) {
                System.out.println("The owner ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        name = MyToys.getString("Input owner's name: ", "Invalid name!");
        address = MyToys.getString("Input owner's address: ", "Invalid address!");
        ownerList.add(new Owner(id, name, address));
        System.out.println("An Owner profile is added successfully.");
    }
    
    //Remove an owner
    public void removeOwner() {
        int id;
        id = MyToys.getAnInteger("Input owner's ID: (integer only)", "Invalid ID!", 0);
        if (searchOwnerObjectByID(id) != null) {
            ownerList.remove(searchOwnerObjectByID(id));
            System.out.println("Owner removed successfully!");
        } else {
            System.out.println("Owner not found");
        }
    }
    
    //Xuat list
    public void printAscendingByID() {
        if (ownerList.isEmpty()) {
            System.out.println("The Owner list is empty. Nothing to print");
            return;
        }
        
        Comparator ascID = new Comparator<Owner>() {
            @Override
            public int compare(Owner o1, Owner o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(ownerList, ascID);

        String show = String.format("|%-8s|%-5s|%-20s|%-40s|",
                "Owner", "ID", "Name", "Address");
        System.out.println(show);
        System.out.println("------------------------------------------------------------------------------");
        for (Owner o : ownerList) {
            o.outputOwner();
        }
    }
}
