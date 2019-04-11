package cs2340.spacetraders.entity;

import java.util.Random;

/**
 * Enum for possible ship types a ship may be
 */
public enum ShipType {

    Flea("FLEA" , 10, "Tiny", 20, 25, 0, 0, 0, 1, 7000, 10),
    Gnat("GNAT" , 15, "Small", 14, 100, 1, 0, 1, 1, 10000, 20),
    Firefly("FIREFLY" , 20, "Small", 17, 100, 1, 1, 1, 1, 16000, 30),
    Mosquito("MOSQUITO" , 15, "Small", 13, 100, 2, 1, 1, 1, 21000, 40),
    Bumblebee("BUMBLEBEE" , 25, "Medium", 15, 100, 1, 2, 2, 2, 51000, 50),
    Beetle("BEETLE" , 50, "Medium", 14, 50, 0, 1, 1, 3, 71000, 60),
    Hornet("HORNET" , 20, "Large", 16, 150, 3, 2, 1 ,2, 91000, 70),
    Grasshopper("GRASSHOPPER", 30, "Medium", 15, 100, 2, 2, 3, 3, 81000, 80),
    Termite("TERMITE", 60, "Large", 13, 150, 1, 2, 3, 3, 91000, 90);

    /** the full string representation of the shiptype **/
    private final String shipType;
    private final int cargoCapacity;
    private final String shipSize;
    private final int range;
    private final int hullStrength;
    private final int weaponSlots;
    private final int shieldSlots;
    private final int gadgetSlots;
    private final int crewQuarters;
    private final int price;
    private final int DEFAULT_DAMAGE;
    private final Random RANDOM = new Random();
    private final int rangeToMaxFuelMultiplier = 10;
    private final int hullStrengthToMaxHPMultiplier = 1;

    /**
     * Initializes string ship type of enum
     * @param shipType the ship type
     */
    ShipType(String shipType, int cargoCapacity, String shipSize, int range, int hullStrength,
             int weaponSlots, int shieldSlots, int gadgetSlots, int crewQuarters, int price, int defaultDamage) {
        this.shipType = shipType;
        this.cargoCapacity = cargoCapacity;
        this.shipSize = shipSize;
        this.range = range;
        this.hullStrength = hullStrength;
        this.weaponSlots = weaponSlots;
        this.shieldSlots = shieldSlots;
        this.gadgetSlots = gadgetSlots;
        this.crewQuarters = crewQuarters;
        this.price = price;
        this.DEFAULT_DAMAGE = defaultDamage;
    }

    /**
     * Getter for the ship's default damage
     * @return DEFAULT_DAMAGE
     */
    public double getDefaultDamage() {return DEFAULT_DAMAGE;}

    /**
     * Getter for the ship's size
     * @return shipSize
     */
    public String getShipSize() {return shipSize;}

    /**
     * Getter for the ship's range
     * @return range
     */
    public int getRange() {return range;}

    /**
     * Getter for the ship's hull strength
     * @return hullStrength
     */
    public int getHullStrength() {return hullStrength;}

    /**
     * Getter for the number of weapon slots
     * @return weaponSlots
     */
    public int getWeaponSlots() {return weaponSlots;}

    /**
     * Getter for the number of shield slots
     * @return shieldSlots
     */
    public int getShieldSlots() {return shieldSlots;}

    /**
     * Getter for the number of shieldSlots
     * @return shieldSlots
     */
    public int getGadgetSlots() {return shieldSlots;}

    /**
     * Getter for the number of crew quarters
     * @return crewQuarters
     */
    public int getCrewQuarters() {return crewQuarters;}

    /**
     * Getter for the ship type's price
     * @return price
     */
    public int getPrice() {return price;}

    /**
     * Getter for the maximum cargo capacity
     * @return cargoCapacity
     */
    public int getCargoCapacity() {return cargoCapacity;}

    /**
     * Getter for the shipType
     * @return shipType
     */
    public String getShipType() {return shipType;}

    /**
     * Getter for the max health
     * @return int of hull strength times multiplier
     */
    public int getMaxHealth() {return hullStrength * hullStrengthToMaxHPMultiplier;}

    /**
     * Getter for max fuel
     * @return int of maximum fuel that can be carried
     */
    public int getMaxFuel() {return range * rangeToMaxFuelMultiplier;}

    /**
     * Returns a random shipType
     * @return a random shipType
     */
    public ShipType randomShipType()  {
        return ShipType.values()[RANDOM.nextInt(ShipType.values().length)];
    }
}
