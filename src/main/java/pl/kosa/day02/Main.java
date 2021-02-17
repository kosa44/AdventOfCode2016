package main.java.pl.kosa.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(3, 3);
        grid.setCurrentLocation(5);
        System.out.println("Part 1: "+grid.findPassword());
        Keypad keypad = new Keypad();
        System.out.println("Part 2: "+keypad.readInput());
    }
}




