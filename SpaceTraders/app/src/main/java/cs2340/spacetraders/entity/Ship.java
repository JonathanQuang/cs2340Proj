package cs2340.spacetraders.entity;

public class Ship {

    private ShipType shipType;

    /**
     *
     * @param shipType the type of ship you want to instantiate
     */
    public Ship(ShipType shipType){
        this.shipType = shipType;
    }

    public Ship() {
        this.shipType = ShipType.Gnat;
    }

    /**
     *
     * @param shipType the type of ship you want to change this ship to
     */
    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    /**
     *
     * @return this ship's ship type
     */
    public ShipType getShipType() {
        return shipType;
    }


    /**
     *
     * @return  this ship's string reperesentaion
     */
    @Override
    public String toString() {
        return "(Type) " + shipType;
    }
}
