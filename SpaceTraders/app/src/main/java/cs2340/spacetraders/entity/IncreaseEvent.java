package cs2340.spacetraders.entity;

public enum IncreaseEvent {
    Drought("Drought"),
    Cold("Cold"),
    Cropfail("Cropfail"),
    War("War"),
    Boredom("Boredom"),
    Plague("Plague"),
    LackOfWorkers("LackOfWorkers");

    private final String increaseEvent;


    IncreaseEvent(String increaseEvent) {
        this.increaseEvent = increaseEvent;
    }


    public String getIncreaseEvent() {
        return increaseEvent;
    }
}