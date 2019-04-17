package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;

/**
 * Concrete implementation of a trader character
 */
public class Trader extends Encounterable {

    private boolean hostile;
    private final Player player = getPlayer();
    private final Inventory playerInventory = player.getInventory();

    /**
     * Default constructor
     * @param planet Planet being traveled to
     */
    public Trader(Planet planet) {
        if (hostile) {
            setAttackChance(0.9);
            setPursueChance(0.5);
            setFleeChance(0.1);
            setUniqueChance(0);
            setIgnoreChance(0);
        } else {
            setUniqueChance(0.9);
            setAttackChance(0);
            setIgnoreChance(0.1);
        }
    }

    /**
     * Trades with the player
     * @return String trade results
     */
    public String trade() {
        return " would like to trade";
    }

    @Override
    public String toString() {
        return "Trader";
    }

    @Override
    public String createDialogue() {
        setHostile();
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
            setAttackChance(0.9);
            setPursueChance(0.5);
            setFleeChance(0.1);
            setUniqueChance(0);
            setIgnoreChance(0);
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
