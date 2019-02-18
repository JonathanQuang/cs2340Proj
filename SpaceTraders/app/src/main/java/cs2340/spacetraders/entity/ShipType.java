package cs2340.spacetraders.entity;

public enum ShipType {
    Gnat("GNAT");

    /** the full string representation of the shiptype **/
    private final String shipType;

    /**
     * Initializes string ship type of enum
     * @param shipType the ship type
     */
    ShipType(String shipType) {this.shipType = shipType;}

    /**
     * Gets the ship type
     * @return the ship type
     */
    public String getShipType() {return shipType;}

    /**
     * Gets the string representation of the ship type
     * @return the string representation of the ship type
     */
    public String toString() {return shipType;}
}


