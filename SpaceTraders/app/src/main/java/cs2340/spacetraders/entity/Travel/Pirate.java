package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Universe.Planet;

public class Pirate extends Encounterable {

    private boolean hostile = true;

    /**
     *
     */
    public Pirate() {
        super();
        super.setAttackChance(0.9);
        super.setIgnoreChance(0);
    }

    /**
     *
     */
    public void steal() {
        Inventory inventory = getPlayer().getInventory();
        if (inventory.getCapacity() > 0) {
            inventory.removeRandomGood();
        } else {
            getPlayer().changeCredits(-2000);
        }
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
        return "Pirate: Hostile";
    }

    @Override
    public void surrenderResult() {
        steal();
    }

    @Override
    public boolean setHostile() {
        return hostile;
    }

    @Override
    public String uniqueAction() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Pirate";
    }


}
