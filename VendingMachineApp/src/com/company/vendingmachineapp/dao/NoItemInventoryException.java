package com.company.vendingmachineapp.dao;

// exception to be thrown when the user tries
// to purchase an item that has zero inventory
public class NoItemInventoryException extends Exception {
    public NoItemInventoryException(String message) {
        super(message);
    }
}
