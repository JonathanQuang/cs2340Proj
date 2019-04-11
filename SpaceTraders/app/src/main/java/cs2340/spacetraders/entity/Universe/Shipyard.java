
package cs2340.spacetraders.entity.Universe;

import java.util.List;
import java.io.Serializable;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;

/**
 * Implementation of the shipyard
 */
public class Shipyard implements  Serializable{

    private Ship playerShip;
    private Player player;

    /**
     * Constructor for the shipyard
     * @param playerShip player's ship
     * @param player current player
     */
    public Shipyard (Ship playerShip, Player player) {
        this.playerShip = playerShip;
        this.player = player;
    }

    /**
     * Buys a weapon
     * @param weapon weapon being bought
     */
    public void executePurchase(WeaponTypes weapon) {
        playerShip.addWeapon(weapon);
        playerShip.changeDamage(weapon.getPower());
        player.changeCredits(weapon.getBuyPrice() * -1);
    }

    /**
     * Sells a weapon
     * @param weapon weapon being sold
     */
    public void executeSell(WeaponTypes weapon) {
        playerShip.removeWeapon(weapon);
        playerShip.changeDamage(weapon.getPower() * -1.0);
        player.changeCredits(weapon.getSellPrice());
    }

    /**
     * Getter for the list of equipped weapon
     * @return list of equipped weapons
     */
    public List<WeaponTypes> getPlayerShipEquippedWeapons() {
        return playerShip.getEquippedWeapons();
    }


}

