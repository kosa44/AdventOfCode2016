package main.java.pl.kosa.day08;

public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen(6,50);
        screen.readInput();
        screen.printArray();
        System.out.println("\n"+screen.countPixelsOn());
    }
}
