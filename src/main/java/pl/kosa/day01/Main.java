package day01;

public class Main {
    public static void main(String[] args) {
        Point start = new Point(0, 0);
        Point finish = new Point(0, 0);

        finish.executeInstruction();

        System.out.println(finish.calculateDistance(start, finish));
        System.out.println("Position x: "+finish.getPositionX()+" Position y: "+finish.getPositionY());
        System.out.println("HQ is "+(Math.abs(finish.getPositionX()+finish.getPositionY()))+" blocks away");

    }
}
