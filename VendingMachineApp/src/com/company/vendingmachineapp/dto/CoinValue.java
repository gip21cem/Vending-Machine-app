package com.company.vendingmachineapp.dto;

// this enum contains some of the common divisions of coins
// in the British monetary system excluding 1 and 2 pounds
public enum CoinValue {
    ONE_PENCE (1), TWO_PENCE (2), FIVE_PENCE (5), TEN_PENCE (10), TWENTY_PENCE (20), FIFTY_PENCE (50);

    private final int valueOfCoins;

    private CoinValue(int valueOfCoins) { this.valueOfCoins = valueOfCoins; }

    // method that returns the enum values for change calculations
    public int getValueOfCoins() { return valueOfCoins; }
}
