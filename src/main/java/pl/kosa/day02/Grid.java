package main.java.pl.kosa.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grid {
    private int xLength;
    private int yLength;
    private int currentLocation;

    private ArrayList<Integer> locations = new ArrayList<Integer>();

    public Grid(int x, int y) {
        this.xLength = x;
        this.yLength = y;

        int square = xLength * yLength;

        for (int i = 1; i <= square; i++) {
            locations.add(i);
        }
    }

    public void printGrid() {
        for (int i = 0; i < locations.size(); i++) {
            int temp = locations.get(i);
            System.out.print(temp);
            if (temp % xLength == 0) {
                System.out.print("\n");
            }
        }
    }

    public void setCurrentLocation(int i) {
        currentLocation = i;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public int followInstruction(String s) {
        for (int i = 0; i < s.length(); i++) {
            char direction = s.charAt(i);
            if (direction == 'U') {
                stepUp();
            } else if (direction == 'D') {
                stepDown();
            } else if (direction == 'L') {
                stepLeft();
            } else if (direction == 'R') {
                stepRight();
            }
        }
        return getCurrentLocation();
    }

    public String findPassword() {
        StringBuilder password = new StringBuilder();
            File file = new File("resources/inputDay02.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null) {
                password.append(followInstruction(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password.toString();

    }

    public void stepUp() {
        if (currentLocation - xLength > 0) {
            currentLocation -= xLength;
        }
    }

    public void stepDown() {
        if (currentLocation + xLength <= locations.size()) {
            currentLocation += xLength;
        }
    }

    public void stepLeft() {
        if ((currentLocation - 1) % xLength != 0) {
            currentLocation -= 1;
        }
    }

    public void stepRight() {
        if (currentLocation % xLength != 0) {
            currentLocation += 1;
        }
    }
}

