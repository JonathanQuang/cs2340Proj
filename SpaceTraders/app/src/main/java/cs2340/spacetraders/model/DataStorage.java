package cs2340.spacetraders.model;

public class DataStorage {
    private static int totalEncounters;

    public static int getTotalEncounters() {
        return totalEncounters;
    }

    public static void setTotalEncounters(int totalEncounters) {
        DataStorage.totalEncounters = totalEncounters;
    }
}
