package cs2340.spacetraders.entity;

public class Ship {

    private ShipType shipType;

    /**
     * Initializes a ship
     * @param shipType the type of ship you want to instantiate
     */
    public Ship(ShipType shipType){
        this.shipType = shipType;
    }

    public Ship() {
        this.shipType = ShipType.Gnat;
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
     * Gets string representation of the information about the ship
     * @return  this ship's string representation
     */
    public String toString() {
        return "(Type) " + shipType;
    }
}
