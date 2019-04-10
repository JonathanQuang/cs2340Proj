package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Universe.Planet;

public class Trader extends Encounterable {

    private boolean hostile = false;

    /**
     *
     */
    public Trader(Planet planet) {
        super();
        if (hostile) {
            super.setAttackChance(0.9);
            super.setIgnoreChance(0);
        } else {
            super.setAttackChance(0);
            super.setIgnoreChance(0);
        }
    }

    /**
     *
     */
    public String trade() {
        return null;
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
    public String toString() {
        return "Trader";
    }

    /**
     *
     * @return
     */
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
