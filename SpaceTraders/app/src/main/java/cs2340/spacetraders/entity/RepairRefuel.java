package cs2340.spacetraders.entity;

public class RepairRefuel {
    private Ship playerShip;
    private ShipType playerShipType;
    private int maxFuel;
    private int maxHealth;

    public RepairRefuel(Ship playerShip) {
        this.playerShip = playerShip;
        playerShipType = playerShip.getShipType();
    }

}
