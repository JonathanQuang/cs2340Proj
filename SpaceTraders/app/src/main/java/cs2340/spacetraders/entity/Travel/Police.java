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

    /**
     *
     */
    public Police(Planet planet) {
        super();
        bribeChance = PoliticalSystem.determineProbability(planet.getPoliceBriberyAcceptance());
        searchChance = PoliticalSystem.determineProbability(planet.getPoliceSmugglingAcceptance());
        if (Player.getCriminalStatus()) {
            super.setIgnoreChance(0);
            super.setAttackChance(1);
        } else {
            super.setIgnoreChance(searchChance);
            super.setAttackChance(0);
        }
    }

    /**
     *
     * @return
     */
    public void bribe() {
        if (super.getRandom() > bribeChance) {
            Player.changeCredits(-500);
        } else {
            confiscate();
        }
    }

    /**
     *
     * @return
     */
    public void checkCargo() {
        if (super.getRandom() > searchChance) {
            if (Player.getInventory().containsIllegalGoods()) {
                confiscate();
            }
        }
    }

    /**
     *
     */
    public void confiscate() {
        Inventory inventory = Player.getInventory();
        inventory.removeGood(Good.Firearms, inventory.getGoodAmount(Good.Firearms));
        inventory.removeGood(Good.Narcotics, inventory.getGoodAmount(Good.Narcotics));
        Player.changeCredits(-1000);
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
        return "This is the police";
    }

}
