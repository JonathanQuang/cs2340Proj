package cs2340.spacetraders.entity;

public class Ship {

    private ShipType shipType;
    private double health;
    private double fuel;

    /**
     * Initializes a ship
     * @param shipType the type of ship you want to instantiate
     */
    public Ship(ShipType shipType){
        this.shipType = shipType;
        this.health = 100;
        this.fuel = 100;
    }

    public Ship() {
        this(ShipType.Gnat);
    }

    /**
     * Sets ships type
     * @param shipType the type of ship you want to change this ship to
     */
    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    /**
     * Gets ship type
     * @return this ship's ship type
     */
    public ShipType getShipType() {
        return shipType;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    /**
     * Gets string representation of the information about the ship
     * @return  this ship's string representation
     */
    public String toString() {
        return "(Type) " + shipType;
    }
}
