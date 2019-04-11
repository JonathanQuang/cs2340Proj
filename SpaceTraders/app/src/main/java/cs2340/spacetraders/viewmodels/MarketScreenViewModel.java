package cs2340.spacetraders.viewmodels;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Market.Market;

/**
 * MarketScreenViewModel is the viewModel for the
 * marketScreen Activity following the MVVM design
 */
public class MarketScreenViewModel {


    private final Market currentMarket;

    /**
     * Constructor for this class
     * @param planetInventory the inventory of the planet that the player is currently at
     * @param playerInventory the inventory of the player
     */
    public MarketScreenViewModel(PlanetInventory planetInventory, Inventory playerInventory) {
        currentMarket = new Market(planetInventory, playerInventory);
    }

    /**
     * Setter for currentGood instance var
     * @param currentGood the good to set currentGood to in currentMarket
     */
    public void setGood(Good currentGood) {
        currentMarket.setGood(currentGood);
    }

    /**
     * getter for currentGood
     * @return the currentGood instance var in currentMarket
     */
    public Good getCurrentGood() {
        return currentMarket.getCurrentGood();
    }

    /**
     * @return string representing information relevant to player's intended purchase
     */
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

    /**
     * @return string representing information relevant to player's intended sale
     */
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

    /**
     * Tells current market to purchase one version of the good it is holding
     */
    public void buyGood() {
        currentMarket.buyGood();
    }

    /**
     * Tells current market to purchase the player's requested amount of a good
     * @param amount the quantity the player wishes to purchase
     */
    public void buyGood(int amount) {
        currentMarket.buyGood(amount);
    }

    /**
     * sets the player for the currentMarket instance var
     * @param currentPlayer a Player representing the user
     */
    public void setPlayer(Player currentPlayer) {
        currentMarket.setPlayer(currentPlayer);
    }

    /**
     *
     * @param buyText a string representation of how much the player wishes to buy
     * @return whether it is possible for the player to buy that amount of a good
     */
    public boolean validQuantityToBuy(String buyText) {
        return currentMarket.validQuantityToBuy(buyText);
    }

    /**
     * tells current market instanceVar to sell some amount of the player's goods
     * @param amount the amount of a good the player wishes to sell
     */
    public void sellGood(int amount) {
        currentMarket.sellGood(amount);
    }
    /**
     *
     * @param sellText a string representation of how much the player wishes to sell
     * @return whether it is possible for the player to sell that amount of a good
     */
    public boolean validQuantityToSell(String sellText) {
        return currentMarket.validQuantityToSell(sellText);
    }
}

