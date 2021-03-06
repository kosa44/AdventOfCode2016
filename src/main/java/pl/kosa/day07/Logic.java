package main.java.pl.kosa.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Logic {
    private File file = new File("resources/inputDay07.txt");

    public void readInput() {
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                if (checkForAbba(line)) {
                    counter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }

    public boolean mirrors(String a, String b) {
        String c = reverse(b);
        if (c.equals(a)) {
            return true;
        } else
            return false;
    }

    public String reverse(String a) {
        if (a.length() != 0) {
            StringBuilder s = new StringBuilder();
            for (int i = a.length() - 1; i >= 0; i--) {
                s.append(a.charAt(i));
            }
            return String.valueOf(s);
        }
        return a;
    }

    public String[] getStringsInsideSquareBrackets(String input) {
        String inputFromFirstBracket = input.substring(input.indexOf('['));
        System.out.println(inputFromFirstBracket);
        String[] list = inputFromFirstBracket.split("\\[");
        for (int i = 0; i < list.length; i++) {
            String s = list[i];
            if (s.contains("]")) {
                String s1 = s.substring(0, s.indexOf("]"));
                list[i] = s1;
            }
        }
        return list;
    }

    public String[] getStringsOutsideSquareBrackets(String input) {
        String[] list = input.split("]");
        for (int i = 0; i < list.length; i++) {
            String s = list[i];
            if (s.contains("[")) {
                String s1 = s.substring(0, s.indexOf('['));
                list[i] = s1;
            }
        }
        return list;
    }

    public boolean checkForAbbaOutside(String s1) {
        String[] strings = getStringsOutsideSquareBrackets(s1);
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            if (s.length() != 0) {
                for (int j = 0; j < s.length() - 3; j++) {
                    StringBuilder a = new StringBuilder();
                    a.append(s.charAt(j));
                    a.append(s.charAt(j + 1));
//                    System.out.println(a);
                    StringBuilder b = new StringBuilder();
                    b.append(s.charAt(j + 2));
                    b.append(s.charAt(j + 3));
//                    System.out.println(b);
                    if (mirrors(String.valueOf(a), String.valueOf(b))) {
                        if (a.charAt(0) != (a.charAt(1))) {
                            return true;
                        }
                    }
                };
            }
        }
        return false;
    }

    public boolean checkForAbbaInside(String s1) {
        String[] strings = getStringsInsideSquareBrackets(s1);
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            if (s.length() != 0) {
                for (int j = 0; j < s.length() - 3; j++) {
                    StringBuilder a = new StringBuilder();
                    a.append(s.charAt(j));
                    a.append(s.charAt(j + 1));
//                    System.out.println(a);
                    StringBuilder b = new StringBuilder();
                    b.append(s.charAt(j + 2));
                    b.append(s.charAt(j + 3));
//                    System.out.println(b);
                    if (mirrors(String.valueOf(a), String.valueOf(b))) {
                        if (a.charAt(0) != (a.charAt(1))) {
                            return true;
                        }
                    }
                };
            }
        }
        return false;
    }

    public boolean checkForAbba(String s1) {
        // check
        if (checkForAbbaInside(s1)) {
            return false;
        } else if (!checkForAbbaInside(s1)) {
            if (checkForAbbaOutside(s1)) {
                return true;
            } else if (!checkForAbbaOutside(s1)) {
                return false;
            }
        }
        return false;
    }
}
