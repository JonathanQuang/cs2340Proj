package cs2340.spacetraders.viewmodels;

import android.util.Log;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;

public class GalaxyMapViewModel {

    private Planet currentPlanet;
    private Planet[] planetList;
    private Player currentPlayer;

    public GalaxyMapViewModel(Planet currentPlanet, Planet[] planetList) {
        this.currentPlanet = currentPlanet;
        this.planetList = planetList;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public String popUpPlanetInfo() {
        return "~~~Planet~~~\n" + currentPlanet.toString();
    }
}

