package com.company.vendingmachineapp.demo;

import com.company.vendingmachineapp.controller.VendingMachineAppController;
import com.company.vendingmachineapp.dao.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppDemo {
    public static void main (String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        // tell Spring where to start looking for annotation
        appContext.scan("com.company.vendingmachineapp");
        appContext.refresh();

        // use appContext to instantiate the application controller
        VendingMachineAppController myCtrl = appContext.getBean("vendingMachineAppController", VendingMachineAppController.class);
        myCtrl.run();

    }
}
