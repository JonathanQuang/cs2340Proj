package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Ship;

/**
 * Concrete implementation of a Pirate Character
 */
public class Pirate extends Encounterable {

    private boolean hostile = true;

    /**
     * Default constructor
     */
    public Pirate() {
        super();
        super.setAttackChance(0.9);
        super.setIgnoreChance(0);
    }

    /**
     * Steals a random item or credits from the player
     */
    public void steal() {
        Inventory inventory = getPlayer().getInventory();
        if (inventory.getCapacity() > 0) {
            inventory.removeRandomGood();
        } else {
            getPlayer().changeCredits(-2000);
        }
    }

    @Override
    public Ship getShip() {
        return super.getShip();
    }

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

    @Override
    public String toString() {
        return "Pirate";
    }


}
