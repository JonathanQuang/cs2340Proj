package cs2340.spacetraders.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ShipType {
    Flea("FLEA" , 5),
    Gnat("GNAT" , 10),
    Firefly("FIREFLY" , 20),
    Mosquito("MOSQUITO" , 30),
    Bumblebee("BUMBLEBEE" , 40),
    Beetle("BEETLE" , 50),
    Hornet("HORNET" , 60);

    /** the full string representation of the shiptype **/
    private final String shipType;
    private final int cargoCapacity;

    private static final List<ShipType> SHIPLIST =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = SHIPLIST.size();
    private static final Random RANDOM = new Random();

    /**
     * Initializes string ship type of enum
     * @param shipType the ship type
     */
    ShipType(String shipType, int cargoCapacity) {
        this.shipType = shipType;
        this.cargoCapacity = cargoCapacity;
    }

    /**
     * Gets the ship type
     * @return the ship type
     */
    public String getShipType() {return shipType;}

    public int getCargoCapacity() {return cargoCapacity;}

    /**
     * Gets the string representation of the ship type
     * @return the string representation of the ship type
     */
    public String toString() {return shipType;}

    public static ShipType randomShipType()  {
        return SHIPLIST.get(RANDOM.nextInt(SIZE));
    }
}


