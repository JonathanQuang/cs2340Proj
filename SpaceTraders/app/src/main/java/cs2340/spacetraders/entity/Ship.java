package cs2340.spacetraders.entity;

import java.util.ArrayList;
import java.util.List;

import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;

public class Ship {

    private ShipType shipType;
    private double health;
    private int fuel;
    private List<WeaponTypes> equippedWeapons;

    /**
     * Initializes a ship
     * @param shipType the type of ship you want to instantiate
     */
    public Ship(ShipType shipType){
        this.shipType = shipType;
        this.health = (double) shipType.getMaxHealth();
        this.fuel = shipType.getMaxFuel();
        this.equippedWeapons = new ArrayList<WeaponTypes>();
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

    public double getHealth() { return health; }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getCargoCapacity() {
        return shipType.getCargoCapacity();
    }

    public int getShipTypePrice() {return shipType.getPrice();}

    public List<WeaponTypes> getEquippedWeapons() {return equippedWeapons;}

    public void addWeapon(WeaponTypes weapon) {
        equippedWeapons.add(weapon);
    }

    /**
     * Gets string representation of the information about the ship
     * @return  this ship's string representation
     */
    public String toString() {
        return "(Type) " + shipType;
    }
}
