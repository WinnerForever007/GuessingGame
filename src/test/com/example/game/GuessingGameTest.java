package com.example.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuessingGameTest {

    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }

    @Test
    void testSimpleWinSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum);
        assertEquals("You got it", message);
    }

    @Test
    void testOneWrongNegGuessSituation() {
        String message = game.guess(-5);
        assertEquals("Too low", message);
    }

    @Test
    void testOneWrongPosGuessSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum + 1);
        assertEquals("Too high", message);
    }

    @RepeatedTest(10)
    void testRandomNumberGeneration() {
        int[] randomNumCount = new int[11];
        for(int counter = 0; counter < 200; counter++) {
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
//        if(rand == 10) {
//            rand = 0;
//        } else {
//            rand++;
//        }
        int rand2 = rand;
        rand2++;
//        if(rand2 == 10) {
//            rand2 = 0;
//        } else {
//            rand2++;
//        }
        assertEquals("Too high", game.guess(rand));
        assertEquals("Too high", game.guess(rand2));

    }

    //Exercise 6
    @Test
    void testTooHighGuessSituation() {
        int randomNum = game.getRandomNumber();
        randomNum += 3;
        String message = game.guess(randomNum);
        assertEquals("Too high", message);
    }

    //Exercise 6
    @Test
    void testTooLowGuessSituation() {
        int randomNum = game.getRandomNumber();
        randomNum -= 3;
        String message = game.guess(randomNum);
        assertEquals("Too low", message);
    }

    //Exercise 7
    @Test
    void testGameOverGuessSituation() {
        int randomNum = game.getRandomNumber();
        randomNum++;
        for(int i = 0; i < 4; i++) {
            String message = game.guess(randomNum++);
            assertEquals("Too high", message);
        }
        String message = game.guess(randomNum++);
        assertEquals("Game over " + game.getRandomNumber(), message);
    }

    //Exercise 10
    @Test
    void testControlledRandomnessGuessSituation() {
        GuessingGame localGame = new GuessingGame(new Random(42));
        assertEquals(1, localGame.getRandomNumber());
        assertEquals("You got it", localGame.guess(1));
    }

}
