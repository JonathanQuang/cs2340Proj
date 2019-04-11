package cs2340.spacetraders.viewmodels;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;
import cs2340.spacetraders.entity.Universe.Shipyard;

/**
 * ViewModel for the shipyard activity class following
 * the MVVM design
 */
public class ShipyardViewModel {
    private final Player player;
    private final Shipyard shipyard;
    private final Ship playerShip;
    private final List<WeaponTypes> weaponTypes;

    /**
     * constructor for this class
     * @param player the Player Object representing the current player
     */
    public ShipyardViewModel(Player player) {
        this.player = player;
        shipyard = new Shipyard(player.getShip(), player);
        playerShip = player.getShip();
        weaponTypes = playerShip.getEquippedWeapons();
    }

    /**
     *
     * @param weapon the weapon the user wishes to purchase
     * @return a string telling the user why they can't buy the item
     * or null if there is no problem with the weapon they want to buy
     */
    public String weaponBuyError(WeaponTypes weapon) {
        if (weapon.getBuyPrice() > player.getCredits()) {
            return "you don't have enough money to buy this ";
        } else if (weaponTypes.size() > playerShip.getShipTypeMaxWeaponSlots()) {
            return "max weapon slots exceeded";
        }
        return null;
    }

    /**
     * tells shipyard object to do the necessary math and update
     * other instance vars to buy a weapon
     * @param weapon the weapon the user bought
     */
    public void buyWeapon(WeaponTypes weapon) {
        shipyard.executePurchase(weapon);
    }

    /**
     * @return how many credits the player has
     */
    public int getPlayerCredits() {
        return player.getCredits();
    }

    /**
     *
     * @param weapon the weapon to check against
     * @return whether the weapon in question exists
     * in the List of weaponTypes
     */
    public boolean containsWeaponType(WeaponTypes weapon) {
        for (int i = 0; i < weaponTypes.size(); i++) {
            if (weaponTypes.get(i).ordinal() == weapon.ordinal()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a string that goes into the TextView in the ShipyardActivity
     * that displays the weapons that the player has
     */
    public String getEquippedWeaponsString() {
        String retStr = "";
        List<WeaponTypes> weaponList = shipyard.getPlayerShipEquippedWeapons();
        for (int i =0; i < weaponList.size(); i++) {
            retStr += weaponList.get(i).getEquipName() + "\n";
        }
        return retStr;
    }

    /**
     * Tells shipyard instance variable object to sell the weapon
     * @param weapon the weapon that the player wants sold
     */
    public void sellWeapon(WeaponTypes weapon){shipyard.executeSell(weapon);}

}
