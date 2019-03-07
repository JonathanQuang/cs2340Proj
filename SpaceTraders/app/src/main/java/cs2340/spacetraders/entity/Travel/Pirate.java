package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Travel.Encounterable;

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
