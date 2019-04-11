package cs2340.spacetraders.entity.Universe;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.ShipType;

/**
 * Implementation of the ship market
 */
public class ShipMarket implements Serializable {


    private final Player player;
    private final Map<ShipType, Integer> shipTypePriceDiffMap;

    /**
     * Constructor for the ship market
     * @param player current player
     */
    public ShipMarket(Player player) {
        this.player = player;
        shipTypePriceDiffMap = new HashMap<ShipType, Integer>();
        updateDiffMap();
    }

    private void updateDiffMap() {
        ShipType[] shipTypeArray = ShipType.values();
        for (ShipType s : shipTypeArray) {
            shipTypePriceDiffMap.put(s,Integer.valueOf(s.getPrice() - player.getShipTypePrice()));
        }
    }

    /**
     * Checks if the player's current ship can be traded in for a new ship
     * @param shipToGet new ship being bought
     * @return true or false, if valid
     */
    public boolean checkCanTradeInShip(ShipType shipToGet) {
        //check if the player has enough money
        return player.getCredits() >= shipTypePriceDiffMap.get(shipToGet);
    }

    /**
     * Exchanges current ship with new ship
     * @param shipToGet new ship
     */
    public void executeShipTradeIn(ShipType shipToGet) {
        player.changeCredits(shipTypePriceDiffMap.get(shipToGet) * -1);
        player.setShipType(shipToGet);
        updateDiffMap();
    }

    /**
     * Getter for price differences between different ships
     * @return map of price differences
     */
    public Map<ShipType, Integer> getShipTypePriceDiffMap() {return shipTypePriceDiffMap;}

    /**
     * Getter for map size
     * @return price difference map size
     */
    public int getMapSize() {return shipTypePriceDiffMap.size();}

    /**
     * Gets the price difference for a specific ship
     * @param shipType ship type being checked
     * @return price difference
     */
    public int getShipTypeDiffPrice(ShipType shipType) {
        return shipTypePriceDiffMap.get(shipType);
    }

}
