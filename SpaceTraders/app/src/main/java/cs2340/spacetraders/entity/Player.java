package cs2340.spacetraders.entity;

public class Player {

    private final int  initialCredits = 1000;

    private String name;
    private int engineerStat;
    private int fighterStat;
    private int traderStat;
    private int pilotStat;
    private static int credits;
    private static Ship ship;
    private static Inventory inventory;
    private static boolean criminalStatus;

    /**
     * Makes a player with name, stats, and ship
     * @param name the name of the player
     * @param engineerStat the engineer stats of the player
     * @param fighterStat the fighter stats of the player
     * @param traderStat the trader stats of the player
     * @param pilotStat the pilot stats of the player
     */
    public Player(String name, int engineerStat, int fighterStat, int traderStat, int pilotStat) {
        this.name = name;
        this.engineerStat = engineerStat;
        this.fighterStat = fighterStat;
        this.traderStat = traderStat;
        this.pilotStat = pilotStat;
        this.credits = initialCredits;
        this.ship = new Ship();
        this.inventory = new Inventory(ship.getCargoCapacity());
        this.criminalStatus = false;
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public int getCredits() {return credits;}

    public static void changeCredits(int changeNum) {credits += changeNum;}

    /**
     * Gets a string representation of the player's attributes
     * @return a string representation of the player's attributes
     */
    public String toString() {
        String retStr = "";
        retStr += "Player name: " + name;
        retStr += ", Engineer stat: " + engineerStat;
        retStr += ", Fighter stat: " + fighterStat;
        retStr += ", Trader stat: " + traderStat;
        retStr += ", Pilot stat: " + pilotStat;
        retStr += ", Number of Credits: " + credits;
        retStr += ", Ship Info: " + ship;
        return retStr;
    }

<<<<<<< HEAD
    public static void setCriminalStatus(boolean status) {
        criminalStatus = status;
    }

    public static boolean getCriminalStatus() {
        return criminalStatus;
    }

    public void death() {

    }

    public static Ship getShip() {
        return ship;
    }

    public static void takeDamage(double damage) {
        ship.takeDamage(damage);
    }

    public static double getHealth() {
        return ship.getHealth();
    }
=======
    public Ship getShip() {
        return ship;
    }
>>>>>>> sandbox
}
