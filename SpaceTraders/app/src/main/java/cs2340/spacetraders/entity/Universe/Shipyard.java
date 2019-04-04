package cs2340.spacetraders.entity.Universe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;

public class Shipyard {

    private Ship playerShip;
    private Player player;

    public Shipyard (Ship playerShip, Player player) {
        this.playerShip = playerShip;
        this.player = player;
    }

    public void executePurchase(WeaponTypes weapon) {
        playerShip.addWeapon(weapon);
        player.changeCredits(weapon.getBuyprice() * -1);
    }

    public List<WeaponTypes> getPlayerShipEquippedWeapons() {
        return playerShip.getEquippedWeapons();
    }


}
