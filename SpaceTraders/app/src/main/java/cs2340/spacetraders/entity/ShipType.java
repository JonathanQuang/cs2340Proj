package cs2340.spacetraders.entity;

public enum ShipType {
    Gnat("GNAT");

    /** the full string representation of the shiptype **/
    private final String shipType;

    /**
     * Constructor for the enumeration
     *
     * @param shipType the ship type
     */
    ShipType(String shipType) {this.shipType = shipType;}

    /**
     *
     * @return the ship type
     */
    public String getShipType() {return shipType;}

    /**
     *
     * @return the display string representation of the ship type
     */
    public String toString() {return shipType;}

}


