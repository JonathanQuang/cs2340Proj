package cs2340.spacetraders.entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Goods, Integer> playerInventory;
    private int capacity;

    /**
     * Constructor
     */
    public Inventory() {
        playerInventory = new HashMap<>();
        loadDummyData();
    }

    /**
     * populate the inventory with some dummy data.
     */
    private void loadDummyData() {
        addGood(Goods.Water, 0);
        addGood(Goods.Furs, 0);
        addGood(Goods.Food, 0);
        addGood(Goods.Ores, 0);
        addGood(Goods.Games, 0);
        addGood(Goods.Firearms, 0);
        addGood(Goods.Medicine, 0);
        addGood(Goods.Machines, 0);
        addGood(Goods.Narcotics, 0);
        addGood(Goods.Robots, 0);
    }

    /**
     * get the inventory
     *
     * @return player inventory
     */
    public Map<Goods, Integer> getPlayerInventory() {
        return playerInventory;
    }

    /**
     * get the total number of items
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    public int getGoodAmount(Goods good) {
        return playerInventory.get(good);
    }

    /**
     * Buying/stealing goods
     * @param goods
     * @param amount
     */
    public void addGood(Goods goods, int amount) {
        capacity += amount;
        playerInventory.put(goods, playerInventory.get(goods) + amount);
    }

    /**
     * Selling/stolen goods
     * @param goods
     * @param amount
     */
    public void removeGood(Goods goods, int amount) {
        capacity -= amount;
        playerInventory.put(goods, playerInventory.get(goods) - amount);
    }


}
