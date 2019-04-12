package cs2340.spacetraders.viewmodels;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.Wormhole;
import cs2340.spacetraders.model.Model;

/**
 * GalaxyMapViewModel is a class that
 * is a view model for the galaxy map activity
 */
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

    /**
     *
     * @param currentPlanet the planet the player is currently at
     * @param planetList a list of planets that exist on the galaxy map
     */
    public GalaxyMapViewModel(Planet currentPlanet, List<Planet> planetList) {
        this.currentPlanet = currentPlanet;
        this.planetList = planetList;
        currentPlayer = model.getPlayer();
        ship = currentPlayer.getShip();
    }


    /**
     * @param currentPlanet the planet set currentPlanet to
     */
    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    /**
     * @return text for the pop up generated when a player presses on a planet
     */
    public String popUpPlanetInfo() {
        return "~~~Planet~~~\n" + currentPlanet.toString() + "\nFuel: "
                + ship.getFuel();
    }

    /**
     * setter for a selected wormhole
     * @param selectedWormhole wormhole to set selectedWormhole instance var to
     */
    public void setSelectedWormhole(Wormhole selectedWormhole) {
        this.selectedWormhole = selectedWormhole;
        connectedWormhole = selectedWormhole.getConnectedWormhole();
        shipPortPlanetEnter = selectedWormhole.getShipportPlanet();
        shipPortPlanetExit = connectedWormhole.getShipportPlanet();
    }

    /**
     *
     * @return string that goes into pop up that shows up when a player presses
     * on a wormhole
     */
    public String popUpWormHoleInfo() {
        return "~~~Wormhole~~~\n" + selectedWormhole.toString()
                + "\n The Planet ship port it is: "
                + shipPortPlanetEnter.getName()
                + "\n The connect ship port it is: "
                + shipPortPlanetExit.getName();
    }
}

