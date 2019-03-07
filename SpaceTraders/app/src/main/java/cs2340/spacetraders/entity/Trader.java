package cs2340.spacetraders.entity;

public class Trader extends Encounterable {

    private Ship ship;

    /**
     *
     */
    public Trader() {
        super();
        ship = new Ship(ShipType.randomShipType());
    }

    /**
     *
     */
    public void trade() {

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
        return "I am a trader";
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
