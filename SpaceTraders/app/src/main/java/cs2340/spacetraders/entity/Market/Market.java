package cs2340.spacetraders.entity.Market;

import android.util.Log;

import java.io.Serializable;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;

/**
 * Implementation of a market
 */
public class Market implements Serializable {

    private PlanetInventory planetInventory;
    private Inventory playerInventory;
    private Good currentGood;
    private Player currentPlayer;

    /**
     * Constructor for a market
     * @param planetInventory create an inventory for the planet
     * @param playerInventory get the player's inventory
     */
    public Market(PlanetInventory planetInventory, Inventory playerInventory) {
        this.planetInventory = planetInventory;
        this.playerInventory = playerInventory;
    }

    /**
     * Getter for the selected good
     * @return currentGood
     */
    public Good getCurrentGood() {
        return currentGood;
    }

    /**
     * Setter for the current good
     * @param currentGood current good
     */
    public void setGood(Good currentGood) {
        this.currentGood = currentGood;
    }

    /**
     * Getter for amount of goods the planet has
     * @return count of a good the planet has
     */
    public int getCurrentGoodCountInPlanet() {
        return planetInventory.getGoodCount(currentGood);
    }

    /**
     * Getter for amount of goods the player has
     * @return count of a good the player has
     */
    public int getCurrentGoodCountInPlayer() {
        return playerInventory.getGoodAmount(currentGood);
    }

    /**
     * Getter for the buying price of a good
     * @return buy price of a good
     */
    public int getPlanetBuyPrice() {
        return planetInventory.getBuyPrice(currentGood);
    }

    /**
     * Getter for a good's name
     * @return string representation of a good
     */
    public String getGoodName() {
        return currentGood.getGoodName();
    }

    /**
     * Getter for player's credits
     * @return player's credits
     */
    public int getPlayerCredits() {
        return currentPlayer.getCredits();
    }

    /**
     * Getter for the selling price of a good
     * @return good's selling price
     */
    public int getPlanetSellPrice() {
        return planetInventory.getSellPrice(currentGood);
    }

    /**
     * Planet buys a good
     */
    public void buyGood() {
        planetInventory.purchaseGood(currentGood);
        playerInventory.addGood(currentGood,1, planetInventory.getBuyPrice(currentGood));
    }

    /**
     * Planet buys a set amount of a good
     * @param amount amount the planet buys
     */
    public void buyGood(int amount) {
        planetInventory.purchaseGood(currentGood, amount);
        playerInventory.addGood(currentGood,amount, planetInventory.getBuyPrice(currentGood));
        currentPlayer.changeCredits(-1*amount*planetInventory.getBuyPrice(currentGood));
    }

    /**
     * Setter for the current player
     * @param currentPlayer current player
     */
    public void setPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Checks if the quantity to be bought is valid
     * @param buyText what the player wants to buy
     * @return true or false, if valid
     */
    public boolean validQuantityToBuy(String buyText) {
        int quant = -1;
        try {
            quant = Integer.parseInt(buyText);
        } catch (Exception e) {
            return false;
        }
        Log.d("market debug", quant + " | " + planetInventory.getBuyPrice(currentGood)
                + " | " + currentPlayer.getCredits());
        if ((quant <= 0) || (quant > planetInventory.getGoodCount(currentGood))) {
            return false;
        }
        if ((quant * planetInventory.getBuyPrice(currentGood)) > (currentPlayer.getCredits())) {
            return false;
        }
        if (!(playerInventory.canBuyGood(quant))) {
            return false;
        }
        return true;
    }

    /**
     * Sells a good to the player
     * @param amount amount the player wants to buy
     */
    public void sellGood(int amount) {
        planetInventory.updateCountOfSingleGood(currentGood, amount);
        playerInventory.removeGood(currentGood, amount);
        currentPlayer.changeCredits(amount * planetInventory.getSellPrice(currentGood));
    }

    /**
     * Checks if the number of goods to be sold is valid
     * @param sellText what the planet wants to sell
     * @return true or false, if valid
     */
    public boolean validQuantityToSell(String sellText) {
        int quant = -1;
        try {
            quant = Integer.parseInt(sellText);
        } catch (Exception e) {
            return false;
        }
        if ((quant <= 0) || (quant > playerInventory.getGoodAmount(currentGood))) {
            return false;
        }
        return true;

    }

}
