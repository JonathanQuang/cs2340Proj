package cs2340.spacetraders.entity.Travel;

import java.util.Random;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;

/**
 * Concrete implementation of a trader character
 */
public class Trader extends Encounterable {

    private boolean hostile;
    private final Player player = getPlayer();
    private final Inventory playerInventory = player.getInventory();
    private final Random  RANDOM = new Random();
    private final PlanetInventory traderInventory = new PlanetInventory();
    private final Good randomGood;

    /**
     * Default constructor
     */
    public Trader() {
        randomGood = traderInventory.randomGood();
        traderInventory.addToPlanetInventory(randomGood, randomGood.getTraderPrice(), 0, RANDOM.nextInt(19)+1, true, true);
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
     */
    public void buyGood(int amount) {
        traderInventory.updateCountOfSingleGood(randomGood, amount);
        playerInventory.addGood(randomGood,amount, randomGood.getTraderPrice());
        player.changeCredits(-1*amount*randomGood.getTraderPrice());
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
        } else if (player.getCredits() >= 2000) {
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
        return " would like to trade";
    }

    public PlanetInventory getTraderInventory() {
        return traderInventory;
    }

    public Good getRandomGood() {
        return randomGood;
    }
}
