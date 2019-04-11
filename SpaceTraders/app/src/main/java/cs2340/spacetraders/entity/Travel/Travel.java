package cs2340.spacetraders.entity.Travel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.PlanetaryEvent;
import cs2340.spacetraders.model.Model;

/**
 * Allows the player to travel between planets
 */
public class Travel implements Serializable {

    private final int FUEL_PER_UNIT_MOVED = 10;
    private final Player player;
    private Planet currentPlanet;
    private final List<Planet> validPlanets;
    private final Map<Planet, Integer> planetDistances;
    private Planet maxValidPlanetAway;
    private final List<Planet> planetList;

    /**
     * @param player the main player
     * @param currentPlanet the current player in the galaxy
     * @param planetList the list of planets in the galaxy
     */
    public Travel(Player player, Planet currentPlanet, List<Planet> planetList) {
        this.player = player;
        this.currentPlanet = currentPlanet;
        validPlanets = new ArrayList<>();
        planetDistances = new HashMap<>();
        this.planetList = planetList;
        PlanetaryEvent randomEvent = this.currentPlanet.getPlanetaryEvent();
        findValidPlanets();
    }

    /**
     * @param planet the planet being tested if can travel to
     * @return if the player has enough fuel to travel to that planet
     */
    public boolean canTravelTo(Planet planet) {
        return validPlanets.contains(planet);
    }

    /**
     * Travel to desired planet if it is a valid planet
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
            currentPlanet = planet;
            findValidPlanets();
            Model.getInstance().getGame().getGalaxy().setCurrentPlanet(planet);
            return 0;
        }
        return 1;
    }

    /**
     * @param planet the plant being traveled to with wormhole
     */
    public void wormHoleTravel(Planet planet) {
        currentPlanet = planet;
        findValidPlanets();
        Model.getInstance().getGame().getGalaxy().setCurrentPlanet(planet);
    }

    /**
     * Find all the valid planets in fuel range
     */
    public void findValidPlanets() {
        validPlanets.clear();
        planetDistances.clear();
        int radiusOfTravel = radiusOfTravel();
        int maxPlanetDistance = 0;

        for (Planet otherPlanet: planetList) {
            int dist = (int) currentPlanet.getPlanetDistance(otherPlanet);
            if (dist <= radiusOfTravel && otherPlanet != currentPlanet) {
                validPlanets.add(otherPlanet);
                planetDistances.put(otherPlanet, dist);
                if (dist > maxPlanetDistance) {
                    maxValidPlanetAway = otherPlanet;
                }
            }
        }
    }

    /**
     * @return the radius of travel determined by how much fuel the player has
     */
    public int radiusOfTravel() {
        return player.getShip().getFuel() / FUEL_PER_UNIT_MOVED;
    }

    /**
     * @return get a List of the valid planets
     */
    public List<Planet> getValidPlanets() {
        return validPlanets;
    }

    /**
     * @return gets max planet away in fuel range
     */
    public Planet getMaxValidPlanetAway() {
        return maxValidPlanetAway;
    }

    /**
     * @return gets map of valid planet distances
     */
    public Map<Planet, Integer> getPlanetDistances() {
        return planetDistances;
    }
}
