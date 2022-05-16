/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.Owner;
import DTO.OwnerList;
import DTO.Pet;
import DTO.PetList;
import DTO.ServiceList;
import DTO.ServiceLog;
import DTO.Services;
import java.util.ArrayList;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author HP
 */
public class Program {

    public static void main(String[] args) {

        OwnerList o = new OwnerList();
        PetList p = new PetList();
        ServiceList s = new ServiceList();
        ArrayList<ServiceLog> serviceLog = new ArrayList<>();

        o.ownerList = initOwner();
        p.petList = initPet();
        s.serviceList = initService();
        serviceLog = initServiceLog();

        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            menu();
            choice = MyToys.getAnInteger("", "Input a valid option, please!", 1, 5);

            switch (choice) {
                case 1:
                    boolean flag = true;
                    do {
                        menu1();
                        int choice1 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 5);
                        switch (choice1) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Owner!");
                                o.addNewOwner();
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove an Owner");
                                o.removeOwner();
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Owners");
                                o.printAscendingByID();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search an Owner with ID and show their pet");
                                int id;
                                id = MyToys.getAnInteger("Input Owner's ID: (integer only)", "Invalid ID!", 0);
                                if (o.searchOwnerObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-40s|",
                                            "Owner", "ID", "Name", "Address");
                                    System.out.println(show);
                                    System.out.println("------------------------------------------------------------------------------");
                                    o.searchOwnerObjectByID(id).outputOwner();
                                    System.out.println("");
                                } else {
                                    System.out.println("Owner not found");
                                }
                                for (Pet pet : p.petList) {
                                    if (pet.getOwner().getId() == id) {
                                        String show = String.format("|%-8s|%-5s|%-20s|%-7s|%-15s|%-10s|",
                                                "Pet", "ID", "Name", "Gender", "Birthday", "Owner's ID");
                                        System.out.println(show);
                                        System.out.println("------------------------------------------------------------------------");
                                        break;
                                    }
                                }
                                for (Pet pet : p.petList) {
                                    if (pet.getOwner().getId() == id) {
                                        pet.outputPet();
                                    }
                                }
                                break;
                            case 5:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 2:
                    flag = true;
                    do {
                        menu2();
                        int choice2 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 5);
                        switch (choice2) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Pet!");
                                p.addNewPet(o);
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove a Pet");
                                p.removePet();
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Pets");
                                p.printAscendingByID();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search a Pet with ID and show their services");
                                int id;
                                id = MyToys.getAnInteger("Input Pet's ID: (integer only)", "Invalid ID!", 0);
                                if (p.searchPetObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-7s|%-15s|%-10s|",
                                            "Pet", "ID", "Name", "Gender", "Birthday", "Owner's ID");
                                    System.out.println(show);
                                    System.out.println("------------------------------------------------------------------------");
                                    p.searchPetObjectByID(id).outputPet();
                                    System.out.println("");
                                    for (ServiceLog log : serviceLog) {
                                        int petID = log.getPet().getId();
                                        if (petID == id) {
                                            String show1 = String.format("|%-8s|%-5s|%-20s|%-12s|",
                                                    "Service", "ID", "Name", "Price");
                                            System.out.println(show1);
                                            System.out.println("--------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (ServiceLog log : serviceLog) {
                                        int petID = log.getPet().getId();
                                        Services service = log.getService();

                                        if (petID == id) {
                                            service.outputService();
                                        }
                                    }
                                } else {
                                    System.out.println("Pet not found");
                                }
                                break;
                            case 5:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 3:
                    flag = true;
                    do {
                        menu3();
                        int choice3 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 6);
                        switch (choice3) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Service!");
                                s.addNewService();
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Update the price of a Service!");
                                s.updatePrice();
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove a Service");
                                s.removeService();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Services");
                                s.printAscendingByID();
                                break;
                            case 5:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search a Service with ID and show which Pet is using the Service");
                                int id;
                                id = MyToys.getAnInteger("Input Service's ID: (integer only)", "Invalid ID!", 0);
                                if (s.searchServiceObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-12s|",
                                            "Service", "ID", "Name", "Price");
                                    System.out.println(show);
                                    System.out.println("--------------------------------------------------");
                                    s.searchServiceObjectByID(id).outputService();
                                    System.out.println("");
                                    for (ServiceLog log : serviceLog) {
                                        int serviceID = log.getService().getId();
                                        if (serviceID == id) {
                                            String show1 = String.format("|%-8s|%-5s|%-20s|%-7s|%-15s|%-10s|",
                                                    "Pet", "ID", "Name", "Gender", "Birthday", "Owner's ID");
                                            System.out.println(show1);
                                            System.out.println("------------------------------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (ServiceLog log : serviceLog) {
                                        int serviceID = log.getService().getId();
                                        Pet pet = log.getPet();

                                        if (serviceID == id) {
                                            pet.outputPet();
                                        }
                                    }
                                } else {
                                    System.out.println("Service not found");
                                }
                                break;
                            case 6:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 4:
                    flag = true;
                    do {
                        menu4();
                        int choice3 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 4);
                        switch (choice3) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new pet service log!");
                                System.out.println("Input a pet and a service that is used by the pet!");
                                //Tim Pet
                                int petID,
                                 serviceID;
                                petID = MyToys.getAnInteger("Input Pet's ID: (integer only)", "Invalid ID!", 0);
                                if (p.searchPetObjectByID(petID) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-7s|%-15s|%-10s|",
                                            "Pet", "ID", "Name", "Gender", "Birthday", "Owner's ID");
                                    System.out.println(show);
                                    System.out.println("------------------------------------------------------------------------");
                                    p.searchPetObjectByID(petID).outputPet();
                                } else {
                                    System.out.println("Pet not found");
                                    break;
                                }
                                //Tim service
                                serviceID = MyToys.getAnInteger("Input Service's ID: (integer only)", "Invalid ID!", 0);
                                if (s.searchServiceObjectByID(serviceID) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-12s|",
                                            "Service", "ID", "Name", "Price");
                                    System.out.println(show);
                                    System.out.println("--------------------------------------------------");
                                    s.searchServiceObjectByID(serviceID).outputService();
                                } else {
                                    System.out.println("Service not found");
                                    break;
                                }
                                //Add vao 1 list
                                serviceLog.add(new ServiceLog(p.searchPetObjectByID(petID), s.searchServiceObjectByID(serviceID)));
                                System.out.println("Added a new log successfully!");
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show all pet service log!");
                                int n = 0;
                                if (serviceLog.isEmpty()) {
                                    System.out.println("The service log is empty. Nothing to print");
                                    break;
                                }
                                String show = String.format("|%-8s|%-8s|%-20s|%-10s|%-20s|%-15s|",
                                        "Log ", "Pet ID", "Pet name", "Service ID", "Service name", "Price");
                                System.out.println(show);
                                System.out.println("----------------------------------------------------------------------------------------");
                                for (ServiceLog log : serviceLog) {
                                    n += 1;
                                    int tmpPetID = log.getPet().getId();
                                    String tmpPetName = log.getPet().getName();
                                    int tmpServiceID = log.getService().getId();
                                    String tmpServiceName = log.getService().getName();
                                    int tmpPRice = log.getService().getPrice();

                                    show = String.format("|%-8d|%-8d|%-20s|%-10d|%-20s|%-15d|",
                                            n, tmpPetID, tmpPetName, tmpServiceID, tmpServiceName, tmpPRice);
                                    System.out.println(show);
                                }
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove a service log!");
                                if (serviceLog.isEmpty()) {
                                    System.out.println("The service log list is empty. Nothing to remove!");
                                    break;
                                }
                                System.out.println("Input a pet's ID and choose which service you want to remove!");
                                int pID;
                                Pet petObj = null;
                                pID = MyToys.getAnInteger("Input Pet's ID: (integer only)", "Invalid ID!", 0);

                                for (int i = 0; i < serviceLog.size(); i++) {
                                    if (serviceLog.get(i).getPet().getId() == pID) {
                                        petObj = serviceLog.get(i).getPet();
                                        break;
                                    }
                                }
                                if (petObj != null) {
                                    String show1 = String.format("|%-8s|%-5s|%-20s|%-7s|%-15s|%-10s|",
                                            "Pet", "ID", "Name", "Gender", "Birthday", "Owner's ID");
                                    System.out.println(show1);
                                    System.out.println("------------------------------------------------------------------------");
                                    p.searchPetObjectByID(pID).outputPet();
                                    System.out.println("This pet is using these services:");
                                    for (ServiceLog log : serviceLog) {
                                        petID = log.getPet().getId();
                                        if (petID == pID) {
                                            String show2 = String.format("|%-8s|%-5s|%-20s|%-12s|",
                                                    "Service", "ID", "Name", "Price");
                                            System.out.println(show2);
                                            System.out.println("--------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (ServiceLog log : serviceLog) {
                                        petID = log.getPet().getId();
                                        Services service = log.getService();

                                        if (petID == pID) {
                                            service.outputService();
                                        }
                                    }

                                    int sID;
                                    ServiceLog sl = null;
                                    sID = MyToys.getAnInteger("Input ID of the service that you want to remove from this pet: (integer only)", "Invalid ID!", 0);
                                    for (ServiceLog serviceLog1 : serviceLog) {
                                        if ((serviceLog1.getPet().getId() == pID) && (serviceLog1.getService().getId() == sID)) {
                                            sl = serviceLog1;
                                        }
                                    }
                                    if (sl != null) {
                                        serviceLog.remove(sl);
                                        System.out.println("Remove successfully!");
                                    } else {
                                        System.out.println("There is no service with this ID that the pet is using!");
                                    }

                                } else {
                                    System.out.println("There is no pet with this ID in the service log!");
                                }

                                break;
                            case 4:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }
                    } while (flag);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
            }

        } while (true);
    }

    static void menu() {
        System.out.println("============================MainMenu===========================");
        System.out.println("**        1. Owner management                                **");
        System.out.println("**        2. Pet management                                  **");
        System.out.println("**        3. Service management                              **");
        System.out.println("**        4. Service log management                          **");
        System.out.println("**        5. Exit                                            **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu1() {
        System.out.println("========================OwnerManagement========================");
        System.out.println("**      1. Add a new Owner                                   **");
        System.out.println("**      2. Remove an Owner                                   **");
        System.out.println("**      3. Show the list of Owner                            **");
        System.out.println("**      4. Search an Owner with ID and show their pet        **");
        System.out.println("**      5. Return to main menu                               **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");

    }

    static void menu2() {
        System.out.println("=========================PetManagement=========================");
        System.out.println("**      1. Add a new Pet                                     **");
        System.out.println("**      2. Remove a Pet                                      **");
        System.out.println("**      3. Show the list of Pet                              **");
        System.out.println("**      4. Search a Pet with ID and show their services      **");
        System.out.println("**      5. Return to main menu                               **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu3() {
        System.out.println("============================ServiceManagement============================");
        System.out.println("**1. Add a new Service                                                 **");
        System.out.println("**2. Update the price of a Service                                     **");
        System.out.println("**3. Remove a Service                                                  **");
        System.out.println("**4. Show the list of Service                                          **");
        System.out.println("**5. Search a Service with ID and show which Pet is using the Service  **");
        System.out.println("**6. Return to main menu                                               **");
        System.out.println("=========================================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu4() {
        System.out.println("=====================ServiceLogManagement======================");
        System.out.println("**      1. Add a new service log                             **");
        System.out.println("**      2. Show the list of service log                      **");
        System.out.println("**      3. Remove a service log                              **");
        System.out.println("**      4. Return to main menu                               **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");

    }

    static ArrayList<Owner> initOwner() {
        ArrayList<Owner> ds = new ArrayList<>();
        ds.add(new Owner(1, "Le Duc", "2, D4, KDC NL, PPLB , Q9, TPHCM, VN"));
        ds.add(new Owner(2, "Pham Nhat Vuong", "Landmark 81, Vinhomes, TPHCM, VN"));
        ds.add(new Owner(3, "Elon Musk", "Somewhere in the USA"));
        ds.add(new Owner(4, "Jack Ma", "Somewhere in China"));
        ds.add(new Owner(5, "Taylor Swift", "In my heart and soul"));
        ds.add(new Owner(10, "Zayn", "Somewhere in the USA"));
        ds.add(new Owner(20, "Huan Rose", "Somewhere in VN"));
        return ds;
    }

    static ArrayList<Pet> initPet() {
        ArrayList<Pet> ds = new ArrayList<>();

        OwnerList o = new OwnerList();
        o.ownerList = initOwner();

        ds.add(new Pet(1, "Hung Truong", "15/03/2019", "F", o.searchOwnerObjectByID(1)));
        ds.add(new Pet(2, "Chipu", "25/08/2020", "F", o.searchOwnerObjectByID(2)));
        ds.add(new Pet(3, "Mountain Tung", "12/10/2018", "M", o.searchOwnerObjectByID(1)));
        ds.add(new Pet(4, "Chihuahua", "07/05/2016", "F", o.searchOwnerObjectByID(3)));
        ds.add(new Pet(5, "Thomas Muller", "20/04/2020", "M", o.searchOwnerObjectByID(5)));
        ds.add(new Pet(6, "Mbappe", "31/12/2019", "M", o.searchOwnerObjectByID(4)));
        ds.add(new Pet(7, "Bergie", "01/01/2020", "M", o.searchOwnerObjectByID(1)));
        ds.add(new Pet(10, "Stray cat", "25/06/2016", "F", o.searchOwnerObjectByID(2)));
        ds.add(new Pet(20, "Weird hamster", "17/03/2017", "M", o.searchOwnerObjectByID(10)));
        return ds;
    }

    static ArrayList<Services> initService() {
        ArrayList<Services> ds = new ArrayList<>();
        ds.add(new Services(1, "Health checking", 150000));
        ds.add(new Services(2, "Hair trimming", 100000));
        ds.add(new Services(3, "Body cleaning", 200000));
        ds.add(new Services(4, "Massaging", 70000));
        ds.add(new Services(5, "Nails cutting", 80000));
        ds.add(new Services(6, "Vaccinating", 300000));
        return ds;
    }

    static ArrayList<ServiceLog> initServiceLog() {
        ArrayList<ServiceLog> ds = new ArrayList<>();

        PetList p = new PetList();
        p.petList = initPet();

        ServiceList s = new ServiceList();
        s.serviceList = initService();

        ds.add(new ServiceLog(p.searchPetObjectByID(1), s.searchServiceObjectByID(1)));
        ds.add(new ServiceLog(p.searchPetObjectByID(1), s.searchServiceObjectByID(2)));
        ds.add(new ServiceLog(p.searchPetObjectByID(2), s.searchServiceObjectByID(1)));
        ds.add(new ServiceLog(p.searchPetObjectByID(3), s.searchServiceObjectByID(3)));
        ds.add(new ServiceLog(p.searchPetObjectByID(4), s.searchServiceObjectByID(1)));
        ds.add(new ServiceLog(p.searchPetObjectByID(2), s.searchServiceObjectByID(5)));
        return ds;
    }
}
