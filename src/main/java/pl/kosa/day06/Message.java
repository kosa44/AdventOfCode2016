package main.java.pl.kosa.day06;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Message {
    private File file = new File("resources/inputDay06.txt");
    private ArrayList<String> input = new ArrayList<>();
    private ArrayList<Character> arrayOfLetters = new ArrayList<>();
    private HashMap<Character, Integer> map = new HashMap<>();

    public void executeInstruction() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        decodeMessage(input);
    }

    public void populateArrayOfLetters(ArrayList<String> input) {
        for (int i = 0; i < input.get(0).length(); i++) {
            for (int j = 0; j < input.size(); j++) {
                char c = input.get(j).charAt(i);
                if (!arrayOfLetters.contains(c)) {
                    arrayOfLetters.add(c);
                }
            }
        }
    }

    public void populateHashMap(ArrayList<Character> arrayOfLetters) {
        for (int i = 0; i < arrayOfLetters.size(); i++) {
            map.put(arrayOfLetters.get(i), 0);
        }
    }

    public Character getCharWithMaxValue(HashMap<Character, Integer> map) {
        Map.Entry<Character, Integer> maxEntry = null;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }

    public Character getCharWithMinValue(HashMap<Character, Integer> map) {
        Map.Entry<Character, Integer> minEntry = null;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0) {
                minEntry = entry;
            }
        }
        return minEntry.getKey();
    }

    public String decodeMessage(ArrayList<String> input) {
        //arrayOfLetters and HashMap could be local
        populateArrayOfLetters(input);
        String decoded = "";
        for (int i = 0; i < input.get(0).length(); i++) {
            if (!map.isEmpty()) {
                map.clear();
            }
            populateHashMap(arrayOfLetters);
            for (int j = 0; j < input.size(); j++) {
                char c = input.get(j).charAt(i);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                }
            }
            //use getCharWithMaxValue for part1
            //use getCharWithMinValue for part2
            decoded = decoded + getCharWithMinValue(map);
        }
        System.out.println(decoded);
        return decoded;
    }
}
