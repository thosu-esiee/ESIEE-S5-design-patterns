package fr.uge.poo.duck;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggedDuck implements Duck {
    private static final Logger LOGGER = Logger.getLogger(LoggedDuck.class.getName());

    private final Duck duck;

    public LoggedDuck(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        LOGGER.log(Level.INFO, "A duck is quacking");
        duck.quack();
    }

    @Override
    public void quackManyTimes(int n) {
        for(int i=0; i < n; i++){
            quack();
        }
    }

    public static void main(String[] args) {
        Duck duck = new LoggedDuck(new RegularDuck());
        duck.quackManyTimes(2);
    }
}
