package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Player;

/**
 * Concrete implementation of a Pirate Character
 */
public class Pirate extends Encounterable {

    private final boolean hostile = true;
    private final Player player = getPlayer();
    private final Inventory inventory = player.getInventory();

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
        if (inventory.getCapacity() > 0) {
            inventory.removeRandomGood();
        } else {
            player.changeCredits(-2000);
        }
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
