package com.example.game;

public class GuessingGameConsole {
    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        boolean loopsShouldContinue = true;
        do {
            String input = System.console().readLine("Enter a number: ");
            if("q".equals(input)) {
                return;
            }
            if(!input.matches("-?\\d{1,2}")) {
                System.out.println("Please enter a number.");
                continue;
            }
            String output = game.guess(Integer.parseInt(input));
            System.out.println(output);
            if(game.isGameOver()) {
                loopsShouldContinue = false;
            }
        } while (loopsShouldContinue);

    }
}
