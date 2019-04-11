package cs2340.spacetraders.viewmodels;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.Wormhole;
import cs2340.spacetraders.model.Model;

/**
 * GalaxyMapViewModel is a class that
 * is a view model for the galaxy map activity
 */
public class GalaxyMapViewModel {

    private Planet currentPlanet;
    private final Player currentPlayer;
    private Wormhole selectedWormhole;

    /**
     *
     * @param currentPlanet the planet the player is currently at
     * @param planetList a list of planets that exist on the galaxy map
     */
    public GalaxyMapViewModel(Planet currentPlanet, List<Planet> planetList) {
        this.currentPlanet = currentPlanet;
        List<Planet> planetList1 = planetList;
        currentPlayer = Model.getInstance().getPlayer();
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
                + currentPlayer.getShip().getFuel();
    }

    /**
     * setter for a selected wormhole
     * @param selectedWormhole wormhole to set selectedWormhole instance var to
     */
    public void setSelectedWormhole(Wormhole selectedWormhole) {
        this.selectedWormhole = selectedWormhole;
    }

    /**
     *
     * @return string that goes into pop up that shows up when a player presses
     * on a wormhole
     */
    public String popUpWormHoleInfo() {
        return "~~~Wormhole~~~\n" + selectedWormhole.toString()
                + "\n The Planet ship port it is: "
                + selectedWormhole.getShipportPlanet().getName()
                + "\n The connect ship port it is: "
                + selectedWormhole.getConnectedWormhole().getShipportPlanet().getName();
    }
}

