
package cs2340.spacetraders.entity.Universe;

import java.util.List;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;
import cs2340.spacetraders.entity.ShipType;

public class Shipyard implements  Serializable{

    private Ship playerShip;
    private Player player;

    public Shipyard (Ship playerShip, Player player) {
        this.playerShip = playerShip;
        this.player = player;
    }

    public void executePurchase(WeaponTypes weapon) {
        playerShip.addWeapon(weapon);
        playerShip.changeDamage(weapon.getPower());
        player.changeCredits(weapon.getBuyPrice() * -1);
    }

    public void executeSell(WeaponTypes weapon) {
        playerShip.removeWeapon(weapon);
        playerShip.changeDamage(weapon.getPower() * -1.0);
        player.changeCredits(weapon.getSellPrice());
    }

    public List<WeaponTypes> getPlayerShipEquippedWeapons() {
        return playerShip.getEquippedWeapons();
    }


}

