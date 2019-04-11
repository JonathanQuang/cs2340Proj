package cs2340.spacetraders.entity.Market;

import android.util.Log;

import java.io.Serializable;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;


public class Market implements Serializable {

    private PlanetInventory planetInventory;
    private Inventory playerInventory;
    private Good currentGood;
    private Player currentPlayer;

    public Market(PlanetInventory planetInventory, Inventory playerInventory) {
        this.planetInventory = planetInventory;
        this.playerInventory = playerInventory;
    }

    public Good getCurrentGood() {
        return currentGood;
    }

    public void setGood(Good currentGood) {
        this.currentGood = currentGood;
    }

    public int getCurrentGoodCountInPlanet() {
        return planetInventory.getGoodCount(currentGood);
    }

    public int getCurrentGoodCountInPlayer() {
        return playerInventory.getGoodAmount(currentGood);
    }

    public int getPlanetBuyPrice() {
        return planetInventory.getBuyPrice(currentGood);
    }

    public String getGoodName() {
        return currentGood.getGoodName();
    }

    public int getPlayerCredits() {
        return currentPlayer.getCredits();
    }

    public int getPlanetSellPrice() {
        return planetInventory.getSellPrice(currentGood);
    }

    public void buyGood() {
        planetInventory.purchaseGood(currentGood);
        playerInventory.addGood(currentGood,1, planetInventory.getBuyPrice(currentGood));
    }

    public void buyGood(int amount) {
        planetInventory.purchaseGood(currentGood, amount);
        playerInventory.addGood(currentGood,amount, planetInventory.getBuyPrice(currentGood));
        currentPlayer.changeCredits(-1*amount*planetInventory.getBuyPrice(currentGood));
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
        Log.d("market debug", quant + " | " + planetInventory.getBuyPrice(currentGood)
                + " | " + currentPlayer.getCredits());
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


    public void sellGood(int amount) {
        planetInventory.updateCountOfSingleGood(currentGood, amount);
        playerInventory.removeGood(currentGood, amount);
        currentPlayer.changeCredits(amount * planetInventory.getSellPrice(currentGood));
    }

    public boolean validQuantityToSell(String sellText) {
        int quant = 0;
        try {
            quant = Integer.parseInt(sellText);
        } catch (Exception e) {
            return false;
        }
        if (quant <= 0 || quant > playerInventory.getGoodAmount(currentGood)) {
            return false;
        }
        return true;

    }

}
