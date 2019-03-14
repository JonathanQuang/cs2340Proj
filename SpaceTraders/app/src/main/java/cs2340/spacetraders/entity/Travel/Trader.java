package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Universe.Planet;

public class Trader extends Encounterable {

    /**
     *
     */
    public Trader(Planet planet) {
        super();
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
        return super.getShip();
    }

    /**
     *
     * @return
     */
    @Override
    public String createDialogue() {
        return "I am a trader";
    }

}
