package cs2340.spacetraders.entity;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;

/**
 * Implementation of a ship
 */
public class Ship implements  Serializable{

    private ShipType shipType;
    private double health;
    private int fuel;
    private double damage;
    private List<WeaponTypes> equippedWeapons;

    /**
     * Initializes a ship
     * @param shipType the type of ship you want to instantiate
     */
    public Ship(ShipType shipType){
        this.shipType = shipType;
        this.health = (double) shipType.getMaxHealth();
        this.damage = shipType.getDefaultDamage();
        this.fuel = shipType.getMaxFuel();
        this.equippedWeapons = new ArrayList<WeaponTypes>();
    }

    /**
     * Default no-args constructor
     */
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

    /**
     * Getter for health
     * @return health
     */
    public double getHealth() { return health; }

    /**
     * Setter for health
     * @param health ship's health
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Ship takes damage, subtract health
     * @param damage damage taken
     */
    public void takeDamage(double damage) {this.health -= damage;}

    /**
     * Getter for ship's attack power
     * @return damage
     */
    public double getDamage() {return this.damage;}

    /**
     * Changes the attack power of the ship
     * @param changeVal amount to be changed
     */
    public void changeDamage(double changeVal) {this.damage += changeVal;}

    /**
     * Getter for fuel
     * @return fuel
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * Setter for fuel
     * @param fuel new amount of fuel
     */
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * Getter for maximum capacity
     * @return ship's cargo capacity
     */
    public int getCargoCapacity() {
        return shipType.getCargoCapacity();
    }

    /**
     * Getter for ship's price
     * @return price of the ship
     */
    public int getShipTypePrice() {return shipType.getPrice();}

    /**
     * Getter for the maximum number of weapons a ship can have
     * @return number of weapon slots
     */
    public int getShipTypeMaxWeaponSlots() {return  shipType.getWeaponSlots();}

    /**
     * Getter for a list of equipped weapons
     * @return list of equipped weapons
     */
    public List<WeaponTypes> getEquippedWeapons() {return equippedWeapons;}

    /**
     * Equip a new weapon to the ship
     * @param weapon weapon to be equip
     */
    public void addWeapon(WeaponTypes weapon) {
        equippedWeapons.add(weapon);
    }

    /**
     * Unequip a weapon
     * @param weapon weapon to be removed
     */
    public void removeWeapon(WeaponTypes weapon) {equippedWeapons.remove(weapon);}

    /**
     * Gets string representation of the information about the ship
     * @return  this ship's string representation
     */
    @Override
    public String toString() {
        return "(Type) " + shipType + ", Health: " + health + " , Fuel: " + fuel;
    }
}

