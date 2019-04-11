package cs2340.spacetraders.viewmodels;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;
import cs2340.spacetraders.entity.Universe.Shipyard;

public class ShipyardViewModel {
    private final Player player;
    private final Shipyard shipyard;
    private final Ship playerShip;
    private final List<WeaponTypes> weaponTypes;

    public ShipyardViewModel(Player player) {
        this.player = player;
        shipyard = new Shipyard(player.getShip(), player);
        playerShip = player.getShip();
        weaponTypes = playerShip.getEquippedWeapons();
    }

    public String weaponBuyError(WeaponTypes weapon) {
        if (weapon.getBuyPrice() > player.getCredits()) {
            return "you don't have enough money to buy this ";
        } else if (weaponTypes.size() > playerShip.getShipTypeMaxWeaponSlots()) {
            return "max weapon slots exceeded";
        }
        return null;
    }

    public void buyWeapon(WeaponTypes weapon) {
        shipyard.executePurchase(weapon);
    }

    public int getPlayerCredits() {
        return player.getCredits();
    }

    public boolean containsWeaponType(WeaponTypes weapon) {
        for (int i = 0; i < weaponTypes.size(); i++) {
            if (weaponTypes.get(i).ordinal() == weapon.ordinal()) {
                return true;
            }
        }
        return false;
    }

    public String getEquippedWeaponsString() {
        String retStr = "";
        List<WeaponTypes> weaponList = shipyard.getPlayerShipEquippedWeapons();
        for (int i =0; i < weaponList.size(); i++) {
            retStr += weaponList.get(i).getEquipName() + "\n";
        }
        return retStr;
    }

    public void sellWeapon(WeaponTypes weapon){shipyard.executeSell(weapon);}

}
