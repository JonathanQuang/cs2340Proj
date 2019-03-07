package cs2340.spacetraders.viewmodels;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;

public class MarketScreenViewModel {

    private PlanetInventory planetInventory;
    private Inventory playerInventory;
    private Good currentGood;

    public MarketScreenViewModel(PlanetInventory planetInventory, Inventory playerInventory) {
        this.planetInventory = planetInventory;
        this.playerInventory = playerInventory;
    }

    public void setGood(Good currentGood) {
        this.currentGood = currentGood;
    }

    public String popUpBuyStr() {
        return "Planet " + currentGood.toString() + " Supply: " + planetInventory.getGoodCount(currentGood) + "\n"
                + "Buying Price: $" + planetInventory.getBuyPrice(currentGood) + "\n"
                + "Quantity Purchasing: ---" + "\n"
                + "Total Cost: ---" + "\n";
    }

    public String popUpSellStr() {
        return "Your current " + currentGood.toString() + " Supply: " + playerInventory.getGoodAmount(currentGood) + "\n"
                + "Selling Price: $" + planetInventory.getBuyPrice(currentGood) + "\n"
                + "Quantity Selling: ---" + "\n"
                + "Total Revenue: ---" + "\n"
                + "Average Revenue per Unit: ---";
    }

    public void buyGood() {
        planetInventory.purchaseGood(currentGood);
        playerInventory.addGood(currentGood, 1, planetInventory.getBuyPrice(currentGood));
    }

    public void sellGood() {

    }
}