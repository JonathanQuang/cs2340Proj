package cs2340.spacetraders.entity.Universe;

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

    PlanetaryEvent(String increaseEvent) {
        this.increaseEvent = increaseEvent;
    }

    public String getIncreaseEvent() {
        return increaseEvent;
    }
}
