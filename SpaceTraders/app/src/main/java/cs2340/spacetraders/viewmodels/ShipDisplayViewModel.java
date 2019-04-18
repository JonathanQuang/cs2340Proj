package cs2340.spacetraders.viewmodels;

import java.util.Map;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Universe.ShipMarket;

/**
 * Concrete class implementation of a view model for the ShipDisplayActivity view
 */
public class ShipDisplayViewModel {

    private final Player player;
    private final ShipMarket shipMarket;

    /**
     * constructor for this class
     * @param player the Player object representing the user
     */
    public ShipDisplayViewModel(Player player) {
        this.player = player;
        shipMarket = new ShipMarket(player);
    }

    /**
     * @return a map from shipMarket instance var containing
     * the difference in prices between all shipTypes and
     * the current player's ships
     */
    public Map<ShipType, Integer> getShipMarketDiffMap() {
        return shipMarket.getShipTypePriceDiffMap();
    }

    /**
     * @return shipMarket instance var's Map object size
     */
    public int getShipMarketDiffMapSize() {
        return shipMarket.getMapSize();
    }

    /**
     * @param shipType the shipType to calculate the difference between
     * @return shipMarket instance var's price difference with the player's current ship
     */
    public int getPriceDiffForShipType(ShipType shipType) {
        return shipMarket.getShipTypeDiffPrice(shipType);
    }

    /**
     * tells shipMarket instanceVar to update its difference Map
     * @param shipType the shipType that the player has purchsed
     */
    public void updateShipMarket(ShipType shipType) {
        shipMarket.executeShipTradeIn(shipType);
    }

    /**
     * @param shipType the ShipType that the activity wishes to check
     * @return whether or not the player's ship is the same type as the one being checked
     */
    public boolean isSameShip(ShipType shipType) {
        return (shipType == player.getShipType());
    }

    /**
     * @param shipType the ShipType the player wishes to buy
     * @return a string with a message telling the player whether
     * the trade was a success or they didn't have enough money to buy the ship
     * returns null if the player trade is successful
     *
     */
    public String playerCanTradeErrorMessage(ShipType shipType) {
        if (!(shipMarket.checkCanTradeInShip(shipType))) {
            return "Not enough money";
        }
        if (shipMarket.weaponsCannotBeMoveToNewShip(shipType)) {
            return "You have more weapons than this ship can hold";
        }
        if (shipMarket.cargoCannotBeMovedToNewShip(shipType)) {
            return "You have more cargo than this ship can hold";
        }
        updateShipMarket(shipType);
        return null;
    }

}
