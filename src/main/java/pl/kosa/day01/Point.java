package day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Point {
    private int positionX;
    private int positionY;

    private Direction direction = Direction.POSITIVE_Y;
    private ArrayList<Footprint> visitedPositions = new ArrayList<>();

    private File file = new File("resources/inputDay01.txt");

    public Point(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        visitedPositions.add(new Footprint(x,y));
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Point getPoint() {
        return this;
    }

    public void setAxisOfMovement(Direction d) {
        direction = d;
    }

    public Direction getCurrentAxisOfMovement() {
        return direction;
    }

    public void executeInstruction() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] instructions = line.split(",");
                for (int i = 0; i < instructions.length; i++) {
                    move(instructions[i].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageVisitedPositions() {
        Footprint footprint = new Footprint(getPositionX(), getPositionY());
        int counter = 0;

        if (visitedPositions.size() != 0) {

            for (int i = 0; i < visitedPositions.size(); i++) {
                if (visitedPositions.get(i).getPositionX() == footprint.getPositionX()) {
                    if (visitedPositions.get(i).getPositionY() == footprint.getPositionY()) {
                        System.out.println("Point " + footprint.getPositionX() + ", " + footprint.getPositionY()
                                + " has been visited twice and it's "+Math.abs(footprint.getPositionX()+ footprint.getPositionY())+" block(s) away");
                        counter++;
                    }
                }
            }
            if (counter == 0) {
                visitedPositions.add(footprint);
            }
        } else if (visitedPositions.size() == 0) {
            visitedPositions.add(footprint);
        }
    }

    public void makeStep(int x, int y) {
        setPosition(getPositionX() + x, getPositionY() + y);
//        System.out.println("Current position: " + getPositionX() + ", " + getPositionY());
    }

    public void move(String s) {
        char turn = s.charAt(0);
        int steps = Integer.parseInt(s.substring(1));

        switch (this.direction) {
            case POSITIVE_X:
                if (turn == 'L') {
                    System.out.println("Turning left");
                    setAxisOfMovement(Direction.POSITIVE_Y);
                    for (int i = 0; i < steps; i++) {
                        makeStep(0,1);
                        manageVisitedPositions();
                    }
                } else if (turn == 'R') {
                    System.out.println("Turning right");
                    setAxisOfMovement(Direction.NEGATIVE_Y);
                    for (int i = 0; i < steps; i++) {
                        makeStep(0,-1);
                        manageVisitedPositions();
                    }
                }
                break;

            case NEGATIVE_X:
                if (turn == 'L') {
                    System.out.println("Turning left");
                    setAxisOfMovement(Direction.NEGATIVE_Y);
                    for (int i = 0; i < steps; i++) {
                        makeStep(0,-1);
                        manageVisitedPositions();
                    }
                } else if (turn == 'R') {
                    System.out.println("Turning right");
                    setAxisOfMovement(Direction.POSITIVE_Y);
                    for (int i = 0; i < steps; i++) {
                        makeStep(0,1);
                        manageVisitedPositions();
                    }
                }
                break;

            case POSITIVE_Y:
                if (turn == 'L') {
                    System.out.println("Turning left");
                    setAxisOfMovement(Direction.NEGATIVE_X);
                    for (int i = 0; i < steps; i++) {
                        makeStep(-1,0);
                        manageVisitedPositions();
                    }
                } else if (turn == 'R') {
                    System.out.println("Turning right");
                    setAxisOfMovement(Direction.POSITIVE_X);
                    for (int i = 0; i < steps; i++) {
                        makeStep(1,0);
                        manageVisitedPositions();
                    }
                }
                break;

            case NEGATIVE_Y:
                if (turn == 'L') {
                    System.out.println("Turning left");
                    setAxisOfMovement(Direction.POSITIVE_X);
                    for (int i = 0; i < steps; i++) {
                        makeStep(1,0);
                        manageVisitedPositions();
                    }
                } else if (turn == 'R') {
                    System.out.println("Turning right");
                    setAxisOfMovement(Direction.NEGATIVE_X);
                    for (int i = 0; i < steps; i++) {
                        makeStep(-1,0);
                        manageVisitedPositions();
                    }
                }
                break;
        }
    }

    public int calculateDistance(Point a, Point b) {
        int xDistance = Math.abs(a.getPositionX() - b.getPositionX());
        int yDistance = Math.abs(a.getPositionY() - b.getPositionY());
        return xDistance + yDistance;
    }

    public void printVisited() {
        for (int i = 0; i < visitedPositions.size(); i++) {
            System.out.println(visitedPositions.get(i).getPositionX()+", "+visitedPositions.get(i).getPositionY());
        }
    }

}

