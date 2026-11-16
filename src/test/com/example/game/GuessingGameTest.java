package com.example.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GuessingGameTest {

    private GuessingGame game;
    private static final int GAME_RANDOMNESS_TRIES = 200;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }

    @Test
    void testSimpleWinSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum);
        assertEquals("You got it in 1 try", message);
    }

    @Test
    void testOneWrongNegGuessSituation() {
        String message = game.guess(-5);
        assertEquals("You didn't get it - you're too low", message);
    }

    @Test
    void testOneWrongPosGuessSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum + 1);
        assertEquals("You didn't get it - you're too high", message);
    }

    @RepeatedTest(10)
    void testRandomNumberGeneration() {
        int[] randomNumCount = new int[11];
        for(int counter = 0; counter < GAME_RANDOMNESS_TRIES; counter++) {
            GuessingGame localGame = new GuessingGame();
            int randomNum = localGame.getRandomNumber();
            assertTrue(randomNum >= 1 && randomNum <= 10);
            randomNumCount[randomNum] = 1;
        }

        int sum = 0;
        for(int counter = 0; counter < randomNumCount.length; counter++) {
            sum += randomNumCount[counter];
        }

        assertEquals(10, sum);
    }

    //Exercise 5
    @Test
    void testTwoWrongGuessesInARow() {
        //GuessingGame localGame = new GuessingGame();
        int rand = game.getRandomNumber();
        rand++;
        int rand2 = rand;
        rand2++;
        assertEquals("You didn't get it - you're too high", game.guess(rand));
        assertEquals("You didn't get it - you're too high", game.guess(rand2));

    }

    //Exercise 6
    @Test
    void testTooHighGuessSituation() {
        int randomNum = game.getRandomNumber();
        randomNum += 3;
        String message = game.guess(randomNum);
        assertEquals("You didn't get it - you're too high", message);
    }

    //Exercise 6
    @Test
    void testTooLowGuessSituation() {
        int randomNum = game.getRandomNumber();
        randomNum -= 3;
        String message = game.guess(randomNum);
        assertEquals("You didn't get it - you're too low", message);
    }

    //Exercise 7
    @Test
    void testGameOverGuessSituation() {
        makeThreeWrongGuesses();
        game.guess(-3);
        String message = game.guess(-3);
        assertEquals("Sorry, you are limited to only 4 tries. Your game is over.", message);
    }

    //Exercise 10
    @Test
    void testControlledRandomnessGuessSituation() {
        GuessingGame localGame = new GuessingGame(new Random(42));
        assertEquals(1, localGame.getRandomNumber());
        assertEquals("You got it in 1 try", localGame.guess(1));
    }

    @Test
    void testThreeWrongGuessesAndOneCorrect() {
        makeThreeWrongGuesses();
        int correctAnswer = game.getRandomNumber();
        String message = game.guess(correctAnswer);
        assertEquals("You got it in 4 tries", message);
    }

    @Test
    void testWrongGuesses() {
        for(int i = 0; i < 9; i++) {
            game.guess(-3);
        }
        String message = game.guess(-3);
        assertEquals("Sorry, you are limited to only 4 tries. " +
                "Your game is over.", message);
    }

    @Test
    void testTwoWrongGuessesAndOneCorrect() {
        game.guess(-3);
        game.guess(-3);
        String message = game.guess(game.getRandomNumber());
        assertTrue(message.contains("3 tries"), "Should indicate three tries");
        assertTrue(message.contains("You got it"), "Should indicate that" +
                "we got the right number");
    }

    //Exercise 3.2
    @Test
    void testFourWrongGuesses() {
        makeThreeWrongGuesses();
        String message = game.guess(-3);
        assertEquals("You didn't get it and you've had 4 tries. Game over.",
                message);
    }

    //Exercise 3.3
    @Test
    void testFourWrongGuessesAndOneCorrect() {
        makeThreeWrongGuesses();
        game.guess(-3);
        String message = game.guess(game.getRandomNumber());
        assertEquals("Sorry, you are limited to only 4 tries. Your game is over.",
                message);
    }

    //Exercise 3.4
    @Test
    void testIsGameOver() {
        assertFalse(game.isGameOver());
        game.guess(game.getRandomNumber());
        assertTrue(game.isGameOver());

        GuessingGame game2 = new GuessingGame();
        game2.guess(-3);
        game2.guess(-3);
        game2.guess(-3);
        assertFalse(game2.isGameOver());
        game2.guess(-3);
        assertTrue(game2.isGameOver());
    }

    //Exercise 3.6
    @Test
    void testAssertionMessages() {
        game.guess(-3);
        game.guess(-3);
        String message = game.guess(game.getRandomNumber());

//        assertTrue(message.contains("3 tries"), "Error with attempts");
//        assertTrue(message.contains("You got it"), "Error in a message");

        assertAll(
                () -> assertTrue(message.contains("3 tries"), "Error with attempts"),
                () -> assertTrue(message.contains("You got it"), "Error in a message")
        );
    }

    //Exercise 3.7
    @Test
    void testFiveWithNoAssertion() {
        game.guess(5);
    }

    //Exercise 3.11
    @Test
    void testZeroDoesNotWin() {
        String message = game.guess(0);
        assertFalse(message.contains("You got it"), "0 will never win");
        assertTrue(message.contains("too low"), "Zero is too low");
    }

    private void makeThreeWrongGuesses() {
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
    }

}
