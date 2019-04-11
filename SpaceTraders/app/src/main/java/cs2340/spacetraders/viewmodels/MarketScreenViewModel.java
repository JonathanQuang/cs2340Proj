package cs2340.spacetraders.viewmodels;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Market.Market;


public class MarketScreenViewModel {


    private final Market currentMarket;

    public MarketScreenViewModel(PlanetInventory planetInventory, Inventory playerInventory) {
        currentMarket = new Market(planetInventory, playerInventory);
    }

    public void setGood(Good currentGood) {
        currentMarket.setGood(currentGood);
    }

    public Good getCurrentGood() {
        return currentMarket.getCurrentGood();
    }

    public String popUpBuyStr() {
        return "Planet " + currentMarket.getCurrentGood().toString() + " Supply: "
                + currentMarket.getCurrentGoodCountInPlanet() + "\n"
                + "Buying Price: $" + currentMarket.getPlanetBuyPrice() + "\n"
                //+ "Quantity Purchasing: ---" + "\n"
                //+ "Total Cost: ---" + "\n";
                + "You currently have " + currentMarket.getCurrentGoodCountInPlayer() + " "
                + currentMarket.getGoodName() + "\n"
                + "You have " + currentMarket.getPlayerCredits() + " credits" + "\n"
                + "Type below amount purchase";
    }

    public String popUpSellStr() {
        return "Your current " + currentMarket.getCurrentGood().toString() + " Supply: "
                + currentMarket.getCurrentGoodCountInPlayer() + "\n"
                + "Selling Price: $" + currentMarket.getPlanetSellPrice() + "\n"
                //+ "Quantity Selling: ---" + "\n"
                //+ "Total Revenue: ---" + "\n"
                //+ "Average Revenue per Unit: ---";
                + "You currently have " + currentMarket.getCurrentGoodCountInPlayer() + " "
                + currentMarket.getGoodName() + "\n"
                + "You have " + currentMarket.getPlayerCredits() + " credits" + "\n"
                + "Type below amount to sell";
    }

    public void buyGood() {
        currentMarket.buyGood();
    }

    public void buyGood(int amount) {
        currentMarket.buyGood(amount);
    }

    public void setPlayer(Player currentPlayer) {
        currentMarket.setPlayer(currentPlayer);
    }

    public boolean validQuantityToBuy(String buyText) {
        return currentMarket.validQuantityToBuy(buyText);
    }


    public void sellGood(int amount) {
        currentMarket.sellGood(amount);
    }

    public boolean validQuantityToSell(String sellText) {
        return currentMarket.validQuantityToSell(sellText);
    }
}

