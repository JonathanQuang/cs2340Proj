package cs2340.spacetraders.viewmodels;

import java.util.Map;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Universe.ShipMarket;

public class ShipDisplayViewModel {

    private Player player;
    private ShipMarket shipMarket;

    public ShipDisplayViewModel(Player player) {
        this.player = player;
        shipMarket = new ShipMarket(player);
    }

    public Map<ShipType, Integer> getShipMarketDiffMap() {
        return shipMarket.getShipTypePriceDiffMap();
    }

    public int getShipMarketDiffMapSize() {
        return shipMarket.getMapSize();
    }

    public int getPriceDiffForShipType(ShipType shipType) {
        return shipMarket.getShipTypeDiffPrice(shipType);
    }

    public void updateShipMarket(ShipType shipType) {
        shipMarket.executeShipTradeIn(shipType);
    }

    public boolean isSameShip(ShipType shipType) {
        return (shipType == player.getShipType());
    }

    public String playerCanTradeMessage(ShipType shipType) {
        if (shipMarket.checkCanTradeInShip(shipType)) {
            updateShipMarket(shipType);
            return "Trade Successful";
        }
        return "Not enough money";
    }

}
