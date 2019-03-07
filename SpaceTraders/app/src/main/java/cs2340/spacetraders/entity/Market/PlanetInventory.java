package cs2340.spacetraders.entity.Market;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlanetInventory {
    private Map<Good, Integer> goodsBuyPrices;
    private Map<Good, Integer> goodsSellPrices;
    private Map<Good, Integer> goodsCount;
    private Map<Good, Boolean> goodsCanSell;
    private Map<Good, Boolean> goodsCanBuy;

    public PlanetInventory() {
        goodsBuyPrices = new HashMap<Good, Integer>();
        goodsSellPrices = new HashMap<Good, Integer>();
        goodsCount = new HashMap<Good, Integer>();
        goodsCanSell = new HashMap<Good, Boolean>();
        goodsCanBuy = new HashMap<Good, Boolean>();
    }

    public void addToPlanetInventory(Good good, int buyPrice, int sellPrice, int count) {
        goodsBuyPrices.put(good, buyPrice);
        goodsSellPrices.put(good, sellPrice);
        goodsCount.put(good, count);
        goodsCanBuy.put(good, true);
        goodsCanSell.put(good, true);
    }

    public void addToPlanetInventory(Good good, int buyPrice, int sellPrice, int count, boolean canBuy, boolean canSell) {
        if (canBuy && buyPrice > 0) {
            goodsBuyPrices.put(good, buyPrice);
            goodsCanBuy.put(good, true);
            goodsCount.put(good, count);
        }

        if (canSell && sellPrice > 0) {
            goodsSellPrices.put(good, sellPrice);
            goodsCanSell.put(good, true);
        }
    }

    public boolean canBuyGood(Good good) {
        return goodsCanBuy.get(good) != null ;
    }

    public boolean canSellGood(Good good) {
        return goodsCanSell.get(good) != null;
    }

    public int getGoodCount(Good good) {
        return goodsCount.get(good) != null ? goodsCount.get(good) : 0;
    }

    public int getBuyPrice(Good good) {
        return goodsBuyPrices.get(good) != null ? goodsBuyPrices.get(good) : 0;
    }

    public int getSellPrice(Good good) {
        return goodsSellPrices.get(good) != null ? goodsSellPrices.get(good) : 0;
    }

    public void purchaseGood(Good good) {
        if (goodsCount.get(good) != null && goodsCount.get(good) != 0) {
            int count = goodsCount.get(good) - 1;
            goodsCount.put(good, count);
            if(count == 0) {
                goodsCanBuy.put(good, false);
            }
        }
    }

    public Set<Good> getSetOfBuyableGoods() {
        return goodsCanBuy.keySet();
    }

    public Set<Good> getSetOfSellableGoods() {
        return goodsCanSell.keySet();
    }

    public String toString() {
        String str = "";
        for (Good good: goodsCanSell.keySet()) {
            str += good.toString() + "(" + getGoodCount(good) + ", ";
            str += getBuyPrice(good) + ", " + getSellPrice(good) + ") ~~" ;
        }
        return str;
    }
}
