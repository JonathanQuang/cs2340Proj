package cs2340.spacetraders.viewmodels;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.Wormhole;
import cs2340.spacetraders.model.Model;

public class GalaxyMapViewModel {

    private Planet currentPlanet;
    private final List<Planet> planetList;
    private final Player currentPlayer;
    private Wormhole selectedWormhole;

    public GalaxyMapViewModel(Planet currentPlanet, List<Planet> planetList) {
        this.currentPlanet = currentPlanet;
        this.planetList = planetList;
        currentPlayer = Model.getInstance().getPlayer();
    }


    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public String popUpPlanetInfo() {
        return "~~~Planet~~~\n" + currentPlanet.toString() + "\nFuel: "
                + currentPlayer.getShip().getFuel();
    }

    public void setSelectedWormhole(Wormhole selectedWormhole) {
        this.selectedWormhole = selectedWormhole;
    }

    public String popUpWormHoleInfo() {
        return "~~~Wormhole~~~\n" + selectedWormhole.toString()
                + "\n The Planet ship port it is: "
                + selectedWormhole.getShipportPlanet().getName()
                + "\n The connect ship port it is: "
                + selectedWormhole.getConnectedWormhole().getShipportPlanet().getName();
    }
}

