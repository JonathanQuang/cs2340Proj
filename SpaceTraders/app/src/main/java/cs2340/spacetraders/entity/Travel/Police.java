package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.PoliticalSystem;

/**
 * Concrete implementation of a Police character
 */
public class Police extends Encounterable {

    private final double bribeChance;
    private final double searchChance;
    private boolean hostile;
    private final Player player = getPlayer();
    private final Inventory playerInventory = player.getInventory();
    private final PoliticalSystem politicalSystem;
    private final String briberyAcceptance;
    private final String smugglingAcceptance;

    /**
     * Default constructor
     * @param planet current planet being traveled to
     */
    public Police(Planet planet) {
        politicalSystem = planet.getPoliticalSystem();
        briberyAcceptance = politicalSystem.getPoliceBriberyAcceptance();
        smugglingAcceptance = politicalSystem.getPoliceSmugglingAcceptance();
        bribeChance = politicalSystem.determineProbability(briberyAcceptance);
        searchChance = politicalSystem.determineProbability(smugglingAcceptance);
        if (hostile) {
            setIgnoreChance(0);
            setAttackChance(0.9);
        } else {
            setIgnoreChance(searchChance);
            setAttackChance(0);
        }
    }

    /**
     * Action that takes place if player tries to bribe the police
     * @return String success or failure
     */
    public String bribe() {
        if (getRandom() > bribeChance) {
            player.changeCredits(-500);
            return " successfully ";
        } else {
            checkCargo();
            return " failed to ";
        }
    }

    /**
     * Checks the player inventory for illegal goods
     */
    public void checkCargo() {
        if (getRandom() > searchChance) {
            if (playerInventory.containsIllegalGoods()) {
                confiscate();
            }
        }
    }

    /**
     * Takes the illegal goods and fines the player
     */
    public void confiscate() {
        int goodAmount = playerInventory.getGoodAmount(Good.Firearms);
        playerInventory.removeGood(Good.Firearms, goodAmount);
        goodAmount = playerInventory.getGoodAmount(Good.Narcotics);
        playerInventory.removeGood(Good.Narcotics, goodAmount);
        player.changeCredits(-1000);
    }

    @Override
    public String createDialogue() {
        if (player.getCriminalStatus()) {
            return "Police: Hostile";
        } else {
            return "Police: Non-hostile";
        }
    }

    @Override
    public void surrenderResult() {
        checkCargo();
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
        return bribe();
    }

    @Override
    public String toString() {
        return "Police";
    }

}
