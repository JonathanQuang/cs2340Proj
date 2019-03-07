package cs2340.spacetraders.viewmodels;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;

public class MarketScreenViewModel {

    private PlanetInventory planetInventory;
    private Inventory playerInventory;
    private Good currentGood;
    private Player currentPlayer;

    public MarketScreenViewModel(PlanetInventory planetInventory, Inventory playerInventory) {
        this.planetInventory = planetInventory;
        this.playerInventory = playerInventory;
    }

    public void setGood(Good currentGood) {
        this.currentGood = currentGood;
    }

    public Good getCurrentGood() {
        return currentGood;
    }

    public String popUpBuyStr() {
        return "Planet " + currentGood.toString() + " Supply: " + planetInventory.getGoodCount(currentGood) + "\n"
                + "Buying Price: $" + planetInventory.getBuyPrice(currentGood) + "\n"
                //+ "Quantity Purchasing: ---" + "\n"
                //+ "Total Cost: ---" + "\n";
                + "You currently have " + playerInventory.getGoodAmount(currentGood) + " " + currentGood.getGoodName() + "\n"
                + "You have " + currentPlayer.getCredits() + " credits" + "\n"
                + "Type below amount purchase";
    }

    public String popUpSellStr() {
        return "Your current " + currentGood.toString() + " Supply: " + playerInventory.getGoodAmount(currentGood) + "\n"
                + "Selling Price: $" + planetInventory.getBuyPrice(currentGood) + "\n"
                //+ "Quantity Selling: ---" + "\n"
                //+ "Total Revenue: ---" + "\n"
                //+ "Average Revenue per Unit: ---";
                + "You currently have " + playerInventory.getGoodAmount(currentGood) + " " + currentGood.getGoodName() + "\n"
                + "You have " + currentPlayer.getCredits() + " credits" + "\n"
                + "Type below amount to sell";
    }

    public void buyGood() {
        planetInventory.purchaseGood(currentGood);
        playerInventory.addGood(currentGood, 1, planetInventory.getBuyPrice(currentGood));
    }

    public void buyGood(int amount) {
        planetInventory.purchaseGood(currentGood);
        playerInventory.addGood(currentGood, amount, planetInventory.getBuyPrice(currentGood));
        currentPlayer.changeCredits(-1 * amount * planetInventory.getBuyPrice(currentGood));
    }

    public void setPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean validQuantityToBuy(String buyText) {
        int quant = -1;
        try {
            quant = Integer.parseInt(buyText);
        } catch (Exception e) {
            return false;
        }
        if (quant <= 0 || quant > planetInventory.getGoodCount(currentGood)) {
            return false;
        }
        if (quant * planetInventory.getBuyPrice(currentGood) > currentPlayer.getCredits()) {
            return false;
        }
        if (!(playerInventory.canBuyGood(quant))) {
            return false;
        }
        return true;
    }

    public void sellGood() {

    }
}

