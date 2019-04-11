package cs2340.spacetraders.entity.Universe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A Enumeration of the possible large scale events that can happen to a planet
 */
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

    /**
     * @param increaseEvent the string representation of the planet event
     */
    PlanetaryEvent(String increaseEvent) {
        this.increaseEvent = increaseEvent;
    }

    /**
     * @return the string representation of the planet event
     */
    public String getIncreaseEvent() {
        return increaseEvent;
    }

    public String toString() {
        return increaseEvent;
    }

    /**
     * @return get random event
     */
    public PlanetaryEvent randomEvent()  {
        return PlanetaryEvent.values()[RANDOM.nextInt(PlanetaryEvent.values().length)];
    }

}
