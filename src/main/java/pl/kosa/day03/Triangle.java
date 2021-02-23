package main.java.pl.kosa.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Triangle {
    private File file = new File("resources/inputDay03.txt");
    private int counter = 0;
    public void executeInstructionPt1() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                readInputLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }

    public void executeInstructionPt2() {
        int columnCounter = 0;
        int trueCounter = 0;
        while (columnCounter < 3) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                ArrayList<Integer> list = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    list.add(getTriangleSide(line, columnCounter));
                    if (list.size() == 3) {
                        if (canConstructTriangle(list.get(0), list.get(1), list.get(2))) {
                            trueCounter++;
                        }
                        list.clear();
                    }
                }
                columnCounter++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(trueCounter);
    }

    public int getTriangleSide(String line, int column) {
        int denominator = 3;
        String[] arr = {line.substring(0, (line.length()/denominator)).trim(), line.substring(((line.length()/denominator)+1), ((line.length()/denominator)*2)).trim(), line.substring(((line.length()/denominator)*2)+1).trim()};
        return Integer.valueOf(arr[column]);

    }

    public void readInput(String line, int column) {

    }

    public void readInputLine(String line) {
        int denominator = 3;
        String[] arr = {line.substring(0, (line.length()/denominator)).trim(), line.substring(((line.length()/denominator)+1), ((line.length()/denominator)*2)).trim(), line.substring(((line.length()/denominator)*2)+1).trim()};
        int one = Integer.valueOf(arr[0]);
        int two = Integer.valueOf(arr[1]);
        int three = Integer.valueOf(arr[2]);
        if (canConstructTriangle(one, two, three)) {
            counter++;
        }
    }

    public boolean canConstructTriangle(int one, int two, int three) {
        int longest = one;
        if (two > longest) {
            longest = two;
        }
        if (three > longest) {
            longest = three;
        }

        return one + two + three - longest > longest;
    }
}

