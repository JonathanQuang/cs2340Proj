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
    private static final List<PlanetaryEvent> EVENT_LIST =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = EVENT_LIST.size();
    private static final Random RANDOM = new Random();

    PlanetaryEvent(String increaseEvent) {
        this.increaseEvent = increaseEvent;
    }

    public String getIncreaseEvent() {
        return increaseEvent;
    }

    public int getSIZE() {
        return SIZE;
    }

    public String toString() {
        return increaseEvent;
    }

    public static PlanetaryEvent randomEvent()  {
        return EVENT_LIST.get(RANDOM.nextInt(SIZE));
    }

}
