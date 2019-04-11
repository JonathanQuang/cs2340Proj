package cs2340.spacetraders.viewmodels;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.Wormhole;
import cs2340.spacetraders.model.Model;

public class GalaxyMapViewModel {

    private Planet currentPlanet;
    private final List<Planet> planetList;
    private final Player currentPlayer;
    private Wormhole selectedWormhole;
    private final Model model = Model.getInstance();
    private final Ship ship;
    private Planet shipPortPlanetEnter;
    private Planet shipPortPlanetExit;
    private Wormhole connectedWormhole;

    public GalaxyMapViewModel(Planet currentPlanet, List<Planet> planetList) {
        this.currentPlanet = currentPlanet;
        this.planetList = planetList;
        currentPlayer = model.getPlayer();
        ship = currentPlayer.getShip();
    }


    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public String popUpPlanetInfo() {
        return "~~~Planet~~~\n" + currentPlanet.toString() + "\nFuel: "
                + ship.getFuel();
    }

    public void setSelectedWormhole(Wormhole selectedWormhole) {
        this.selectedWormhole = selectedWormhole;
        connectedWormhole = selectedWormhole.getConnectedWormhole();
        shipPortPlanetEnter = selectedWormhole.getShipportPlanet();
        shipPortPlanetExit = connectedWormhole.getShipportPlanet();
    }

    public String popUpWormHoleInfo() {
        return "~~~Wormhole~~~\n" + selectedWormhole.toString()
                + "\n The Planet ship port it is: "
                + shipPortPlanetEnter.getName()
                + "\n The connect ship port it is: "
                + shipPortPlanetExit.getName();
    }
}

