package com.example.game;

import java.util.Random;

public class GuessingGame {
    private static final int MAX_TRIES = 4;
    private final int randomNumber;
    private int attempts;
    private boolean gameWon;



    public GuessingGame() {
        this(new Random());
    }

    public GuessingGame(Random random) {
        this.randomNumber = random.nextInt(10) + 1;
    }

    public String guess(int guessedNumber) {
        attempts++;
        String tryText = attempts == 1 ? "try" : "tries";
        String winningMsg = String.format("You got it in %d %s", attempts, tryText);

        String response;
        if(attempts == MAX_TRIES && guessedNumber != getRandomNumber()){
            response = String.format("You didn't get it and you've had %d %s. Game over.",
                    attempts, tryText);
        } else if(attempts > MAX_TRIES) {
            response = "Sorry, you are limited to only 4 tries. Your game is over.";
        } else {
            String tooHighLowText;
            if(guessedNumber < getRandomNumber()) {
                tooHighLowText = "- you're too low";
            } else if(guessedNumber > getRandomNumber()) {
                tooHighLowText = "- you're too high";
            } else {
                tooHighLowText = "";
            }
            String loseText = String.format("You didn't get it %s", tooHighLowText).strip();
            response = guessedNumber == getRandomNumber() ? winningMsg : loseText;
            if(response.equals(winningMsg)) {
                gameWon = true;
            }
        }

        return response;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public boolean isGameOver() {
        return gameWon || attempts >= MAX_TRIES;
    }
}
