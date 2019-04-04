package cs2340.spacetraders.viewmodels;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;

public class RepairRefuelViewModel {
    private Ship playerShip;
    private ShipType playerShipType;
    private static int fuelUnitPrice = 10;
    private static int healthUnitPrice = 20;
    private Player player;


    public RepairRefuelViewModel(Player player) {
        this.player = player;
        playerShip = player.getShip();
        playerShipType = playerShip.getShipType();
    }

    public double getMaxHealth() {return (double) playerShipType.getMaxHealth();}

    public int getMaxFuel() {return playerShipType.getMaxFuel();}

    public double getPlayerHealth() {return playerShip.getHealth();}

    public int getPlayerFuel() {return playerShip.getFuel();}

    public String fuelError(double fuelToBuy) {
        if (fuelToBuy <= 0) {
           return "please enter a positive quantity";
        } else if (fuelToBuy > playerShipType.getMaxFuel() - playerShip.getFuel()) {
            return "buying more fuel than player ship can hold";
        } else if (fuelToBuy * fuelUnitPrice > player.getCredits()) {
            return "too poor, fuel costs " + fuelUnitPrice + " here";
        }
        return null;
    }

    public void purchaseFuel(int fuelToBuy) {
        player.changeCredits(-1 * fuelToBuy * fuelUnitPrice);
        playerShip.setFuel(playerShip.getFuel() + fuelToBuy);
    }

    public String healthError(double healthToBuy) {
        if (healthToBuy <= 0) {
            return "can only repair positive quantities";
        } else if (healthToBuy > playerShipType.getMaxHealth() - playerShip.getHealth()) {
            return "repairing more health than player ship has";
        } else if (healthToBuy * healthUnitPrice > player.getCredits()) {
            return "too poor, one HP costs " + healthUnitPrice + " here";
        }
        return null;
    }

    public void repairHealth(double healthToBuy) {
        player.changeCredits((int) (-1.0 * healthToBuy * healthUnitPrice));
        playerShip.setHealth(healthToBuy + playerShip.getHealth());
    }

}
