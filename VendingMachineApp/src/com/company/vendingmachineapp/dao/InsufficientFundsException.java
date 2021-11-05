package com.company.vendingmachineapp.dao;

// exception to be thrown when the user tries to purchase
// an item but doesn't deposit enough money
public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message) {

        // message passed as argument of superclass constructor call
        // will be displayed as the exception message when it occurs
        super(message);
    }
}
