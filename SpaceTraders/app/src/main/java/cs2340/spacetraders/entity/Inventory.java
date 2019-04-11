package cs2340.spacetraders.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cs2340.spacetraders.entity.Market.Good;

/**
 * Class for an inventory that contains goods
 */
public class Inventory implements Serializable {
    private Map<Good, Integer> inventoryGoodCount;
    private Map<Good, Double> averagePurchasePrice;
    private int currCapacity;
    private int maxCapacity;
    private boolean containsIllegalGoods;

    /**
     * Constructor for an inventory
     * @param maxCapacity maximum amount that can be carried
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

    /**
     * Returns whether or not the inventory has illegal goods
     * @return true or false
     */
    public boolean containsIllegalGoods() {
        return containsIllegalGoods;
    }

    /**
     * Gets the number of a good in the inventory
     * @param good Good to be searched
     * @return amount in inventory
     */
    public int getGoodAmount(Good good) {
        return inventoryGoodCount.get(good) != null ? inventoryGoodCount.get(good) : 0;
    }

    /**
     * Gets the average price player purchased the good
     * @param good Good to be searched
     * @return average price player purchased the good
     */
    public double getAvgPurchasePrice(Good good) {
        return averagePurchasePrice.get(good) != null ? averagePurchasePrice.get(good) : 0;
    }

    /**
     * Adds a good to the inventory
     * @param good good to be added
     * @param amount amount of the good to be added
     * @param price the price the player bought the good
     */
    public void addGood(Good good, int amount, double price) {
        currCapacity += amount;
        double total = getGoodAmount(good) * getAvgPurchasePrice(good);
        total += amount * price;

        inventoryGoodCount.put(good, getGoodAmount(good) + amount);
        averagePurchasePrice.put(good, total / getGoodAmount(good));
    }

    /**
     * Checks if the good can be sold
     * @param good good to be sold
     * @return true or false
     */
    public boolean canSellGood(Good good) {
        return getGoodAmount(good) != 0;
    }

    /**
     * Checks if the good can be bought
     * @param numGoods number of goods player wants to buy
     * @return true or false
     */
    public boolean canBuyGood(int numGoods) {
        return currCapacity + numGoods <= maxCapacity;
    }

    /**
     * Selling/stolen goods
     * @param good good being removed from inventory
     * @param amount amount of goods being removed
     */
    public void removeGood(Good good, int amount) {
        currCapacity -= amount;
        inventoryGoodCount.put(good, getGoodAmount(good) - amount);
    }

    /**
     * Removes a random good from the inventory
     */
    public void removeRandomGood() {
        Random random = new Random();
        int ordinal = random.nextInt(inventoryGoodCount.size());
        Good good = Good.values()[ordinal];
        currCapacity -= getGoodAmount(good);
        inventoryGoodCount.put(good, 0);
    }
}
