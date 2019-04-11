package cs2340.spacetraders.entity.Market;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A structure-r for the planet's inventory
 */
public class PlanetInventory implements Serializable {
    private Map<Good, Integer> goodsBuyPrices;
    private Map<Good, Integer> goodsSellPrices;
    private Map<Good, Integer> goodsCount;
    private Map<Good, Boolean> goodsCanSell;
    private Map<Good, Boolean> goodsCanBuy;

    /**
     * Initializes the instances variables of PlanetInventory
     */
    public PlanetInventory() {
        goodsBuyPrices = new HashMap<Good, Integer>();
        goodsSellPrices = new HashMap<Good, Integer>();
        goodsCount = new HashMap<Good, Integer>();
        goodsCanSell = new HashMap<Good, Boolean>();
        goodsCanBuy = new HashMap<Good, Boolean>();
    }

    /**
     * @param good the good being add
     * @param buyPrice the buying price of the good
     * @param sellPrice the selling price of the good
     * @param count the count of the good
     * @param canBuy if can buy the good
     * @param canSell if can sell the good
     */
    public void addToPlanetInventory(Good good, int buyPrice, int sellPrice, int count,
                                     boolean canBuy, boolean canSell) {
        if ((canBuy) && (buyPrice > 0)) {
            goodsBuyPrices.put(good, buyPrice);
            goodsCanBuy.put(good, true);
            goodsCount.put(good, count);
        }

        if ((canSell) && (sellPrice > 0)) {
            goodsSellPrices.put(good, sellPrice);
            goodsCanSell.put(good, true);
        }
    }

    /**
     * @param good the good being updated
     * @param quantityAdded the quantity of a good being added to the inventory
     */
    public void updateCountOfSingleGood(Good good, int quantityAdded) {
        int currentGoodAmount = goodsCount.get(good);
        goodsCount.put(good,currentGoodAmount + quantityAdded);
    }

    /**
     * @param good the good being checked if can buy
     * @return if can buy that particular good
     */
    public boolean canBuyGood(Good good) {
        return goodsCanBuy.get(good) != null ;
    }

    /**
     * @param good the good being checked if can sell
     * @return if can sell that particular good
     */
    public boolean canSellGood(Good good) {
        return goodsCanSell.get(good) != null;
    }

    /**
     * @param good the good in the planet's inventory whose quantity is being checked
     * @return how much of that good in the planet's inventory
     */
    public int getGoodCount(Good good) {
        return goodsCount.get(good) != null ? goodsCount.get(good) : 0;
    }

    /**
     * @param good the good whose buying price is being checked
     * @return how much the good costs
     */
    public int getBuyPrice(Good good) {
        return goodsBuyPrices.get(good) != null ? goodsBuyPrices.get(good) : 0;
    }

    /**
     * @param good the good whose selling price is being checked
     * @return how much the good costs
     */
    public int getSellPrice(Good good) {
        return goodsSellPrices.get(good) != null ? goodsSellPrices.get(good) : 0;
    }

    /**
     * @param good the good being purchased
     */
    public void purchaseGood(Good good) {
        if ((goodsCount.get(good) != null) && (goodsCount.get(good) != 0)) {
            int count = goodsCount.get(good) - 1;
            goodsCount.put(good, count);
            if(count == 0) {
                goodsCanBuy.put(good, false);
            }
        }
    }

    /**
     * @param good the good being purchased
     * @param amountToRemove the amount of the good being bought
     */
    public void purchaseGood(Good good, int amountToRemove) {
        if ((goodsCount.get(good) != null) && (goodsCount.get(good) != 0)) {
            int count = goodsCount.get(good) - amountToRemove;
            goodsCount.put(good, count);
            if(count == 0) {
                goodsCanBuy.put(good, false);
            }
        }
    }

    /**
     * @return the string representation of the planet inventory
     */
    public String toString() {
        String str = "";
        for (Good good: goodsCanSell.keySet()) {
            str += good.toString() + "(" + getGoodCount(good) + ", ";
            str += getBuyPrice(good) + ", " + getSellPrice(good) + ") ~~" ;
        }
        return str;
    }
}
