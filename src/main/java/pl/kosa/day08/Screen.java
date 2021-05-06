package main.java.pl.kosa.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Screen {
    private char[][] array;
    private int width;
    private int height;
    private File file = new File("resources/inputDay08.txt");

    public Screen(int height, int width) {
        this.width = width;
        this.height = height;
        this.array = new char[height][width];
        setArrayToDots();
    }

    public void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("rotate")) {
                    String[] strings = line.split(" ");
                    if (strings[1].equals("column")) {
                        String[] columnNumber = strings[2].split("=");
                        rotateColumn(Integer.valueOf(columnNumber[1]), Integer.valueOf(strings[4]));
                    } else if (strings[1].equals("row")) {
                        String[] rowNumber = strings[2].split("=");
                        rotateRow(Integer.valueOf(rowNumber[1]), Integer.valueOf(strings[4]));
                    }
                } else if (line.startsWith("rect")) {
                    String[] strings = line.split(" ");
                    String[] numbers = strings[1].split("x");
                    rect(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1]));
                } else if (!(line.startsWith("rotate") || !(line.startsWith("rect")))) {
                    System.out.println("Unknown command");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setArrayToDots() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = '.';
            }
        }
    }

    public void printArray() {
        System.out.println("");
        for (int i = 0; i < height; i++) {
            if (i != 0) {
                System.out.print("\n");
            }
            for (int j = 0; j < width; j++) {
                System.out.print(array[i][j]);
            }
        }
    }

    public void rect(int a, int b) {
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                array[i][j] = '#';
            }
        }
    }

    public void rotateRow(int row, int number) {
        char[] beforeRotation = new char[width];
        for (int i = 0, j = width - number; i < width; i++, j++) {
            if (j < width) {
                beforeRotation[i] = array[row][j];
            } else
                beforeRotation[i] = array[row][i - number];
        }
        for (int i = 0; i < width; i++) {
            array[row][i] = beforeRotation[i];
        }
    }

    public void rotateColumn(int column, int number) {
        char[] beforeRotation = new char[height];
        for (int i = 0, j = height - number; i < height; i++, j++) {
            if (j < height) {
                beforeRotation[i] = array[j][column];
            } else
                beforeRotation[i] = array[i - number][column];
        }
        for (int i = 0; i < height; i++) {
            array[i][column] = beforeRotation[i];
        }
    }

    public int countPixelsOn() {
        int number = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (array[i][j] == '#') {
                    number++;
                }
            }
        }
        return number;
    }
}
