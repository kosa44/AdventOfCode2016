package main.java.pl.kosa.day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class Room {
    private ArrayList<String> arrayOfChars = new ArrayList<>();
    private File file = new File("resources/inputDay04.txt");

    public void executeInstruction() {
        int sumOfSectorIDs = 0;
        int lineCounter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String s = line;
                lineCounter++;
                boolean isReal = checkIfRoomIsReal(s);
                System.out.println(isReal);
                System.out.println(lineCounter);
                if (isReal) {
                    sumOfSectorIDs = sumOfSectorIDs + selectSectorID(s);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sumOfSectorIDs);
    }

    public void executeInstruction2() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String s = line;
                String current = decrypt(line);
                if (current.contains("northpole")) {
                    System.out.println(current);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int findFirstNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                return i;
            }
        }
        System.out.println("Invalid format");
        return 0;
    }

    public int selectSectorID(String s) {
        String[] array = s.split(Pattern.quote("["));
        String sub = array[0];
        return Integer.valueOf(sub.substring(sub.length() - 3, sub.length()));
    }

    public String selectControlSum(String s) {
        String[] array = s.split(Pattern.quote("["));
        String sub = array[1];
        sub = sub.substring(0, sub.length() - 1);
        return sub;
    }

    public String selectLettersOnly(String s) {
        String sub = s.substring(0, findFirstNumber(s));

        sub = sub.replace("-", "");
        return sub;
    }
    //try hashmap
    public void countLetters(String s) {
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (arrayOfChars.size() != 0) {
                int numberOfOccurences = 0;
                for (int j = 0; j < arrayOfChars.size(); j++) {
                    if (currentChar == arrayOfChars.get(j).charAt(0)) {
                        numberOfOccurences = +1;
                        String updatedString = arrayOfChars.get(j) + String.valueOf(currentChar);
                        arrayOfChars.set(j, updatedString);
                    }
                }
                if (numberOfOccurences == 0) {
                    arrayOfChars.add(String.valueOf(currentChar));
                }
            } else
                arrayOfChars.add(String.valueOf(currentChar));
        }
        Collections.sort(arrayOfChars, new DescendingStringLengthComparator());
    }

    public String selectLettersToCompareWithControlSum() {
        String letters = "";
        for (int i = 0; i < arrayOfChars.size(); i++) {
            letters = letters + arrayOfChars.get(i).charAt(0);
        }
        return letters.substring(0, 5);
    }

    public boolean checkIfRoomIsReal(String s1) {
        String s = selectLettersOnly(s1);
        countLetters(s);
        String letters = selectLettersToCompareWithControlSum();
        String controlSum = selectControlSum(s1);
        if (letters.equals(controlSum)) {
            arrayOfChars.clear();
            return true;
        } else
            arrayOfChars.clear();
        return false;
    }

    public void printCountedLetters() {
        for (int i = 0; i < arrayOfChars.size(); i++) {
            System.out.println(arrayOfChars.get(i));
        }
    }

    public String decrypt(String s) {
        int end = findFirstNumber(s);
        String s1 = s.substring(0, end);
        int ID = selectSectorID(s);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == '-') {
                sb.append(' ');
            } else if (s1.charAt(i) != '-') {
                int a = (int) s.charAt(i);
                int b = ((a+ID)-97)%26;
                char c = (char) (b+97);
                sb.append(c);
            }
        }
        sb.append(ID);
        String s2 = sb.toString();
        return s2;
    }
}

