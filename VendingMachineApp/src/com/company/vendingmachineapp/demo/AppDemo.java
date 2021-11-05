package com.company.vendingmachineapp.demo;

import com.company.vendingmachineapp.controller.VendingMachineAppController;
import com.company.vendingmachineapp.dao.*;
import com.company.vendingmachineapp.view.VendingMachineView;


public class AppDemo {
    public static void main (String[] args) throws NoItemInventoryException {
        VendingMachineAppDAO myImpl = new VendingMachineAppDAOImpl();
        VendingMachineView myView = new VendingMachineView();

        // constructor based dependency injection
        // the first parameter of controller constructor is of type interface
        // the argument passed is of the type of the class that implements that interface
        VendingMachineAppController myCtrl = new VendingMachineAppController(myImpl, myView);

        // call the method in VendingMachineAppController
        // to run the full application
        myCtrl.run();
    }
}
