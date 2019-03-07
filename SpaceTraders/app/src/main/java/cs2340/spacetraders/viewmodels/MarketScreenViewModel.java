package cs2340.spacetraders.viewmodels;

import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;

public class MarketScreenViewModel {

    private PlanetInventory pInventory;
    private Good currentGood;
    private String goodText;

    public MarketScreenViewModel(PlanetInventory pInventory) {
        this.pInventory = pInventory;
    }

    public void setGoodViaString(String inputStr) {
        goodText = inputStr;
        currentGood = Good.valueOf(inputStr);
    }

    public void setGood(Good currentGood) {
        this.currentGood = currentGood;
    }

    public Good getGood() {
        return currentGood;
    }

    public String retBuyStr() {
        return "This planet has " + pInventory.getGoodCount(currentGood) + " " + goodText + " How many of this would you like to buy";
    }

}
