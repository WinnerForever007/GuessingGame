package com.example.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

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
        assertEquals("You didn't get it", message);
    }

    @Test
    void testOneWrongPosGuessSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum + 1);
        assertEquals("You didn't get it", message);
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

}
