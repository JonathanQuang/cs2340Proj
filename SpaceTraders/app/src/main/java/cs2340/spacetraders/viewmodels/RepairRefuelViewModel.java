package cs2340.spacetraders.viewmodels;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;

public class RepairRefuelViewModel {
    private final Ship playerShip;
    private final ShipType playerShipType;
    private final int FUEL_UNIT_PRICE = 10;
    private final int HEALTH_UNIT_PRICE = 20;
    private final Player player;


    public RepairRefuelViewModel(Player player) {
        this.player = player;
        playerShip = player.getShip();
        playerShipType = playerShip.getShipType();
    }

    public double getMaxHealth() {return (double) playerShipType.getMaxHealth();}

    public int getMaxFuel() {return playerShipType.getMaxFuel();}

    public double getPlayerHealth() {return playerShip.getHealth();}

    public int getPlayerFuel() {return playerShip.getFuel();}

    public int getFuelUnitPrice() {return FUEL_UNIT_PRICE;}

    public int getHealthUnitPrice() {return HEALTH_UNIT_PRICE;}

    public String fuelError(double fuelToBuy) {
        if (fuelToBuy <= 0) {
           return "please enter a positive quantity";
        } else if (fuelToBuy > (playerShipType.getMaxFuel() - playerShip.getFuel())) {
            return "buying more fuel than player ship can hold";
        } else if ((fuelToBuy * FUEL_UNIT_PRICE) > (player.getCredits())) {
            return "too poor, fuel costs " + FUEL_UNIT_PRICE + " here";
        }
        return null;
    }

    public void purchaseFuel(int fuelToBuy) {
        player.changeCredits(-1 * fuelToBuy * FUEL_UNIT_PRICE);
        playerShip.setFuel(playerShip.getFuel() + fuelToBuy);
    }

    public String healthError(double healthToBuy) {
        if (healthToBuy <= 0) {
            return "can only repair positive quantities";
        } else if (healthToBuy > (playerShipType.getMaxHealth() - playerShip.getHealth())) {
            return "repairing more health than player ship has";
        } else if (healthToBuy * HEALTH_UNIT_PRICE > (player.getCredits())) {
            return "too poor, one HP costs " + HEALTH_UNIT_PRICE + " here";
        }
        return null;
    }

    public void repairHealth(int healthToBuy) {
        player.changeCredits(-1 * healthToBuy * HEALTH_UNIT_PRICE);
        playerShip.setHealth(healthToBuy + playerShip.getHealth());
    }

    public int getPlayerCredits() {
        return player.getCredits();
    }

}
