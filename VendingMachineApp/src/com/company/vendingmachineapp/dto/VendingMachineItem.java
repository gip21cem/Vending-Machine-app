package com.company.vendingmachineapp.dto;

// this class represents the DTO item in the vending machine
public class VendingMachineItem {
    private int itemID;
    private String itemName;
    private int itemPrice;
    private int itemNumberOfUnits;

    public VendingMachineItem(int itemID, String itemName, int itemPrice, int itemNumberOfPieces) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemNumberOfUnits = itemNumberOfPieces;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemNumberOfUnits() {
        return itemNumberOfUnits;
    }

    public void setItemNumberOfUnits(int itemNumberOfPieces) {
        this.itemNumberOfUnits = itemNumberOfPieces;
    }
}
