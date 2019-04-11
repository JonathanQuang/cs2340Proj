package cs2340.spacetraders.entity;

import java.io.Serializable;

/**
 * The player playing the game
 */
public class Player implements Serializable {

    private final int  initialCredits = 1000;

    private String name;
    private int engineerStat;
    private int fighterStat;
    private int traderStat;
    private int pilotStat;
    private int credits;
    private Ship ship;
    private Inventory inventory;
    private boolean criminalStatus;

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

    /**
     * @return the player's inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @return the number of credit held by the player
     */
    public int getCredits() {return credits;}

    /**
     * @return the ship type
     */
    public int getShipTypePrice() {return ship.getShipTypePrice();}

    /**
     * @param changeNum the number of credits to be changed
     */
    public void changeCredits(int changeNum) {credits += changeNum;}

    /**
     * @param shipType the ship type being changed to
     */
    public void setShipType(ShipType shipType) {
        ship.setShipType(shipType);
    }

    /**
     * @return the player's ship type
     */
    public ShipType getShipType() {return ship.getShipType();}

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

    public void setCriminalStatus(boolean status) {
        criminalStatus = status;
    }

    public boolean getCriminalStatus() {
        return criminalStatus;
    }

    public void death() {

    }

    /**
     * @return the player's ship
     */
    public Ship getShip() {
        return ship;
    }

    public void takeDamage(double damage) {
        ship.takeDamage(damage);
    }

    public double getHealth() {
        return ship.getHealth();
    }
}
