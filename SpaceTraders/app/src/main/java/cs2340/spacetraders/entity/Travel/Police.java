package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.PoliticalSystem;

public class Police extends Encounterable {

    private double bribeChance, searchChance;
    private boolean hostile = false;

    /**
     *
     */
    public Police(Planet planet) {
        super();
        bribeChance = planet.getPoliticalSystem().determineProbability(planet.getPoliceBriberyAcceptance());
        searchChance = planet.getPoliticalSystem().determineProbability(planet.getPoliceSmugglingAcceptance());
        if (hostile) {
            super.setIgnoreChance(0);
            super.setAttackChance(0.9);
        } else {
            super.setIgnoreChance(searchChance);
            super.setAttackChance(0);
        }
    }

    /**
     *
     * @return
     */
    public String bribe() {
        if (super.getRandom() > bribeChance) {
            getPlayer().changeCredits(-500);
            return " successfully ";
        } else {
            checkCargo();
            return " failed to ";
        }
    }

    /**
     *
     * @return
     */
    public void checkCargo() {
        if (super.getRandom() > searchChance) {
            if (getPlayer().getInventory().containsIllegalGoods()) {
                confiscate();
            }
        }
    }

    /**
     *
     */
    public void confiscate() {
        Inventory inventory = getPlayer().getInventory();
        inventory.removeGood(Good.Firearms, inventory.getGoodAmount(Good.Firearms));
        inventory.removeGood(Good.Narcotics, inventory.getGoodAmount(Good.Narcotics));
        getPlayer().changeCredits(-1000);
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
            super.setIgnoreChance(0);
            super.setAttackChance(0.9);
            return hostile;
        } else {
            return hostile;
        }
    }

    @Override
    public String uniqueAction() {
        return bribe();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Police";
    }

}
