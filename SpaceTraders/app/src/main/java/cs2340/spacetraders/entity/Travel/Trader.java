package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Planet;

/**
 * Concrete implementation of a trader character
 */
public class Trader extends Encounterable {

    private boolean hostile;

    /**
     * Default constructor
     * @param planet Planet being traveled to
     */
    public Trader(Planet planet) {
        if (hostile) {
            setAttackChance(0.9);
            setIgnoreChance(0);
        } else {
            setAttackChance(0);
            setIgnoreChance(0);
        }
    }

    /**
     * Trades with the player
     * @return String trade results
     */
    public String trade() {
        return null;
    }

    @Override
    public Ship getShip() {
        return super.getShip();
    }

    @Override
    public String toString() {
        return "Trader";
    }

    @Override
    public String createDialogue() {
        if (getPlayer().getCriminalStatus()) {
            return "Trader: Hostile";
        } else {
            return "Trader: Non-hostile";
        }
    }

    @Override
    public void surrenderResult() {
        Inventory inventory = getPlayer().getInventory();
        if (inventory.getCapacity() > 0) {
            inventory.removeRandomGood();
        } else {
            getPlayer().changeCredits(-2000);
        }
    }

    @Override
    public boolean setHostile() {
        if (getPlayer().getCriminalStatus()) {
            hostile = true;
            super.setIgnoreChance(0);
            super.setAttackChance(0.9);
            return hostile;
        } else {
            return hostile;
        }
    }

    @Override
    public String uniqueAction() {
        return trade();
    }

}
