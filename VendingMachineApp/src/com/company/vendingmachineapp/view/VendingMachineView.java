package com.company.vendingmachineapp.view;

import com.company.vendingmachineapp.dao.VendingMachineAppDAOImpl;
import com.company.vendingmachineapp.dto.*;
import java.util.List;
import java.util.Scanner;

@Component
public class VendingMachineView {

    VendingMachineAppDAOImpl myImpl = new VendingMachineAppDAOImpl();
    Scanner scan = new Scanner(System.in);

    public void displayItemsInInventory(List<VendingMachineItem> itemsInList) {

        System.out.println("**** ITEM MENU ****");

        for(VendingMachineItem item : itemsInList) {
            int itemInventoryLevel = item.getItemNumberOfUnits();
            if(itemInventoryLevel > 0)
                System.out.println("#" + item.getItemID() + " " + item.getItemName() + " " + item.getItemPrice() + " pence");
            else
                System.out.println("#" + item.getItemID() + " " + item.getItemName() + " NOT AVAILABLE");
        }
    }

    public String promptUserAndReturnUserSelection(String messagePrompt) {
        System.out.print(messagePrompt);
        String userSelection = scan.nextLine();
        return userSelection;
    }

    public void displayChangeDue(int changeDue) {
        int[] coinsDue = new Change().calculateChange(changeDue);

        System.out.println("Your change is " + coinsDue[0] + " fifty pence, " + coinsDue[1] + " twenty pence, " + coinsDue[2] + " ten pence, " + coinsDue[3] + " five pence, " + coinsDue[4] + " two pence and " + coinsDue[5] + " one pence");
    }

    // default message on wrong input occurrence
    public void wrongInput() {
        System.out.println("Here is your money back. Bye!");
        System.exit(0);
    }
}
