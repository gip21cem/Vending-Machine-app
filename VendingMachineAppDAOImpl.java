package com.company.vendingmachineapp.dao;

import com.company.vendingmachineapp.dto.VendingMachineItem;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VendingMachineAppDAOImpl implements VendingMachineAppDAO{

    private static final List<VendingMachineItem> inventoryList = new ArrayList<>();
    private static final File inventoryFile = new File("F:\\Mthree\\VendingMachineApp\\vendingMachineInventory.txt");
    private VendingMachineAuditLog auditLog = new VendingMachineAuditLogImpl();

    /*
     * This method reads the items in the inventory file
     * and stores them in memory as values of an arrayList
     */
    @Override
    public List<VendingMachineItem> loadItemsFromInventory() {

        // clear the arrayList to avoid duplication of items
        inventoryList.clear();

        try(FileReader input = new FileReader(inventoryFile)) {
            BufferedReader lineReader = new BufferedReader(input);
            String currentLine;
            String[] dataInCurrentLine;

            // Read the file contents line by line until the end
            while ((currentLine = lineReader.readLine()) != null) {
                // Store in array individual bits of info
                dataInCurrentLine = currentLine.split(",");
                // extract the values in the array
                String itemID = dataInCurrentLine[0].trim();
                String itemName = dataInCurrentLine[1].trim();
                String itemPrice = dataInCurrentLine[2].trim();
                String itemQuantity = dataInCurrentLine[3].trim();
                // create a new Item and add it to the arrayList
                VendingMachineItem currentItem =  new VendingMachineItem(Integer.parseInt(itemID), itemName, Integer.parseInt(itemPrice), Integer.parseInt(itemQuantity));
                inventoryList.add(currentItem);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        return inventoryList;
    }

    // this method writes the items in the arrayList
    // back into the inventory file
    @Override
    public void saveItemsInInventory(List<VendingMachineItem> inventoryList) {
        try(FileWriter output = new FileWriter(inventoryFile)) {
            BufferedWriter lineWriter = new BufferedWriter(output);
            // write the file line by line
            for(VendingMachineItem item : inventoryList) {
                lineWriter.write(item.getItemID() + "," + item.getItemName() + "," + item.getItemPrice() + "," + item.getItemNumberOfUnits() + "\n");
            }
            lineWriter.flush();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    /*
     * this method executes the vending transaction;
     * it throws both exceptions
     * declared by the implemented interface;
     * it updates the inventory level of the vended item
     */
    @Override
    public void vendingTransaction(List<VendingMachineItem> inventoryList, int itemSelectedFromList, int amountEntered) throws InsufficientFundsException, NoItemInventoryException{
        VendingMachineItem selectedItem = inventoryList.get(itemSelectedFromList-1);
        int priceOfSelectedItem = selectedItem.getItemPrice();
        int inventoryLevelOfSelectedItem = selectedItem.getItemNumberOfUnits();

        if(amountEntered < priceOfSelectedItem) {
            throw new InsufficientFundsException("The money you entered is not enough");
        }

        if(inventoryLevelOfSelectedItem <= 0) {
            throw new NoItemInventoryException("Sorry the selected item is not available");
        }

        // no exception was thrown: complete transaction
        // and update vended item inventory level
        int updatedInventoryLevel = inventoryLevelOfSelectedItem - 1;
        selectedItem.setItemNumberOfUnits(updatedInventoryLevel);

        // call method in VendingMachineAuditLogImpl
        // to write a new entry to the audit log file
        auditLog.auditLogWriter("new transaction. Item ID: " + selectedItem.getItemID());
    }
}
