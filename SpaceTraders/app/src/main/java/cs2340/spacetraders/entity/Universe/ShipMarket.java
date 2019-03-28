package cs2340.spacetraders.entity.Universe;

import java.util.HashMap;
import java.util.Map;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.ShipType;

public class ShipMarket {


    private Player player;
    private Map<ShipType, Integer> shipTypePriceDiffMap;

    public ShipMarket(Player player) {
        this.player = player;
        shipTypePriceDiffMap = new HashMap<ShipType, Integer>();
        updateDiffMap();
    }

    private void updateDiffMap() {
        ShipType[] shipTypeArray = ShipType.values();
        for (ShipType s : shipTypeArray) {
            shipTypePriceDiffMap.put(s,new Integer(s.getPrice() - player.getShipTypePrice()));
        }
    }

    public boolean checkCanTradeInShip(ShipType shipToGet) {
        //check if the player has enough money
        if (player.getCredits() < shipTypePriceDiffMap.get(shipToGet)) {
            return false;
        }
        return true;
    }

    public void executeShipTradeIn(ShipType shipToGet) {
        player.changeCredits(shipTypePriceDiffMap.get(shipToGet) * -1);
        player.setShipType(shipToGet);
        updateDiffMap();
    }

    public Map<ShipType, Integer> getShipTypePriceDiffMap() {return shipTypePriceDiffMap;}

    public int getMapSize() {return shipTypePriceDiffMap.size();}

    public int getShipTypeDiffPrice(ShipType shipType) {
        return shipTypePriceDiffMap.get(shipType);
    }

}