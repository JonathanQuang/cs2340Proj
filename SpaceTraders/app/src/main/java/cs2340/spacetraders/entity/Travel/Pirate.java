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
        setAttackChance(0.9);
        setPursueChance(0.5);
        setFleeChance(0.1);
    }

    /**
     * Steals a random item or credits from the player
     */
    public void steal() {
        if (inventory.getCapacity() > 0) {
            inventory.removeRandomGood();
        } else if (player.getCredits() >= 2000) {
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
        return true;
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
