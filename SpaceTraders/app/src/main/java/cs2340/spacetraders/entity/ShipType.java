package cs2340.spacetraders.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ShipType {
<<<<<<< HEAD
    Flea("FLEA" , 5, 20),
    Gnat("GNAT" , 10, 25),
    Firefly("FIREFLY" , 20, 35),
    Mosquito("MOSQUITO" , 30, 45),
    Bumblebee("BUMBLEBEE" , 40, 55),
    Beetle("BEETLE" , 50, 65),
    Hornet("HORNET" , 60, 75);

    /** the full string representation of the shiptype **/
    private final String SHIP_TYPE;
    private final int CARGO_CAPACITY;
    private final int DEFAULT_DAMAGE;
=======
    Flea("FLEA" , 10, "Tiny", 20, 25, 0, 0, 0, 1, 7000),
    Gnat("GNAT" , 15, "Small", 14, 100, 1, 0, 1, 1, 10000),
    Firefly("FIREFLY" , 20, "Small", 17, 100, 1, 1, 1, 1, 16000),
    Mosquito("MOSQUITO" , 15, "Small", 13, 100, 2, 1, 1, 1, 21000),
    Bumblebee("BUMBLEBEE" , 25, "Medium", 15, 100, 1, 2, 2, 2, 51000),
    Beetle("BEETLE" , 50, "Medium", 14, 50, 0, 1, 1, 3, 71000),
    Hornet("HORNET" , 20, "Large", 16, 150, 3, 2, 1 ,2, 91000);

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
>>>>>>> sandbox

    private static final List<ShipType> SHIPLIST =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = SHIPLIST.size();
    private static final Random RANDOM = new Random();

    /**
     * Initializes string ship type of enum
     * @param shipType the ship type
     */
<<<<<<< HEAD
    ShipType(String shipType, int cargoCapacity, int defaultDamage) {
        this.SHIP_TYPE = shipType;
        this.CARGO_CAPACITY = cargoCapacity;
        this.DEFAULT_DAMAGE = defaultDamage;
=======
    ShipType(String shipType, int cargoCapacity, String shipSize, int range, int hullStrength,
             int weaponSlots, int shieldSlots, int gadgetSlots, int crewQuarters, int price) {
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
>>>>>>> sandbox
    }

    /**
     * Gets the ship type
     * @return the ship type
     */
    public String getShipType() {return SHIP_TYPE;}

    public int getCargoCapacity() {return CARGO_CAPACITY;}

    public double getDefaultDamage() {return DEFAULT_DAMAGE;}

    public String getShipSize() {return shipSize;}

    public int getRange() {return range;}

    public int getHullStrength() {return hullStrength;}

    public int getWeaponSlots() {return weaponSlots;}

    public int getShieldSlots() {return shieldSlots;}

    public int getGadgetSlots() {return shieldSlots;}

    public int getCrewQuarters() {return crewQuarters;}

    public int getPrice() {return price;}



    /**
     * Gets the string representation of the ship type
     * @return the string representation of the ship type
     */
    public String toString() {return SHIP_TYPE;}

    public static ShipType randomShipType()  {
        return SHIPLIST.get(RANDOM.nextInt(SIZE));
    }
}


