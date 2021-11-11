package com.company.vendingmachineapp.controller;

import com.company.vendingmachineapp.dao.*;
import com.company.vendingmachineapp.dto.VendingMachineItem;
import com.company.vendingmachineapp.view.VendingMachineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class VendingMachineAppController {
    private VendingMachineAppDAO myDAO;
    private VendingMachineView myView;

    @Autowired
    public VendingMachineAppController(VendingMachineAppDAO myDAO, VendingMachineView myView) {
        this.myDAO = myDAO;
        this.myView = myView;
    }

    // this method runs the entire application
    // by calling the appropriate methods in other classes
    public void run() {
        // call method in VendingMachineAppDAOImpl
        // to load in memory the items from the inventory file
        List<VendingMachineItem> itemsInInventory = myDAO.loadItemsFromInventory();

        // call the method from VendingMachineView
        // to display the item menu
        myView.displayItemsInInventory(itemsInInventory);

        // store user's selection
        String yesOrNo = myView.promptUserAndReturnUserSelection("\nWould you like to buy something (Yes or No)? ");

        if(yesOrNo.equalsIgnoreCase("no")) {
            System.out.println("Bye!");
            System.exit(0);
        } else { // proceed with the sale
            String insertedAmount = myView.promptUserAndReturnUserSelection("Please insert coins: ");

            // this arrayList stores the item IDs
            // to be used for comparison with the user's selected item
            List<String> itemIDs = new ArrayList<>();
            for(VendingMachineItem item : itemsInInventory) {
                itemIDs.add(String.valueOf(item.getItemID()));
            }

            String selectedItemID = myView.promptUserAndReturnUserSelection("Thanks. Select an item from the list above: ");

            int IDConversion = 0;
            // deal with the FormatException
            try {
                IDConversion = Integer.parseInt(selectedItemID);
            } catch (NumberFormatException exc) {
                System.out.println("Wrong input");
                myView.wrongInput();
            }

            int indexForSearchInList = IDConversion-1;

            if(indexForSearchInList < 0 || indexForSearchInList >= itemsInInventory.size()) {
                // selected number not in the item list
                // avoid IndexOutOFBoundsException to be thrown
                System.out.println("Sorry item not in the list");
                myView.wrongInput();
            }

            VendingMachineItem selectedItem =  itemsInInventory.get(indexForSearchInList);

            // the user selected an existing item from the list
            if(itemIDs.contains(selectedItemID)) {
                try {
                    // call method from VendingMachineAppDAOImpl
                    // to complete the transaction
                    // and deals with the thrown exceptions
                    myDAO.vendingTransaction(itemsInInventory, Integer.parseInt(selectedItemID), Integer.parseInt(insertedAmount));
                } catch (InsufficientFundsException exc) {
                    System.out.println(exc.getMessage() + "\nThe price of the selected item is " + selectedItem.getItemPrice() + " pence\nYou entered only " + insertedAmount + " pence");
                    myView.wrongInput();
                } catch(NoItemInventoryException exc) {
                    System.out.println(exc.getMessage());
                    myView.wrongInput();
                }

                // transaction completed successfully
                // call method from VendingMachineView
                // to display given change
                int changeDue = Integer.parseInt(insertedAmount) - selectedItem.getItemPrice();
                myView.displayChangeDue(changeDue);
            }

            System.out.println("Thanks for your purchase. Bye!");
        }

        // call method in VendingMachineAppDAOImpl
        // to save items back to inventory file
        myDAO.saveItemsInInventory(itemsInInventory);
    }
}
