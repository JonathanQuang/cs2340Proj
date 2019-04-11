package cs2340.spacetraders.entity.Universe;

import java.util.Random;

/**
 * Enum for a random event that occurs on a planet
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

    PlanetaryEvent(String increaseEvent) {
        this.increaseEvent = increaseEvent;
    }

    /**
     * Getter for the event
     * @return increaseEvent
     */
    public String getIncreaseEvent() {
        return increaseEvent;
    }

    @Override
    public String toString() {
        return increaseEvent;
    }

    /**
     * Returns a random event from the enum
     * @return a random PlanetaryEvent enum type
     */
    public PlanetaryEvent randomEvent()  {
        return PlanetaryEvent.values()[RANDOM.nextInt(PlanetaryEvent.values().length)];
    }

}
