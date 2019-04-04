package cs2340.spacetraders.viewmodels;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;
import cs2340.spacetraders.entity.Universe.Shipyard;
import cs2340.spacetraders.views.ShipyardActivity;

public class ShipyardViewModel {
    private Player player;
    private Shipyard shipyard;

    public ShipyardViewModel(Player player) {
        this.player = player;
        shipyard = new Shipyard(player.getShip(), player);
    }

    public String weaponBuyError(WeaponTypes weapon) {
        if (weapon.getBuyprice() > player.getCredits()) {
            return "you don't have enough money to buy this ";
        }
        return null;
    }

    public void buyWeapon(WeaponTypes weapon) {
        shipyard.executePurchase(weapon);
    }

    public int getPlayerCredits() {
        return player.getCredits();
    }

    public String getEquippedWeaponsString() {
        String retStr = "";
        List<WeaponTypes> weaponList = shipyard.getPlayerShipEquippedWeapons();
        for (int i =0; i < weaponList.size(); i++) {
            retStr += weaponList.get(i).getEquipName() + "\n";
        }
        return retStr;
    }
}
