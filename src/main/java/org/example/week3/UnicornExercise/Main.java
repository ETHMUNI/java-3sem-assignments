package org.example.week3.UnicornExercise;

public class Main {
    public static void main(String[] args) {
        Unicorn unicorn1 = new Unicorn("Sparky", 25, Unicorn.PowerStrength.FIRE);
        Unicorn unicorn2 = new Unicorn("Bubbles", 20, Unicorn.PowerStrength.WATER);

        UnicornDAO unicornDAO = new UnicornDAO();
        /*unicornDAO.save(unicorn1);
        unicornDAO.save(unicorn2);*/

        System.out.println(unicornDAO.findById(1));
    }
}
