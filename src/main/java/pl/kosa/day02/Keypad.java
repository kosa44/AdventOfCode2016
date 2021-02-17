package main.java.pl.kosa.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Keypad {
    private String[] row = {" ", " ", "1", " ", " "};
    private String[] row2 = {" ", "2", "3", "4", " "};
    private String[] row3 = {"5", "6", "7", "8", "9"};
    private String[] row4 = {" ", "A", "B", "C", " "};
    private String[] row5 = {" ", " ", "D", " ", " "};
    private String[][] array = {row, row2, row3, row4, row5};
    private int currentRow = 2;
    private int currentColumn = 0;
    private String currentPosition = array[currentRow][currentColumn];
    private File file = new File("resources/inputDay02.txt");

    public String readInput() {
        StringBuilder password = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            char character = ' ';
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    move(line.charAt(i));
                }
                password.append(currentPosition);
            }
            System.out.println(password.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password.toString();
    }

    public void moveRight() {
        // check if you're getting row length or column length;
        if (!(currentColumn >= array[0].length - 1)) {
            if (!array[currentRow][currentColumn + 1].equals(" ")) {
                currentColumn = currentColumn + 1;
                currentPosition = array[currentRow][currentColumn];
            }
        }
    }

    public void moveLeft() {
        if (currentColumn > 0) {
            if (!array[currentRow][currentColumn - 1].equals(" ")) {
                currentColumn = currentColumn - 1;
                currentPosition = array[currentRow][currentColumn];
            }
        }
    }

    public void moveUp() {
        if (currentRow > 0) {
            if (!array[currentRow - 1][currentColumn].equals(" ")) {
                currentRow = currentRow - 1;
                currentPosition = array[currentRow][currentColumn];
            }
        }
    }

    public void moveDown() {
        if (!(currentRow >= array[0].length - 1)) {
            if (!array[currentRow + 1][currentColumn].equals(" ")) {
                currentRow = currentRow + 1;
                currentPosition = array[currentRow][currentColumn];
            }
        }
    }

    public void move(char c) {
        if (c == 'U') {
            moveUp();
        } else if (c == 'D') {
            moveDown();
        } else if (c == 'R') {
            moveRight();
        } else if (c == 'L') {
            moveLeft();
        }
    }

    public void printKeypad() {
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    public void printCurrentPosition() {
        System.out.println(currentPosition);
    }
}
