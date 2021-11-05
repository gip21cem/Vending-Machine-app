package com.company.vendingmachineapp.dao;

import com.company.vendingmachineapp.dto.VendingMachineItem;
import java.util.List;

/*
 * this interface contains the methods that must be implemented
 * by any class that acts as DAO in the application
 */
public interface VendingMachineAppDAO {
    List<VendingMachineItem> loadItemsFromInventory();
    void saveItemsInInventory(List<VendingMachineItem> inventoryList);
    void vendingTransaction(List<VendingMachineItem> inventoryList, int itemSelectedFromList, int amountEntered) throws InsufficientFundsException, NoItemInventoryException;
}
