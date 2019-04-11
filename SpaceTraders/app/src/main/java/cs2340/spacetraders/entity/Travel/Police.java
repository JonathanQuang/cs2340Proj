package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Planet;

/**
 * Concrete implementation of a Police character
 */
public class Police extends Encounterable {

    private double bribeChance;
    private double searchChance;
    private boolean hostile;

    /**
     * Default constructor
     * @param planet current planet being traveled to
     */
    public Police(Planet planet) {
        bribeChance = planet.getPoliticalSystem().determineProbability(
                planet.getPoliceBriberyAcceptance());
        searchChance = planet.getPoliticalSystem().determineProbability(
                planet.getPoliceSmugglingAcceptance());
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
            getPlayer().changeCredits(-500);
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
            if (getPlayer().getInventory().containsIllegalGoods()) {
                confiscate();
            }
        }
    }

    /**
     * Takes the illegal goods and fines the player
     */
    public void confiscate() {
        Inventory inventory = getPlayer().getInventory();
        inventory.removeGood(Good.Firearms, inventory.getGoodAmount(Good.Firearms));
        inventory.removeGood(Good.Narcotics, inventory.getGoodAmount(Good.Narcotics));
        getPlayer().changeCredits(-1000);
    }

    @Override
    public Ship getShip() {
        return super.getShip();
    }

    @Override
    public String createDialogue() {
        if (getPlayer().getCriminalStatus()) {
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
        if (getPlayer().getCriminalStatus()) {
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
