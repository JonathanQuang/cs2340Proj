package cs2340.spacetraders.entity.Travel;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Universe.Planet;

public class Pirate extends Encounterable {

    /**
     *
     */
    public Pirate() {
        super();
        super.setAttackChance(1);
        super.setIgnoreChance(0);
    }

    /**
     *
     */
    public void steal() {
        Inventory inventory = Player.getInventory();
        if (inventory.getCapacity() > 0) {
            inventory.removeRandomGood();
        } else {
            Player.changeCredits(-2000);
        }
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
        return "Arrrr";
    }


}
