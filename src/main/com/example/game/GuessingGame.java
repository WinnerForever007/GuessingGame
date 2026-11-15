package com.example.game;

import java.util.Random;

public class GuessingGame {
    private final int randomNumber;
    private int attempts;

    public GuessingGame() {
        this(new Random());
    }

    public GuessingGame(Random random) {
        this.randomNumber = random.nextInt(10) + 1;
    }

    public String guess(int guessedNumber) {
        if(attempts > 3){
            attempts++;
            return "Game over " + getRandomNumber();
        }
        if(guessedNumber > getRandomNumber()) {
            attempts++;
            return "Too high";
        }
        if(guessedNumber < getRandomNumber()) {
            attempts++;
            return "Too low";
        }

        return "You got it";
    }

    public int getRandomNumber() {
        return randomNumber;
    }
}
