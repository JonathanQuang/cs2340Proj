package cs2340.spacetraders.entity;

public class Pirate extends Encounterable {

    private Ship ship;

    /**
     *
     */
    public Pirate() {
        super();
        ship = new Ship(ShipType.randomShipType());
    }

    /**
     *
     */
    public void steal() {

    }

    /**
     *
     * @return
     */
    @Override
    public Ship getShip() {
        return ship;
    }

    /**
     *
     * @return
     */
    @Override
    public String createDialogue() {
        return "Arrrr";
    }

    /**
     *
     * @return
     */
    @Override
    public int attack() {
        return 0;
    }


}
