package cs2340.spacetraders.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cs2340.spacetraders.entity.Market.Good;

public class Inventory implements Serializable {
    private Map<Good, Integer> inventoryGoodCount;
    private Map<Good, Double> averagePurchasePrice;
    private int currCapacity;
    private int maxCapacity;
    private boolean containsIllegalGoods = false;

    /**
     * Constructor
     */
    public Inventory(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        inventoryGoodCount = new HashMap<Good, Integer>();
        averagePurchasePrice = new HashMap<Good, Double>();
        loadDummyData();
    }

    /**
     * populate the inventory with some dummy data.
     */
    private void loadDummyData() {
        for (Good good: Good.values()) {
            addGood(good, 0, 0);
        }
    }

    /**
     * get the inventory
     *
     * @return player inventory
     */
    public Map<Good, Integer> getInventoryGoodCount() {
        return inventoryGoodCount;
    }

    /**
     * get the total number of items
     *
     * @return capacity
     */
    public int getCapacity() {
        return currCapacity;
    }

    public boolean containsIllegalGoods() {
        return containsIllegalGoods;
    }

    public int getGoodAmount(Good good) {
        return inventoryGoodCount.get(good) != null ? inventoryGoodCount.get(good) : 0;
    }

    public double getAvgPurchasePrice(Good good) {
        return averagePurchasePrice.get(good) != null ? averagePurchasePrice.get(good) : 0;
    }

    /**
     * Buying/stealing goods
     * @param good
     * @param amount
     */
    public void addGood(Good good, int amount, double price) {
        currCapacity += amount;
        double total = getGoodAmount(good) * getAvgPurchasePrice(good);
        total += amount * price;

        inventoryGoodCount.put(good, getGoodAmount(good) + amount);
        averagePurchasePrice.put(good, total / getGoodAmount(good));
    }

    public boolean canSellGood(Good good) {
        return getGoodAmount(good) != 0;
    }

    public boolean canBuyGood(int numGoods) {
        return currCapacity + numGoods <= maxCapacity;
    }

    /**
     * Selling/stolen goods
     * @param good
     * @param amount
     */
    public void removeGood(Good good, int amount) {
        currCapacity -= amount;
        inventoryGoodCount.put(good, getGoodAmount(good) - amount);
    }

    public void removeRandomGood() {
        Random random = new Random();
        int ordinal = random.nextInt(inventoryGoodCount.size());
        Good good = Good.values()[ordinal];
        currCapacity -= getGoodAmount(good);
        inventoryGoodCount.put(good, 0);
    }
}
