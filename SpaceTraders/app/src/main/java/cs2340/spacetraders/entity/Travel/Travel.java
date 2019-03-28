package cs2340.spacetraders.entity.Travel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.model.Model;

public class Travel {

    private int FUEL_PER_UNIT_MOVED = 10;
    private Player player;
    private Planet currentPlanet;
    private ArrayList<Planet> validPlanets;
    private Map<Planet, Integer> planetDistances;

    public Travel(Player player) {
        this.player = player;
        validPlanets = new ArrayList<>();
        planetDistances = new HashMap<>();
    }

    public boolean canTravelTo(Planet planet) {
        return validPlanets.contains(planet);
    }

    /**
     * Travel to desired planet if it is a valid planet.
     * (Maybe have to recalculate validPlanets if you can buy more fuel)
     *
     * @param planet the planet you want to travel to
     * @return if successfully traveled to that planet (0 if success)
     */
    public int travel(Planet planet) {
        Ship ship = player.getShip();
        if (canTravelTo(planet)) {
            int fuel = ship.getFuel();
            int dist = planetDistances.get(planet);
            int fuelUsed = dist * FUEL_PER_UNIT_MOVED;
            ship.setFuel(fuel - fuelUsed);
            //RUN_ENCOUNTERABLE()
            currentPlanet = planet;
            findValidPlanets();
            Model.getInstance().getGame().getGalaxy().setCurrentPlanet(planet);
            return 0;
        }
        return 1;
    }

    private void findValidPlanets() {
        validPlanets.clear();
        planetDistances.clear();
        int maxDistance = radiusOfTravel();
        List<Planet> planetList = Model.getInstance().getGame().getGalaxy().getPlanetList();

        //Brute Force
        for (Planet otherPlanet: planetList) {
            int dist = (int) currentPlanet.getPlanetDistance(otherPlanet);
            if (dist < maxDistance) {
                validPlanets.add(otherPlanet);
                planetDistances.put(otherPlanet, dist);
            }
        }
    }

    public int radiusOfTravel() {
        return player.getShip().getFuel() / FUEL_PER_UNIT_MOVED;
    }
}
