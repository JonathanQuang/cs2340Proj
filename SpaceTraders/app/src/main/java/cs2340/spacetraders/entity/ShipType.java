package cs2340.spacetraders.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ShipType {
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

    private static final List<ShipType> SHIPLIST =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = SHIPLIST.size();
    private static final Random RANDOM = new Random();

    /**
     * Initializes string ship type of enum
     * @param shipType the ship type
     */
    ShipType(String shipType, int cargoCapacity, int defaultDamage) {
        this.SHIP_TYPE = shipType;
        this.CARGO_CAPACITY = cargoCapacity;
        this.DEFAULT_DAMAGE = defaultDamage;
    }

    /**
     * Gets the ship type
     * @return the ship type
     */
    public String getShipType() {return SHIP_TYPE;}

    public int getCargoCapacity() {return CARGO_CAPACITY;}

    public double getDefaultDamage() {return DEFAULT_DAMAGE;}

    /**
     * Gets the string representation of the ship type
     * @return the string representation of the ship type
     */
    public String toString() {return SHIP_TYPE;}

    public static ShipType randomShipType()  {
        return SHIPLIST.get(RANDOM.nextInt(SIZE));
    }
}


