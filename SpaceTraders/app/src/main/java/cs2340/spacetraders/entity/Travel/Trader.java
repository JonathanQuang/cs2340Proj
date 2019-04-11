package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;

/**
 * Concrete implementation of a trader character
 */
public class Trader extends Encounterable {

    private boolean hostile;
    private Player player = getPlayer();
    private Inventory playerInventory = player.getInventory();

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
    public String toString() {
        return "Trader";
    }

    @Override
    public String createDialogue() {
        if (player.getCriminalStatus()) {
            return "Trader: Hostile";
        } else {
            return "Trader: Non-hostile";
        }
    }

    @Override
    public void surrenderResult() {
        if (playerInventory.getCapacity() > 0) {
            playerInventory.removeRandomGood();
        } else {
            player.changeCredits(-2000);
        }
    }

    @Override
    public boolean setHostile() {
        if (player.getCriminalStatus()) {
            hostile = true;
            setIgnoreChance(0);
            setAttackChance(0.9);
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
