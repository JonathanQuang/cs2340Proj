package cs2340.spacetraders.entity;

public enum ShipType {
    Gnat("GNAT" , 10);

    /** the full string representation of the shiptype **/
    private final String shipType;
    private final int cargoCapacity;

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
}


