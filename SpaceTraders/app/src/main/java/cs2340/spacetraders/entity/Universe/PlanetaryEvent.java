package cs2340.spacetraders.entity.Universe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PlanetaryEvent {
    Nothing("Nothing"),
    Drought("Drought"),
    Cold("Cold"),
    Cropfail("Cropfail"),
    War("War"),
    Boredom("Boredom"),
    Plague("Plague"),
    LackOfWorkers("LackOfWorkers");

    private final String increaseEvent;
    private final Random RANDOM = new Random();

    PlanetaryEvent(String increaseEvent) {
        this.increaseEvent = increaseEvent;
    }

    public String getIncreaseEvent() {
        return increaseEvent;
    }

    public String toString() {
        return increaseEvent;
    }

    public PlanetaryEvent randomEvent()  {
        return PlanetaryEvent.values()[RANDOM.nextInt(PlanetaryEvent.values().length)];
    }

}
