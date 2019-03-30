package cs2340.spacetraders.viewmodels;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.model.Model;

public class GalaxyMapViewModel {

    private Planet currentPlanet;
    private List<Planet> planetList;
    private Player currentPlayer;

    public GalaxyMapViewModel(Planet currentPlanet, List<Planet> planetList) {
        this.currentPlanet = currentPlanet;
        this.planetList = planetList;
        currentPlayer = Model.getInstance().getPlayer();
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public String popUpPlanetInfo() {
        return "~~~Planet~~~\n" + currentPlanet.toString() + "\nFuel: " + currentPlayer.getShip().getFuel();
    }
}

