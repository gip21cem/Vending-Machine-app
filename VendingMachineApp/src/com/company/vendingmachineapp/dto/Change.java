package com.company.vendingmachineapp.dto;

// thi class is used for change calculation
public class Change {
    private int[] coinsDue = new int[6];

    // this method calculates the change to be given to the user
    // in fifty, twenty, ten, five, two and one pence
    public int[] calculateChange (int changeDue) {
        int fifties = changeDue / CoinValue.FIFTY_PENCE.getValueOfCoins();
        int remainderOfFifties = changeDue % CoinValue.FIFTY_PENCE.getValueOfCoins();
        coinsDue[0] = fifties;
        int twenties = remainderOfFifties / CoinValue.TWENTY_PENCE.getValueOfCoins();
        int remainderOfTwenties = remainderOfFifties % CoinValue.TWENTY_PENCE.getValueOfCoins();
        coinsDue[1] = twenties;
        int tens = remainderOfTwenties / CoinValue.TEN_PENCE.getValueOfCoins();
        int remainderOfTens = remainderOfTwenties % CoinValue.TEN_PENCE.getValueOfCoins();
        coinsDue[2] = tens;
        int fives = remainderOfTens / CoinValue.FIVE_PENCE.getValueOfCoins();
        int remainderOfFives = remainderOfTens % CoinValue.FIVE_PENCE.getValueOfCoins();
        coinsDue[3] = fives;
        int twos = remainderOfFives / CoinValue.TWO_PENCE.getValueOfCoins();
        int remainderOfTwos = remainderOfFives % CoinValue.TWO_PENCE.getValueOfCoins();
        coinsDue[4] = twos;
        int ones = remainderOfTwos;
        coinsDue[5] = ones;

        return coinsDue;
    }
}
