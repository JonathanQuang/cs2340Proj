package cs2340.spacetraders.entity.Travel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Universe.CelestialName;
import cs2340.spacetraders.entity.Universe.Galaxy;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.PoliticalSystem;
import cs2340.spacetraders.entity.Universe.RelativePosition;
import cs2340.spacetraders.entity.Universe.Resources;
import cs2340.spacetraders.entity.Universe.TechLevel;

import static org.junit.Assert.*;

/**
 * Tests for findValidPlanets()
 *
 * @author Daniel Martin
 * @version 1.0
 *
 */
public class TravelTest {
    private Player player;
    private Ship ship;
    private List<Planet> planetList;
    private CelestialName[] celestialNames;
    private Travel travel;

    private static final int TIMEOUT = 200;

    @Before
    public void setUp() throws Exception {
        player = new Player("Daniel", 0, 0, 0, 0);
        ship = player.getShip();
        planetList = new ArrayList<Planet>();
        celestialNames = CelestialName.values();
    }

    private Planet makePlanet(int x, int y) {
        return makePlanet(null, x, y);
    }

    private Planet makePlanet(CelestialName name, int x, int y) {
        return new Planet(name, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(x, y), null, null);
    }


    @Test(timeout = TIMEOUT)
    public void testFindingZeroValidPlanets() {
        travel = new Travel(player, makePlanet(0, 0), new ArrayList<Planet>());
        travel.findValidPlanets();
        assertEquals(0, travel.getValidPlanets().size());
    }

    @Test(timeout = TIMEOUT)
    public void testFindingOneValidPlanetsEasy() {
        CelestialName name = celestialNames[0];
        Planet planet = makePlanet(name, 0,1);
        planetList.add(planet);
        travel = new Travel(player, makePlanet(0, 0), planetList);

        travel.findValidPlanets();
        assertEquals(1, travel.getValidPlanets().size());
        assertEquals(planet, travel.getValidPlanets().get(0));
        assertEquals(new Integer(1), travel.getPlanetDistances().get(planet));
        assertEquals(planet, travel.getMaxValidPlanetAway());
    }

    @Test(timeout = TIMEOUT)
    public void testFindingOneValidPlanetsMedium() {
        CelestialName name = celestialNames[0];
        Planet planet = makePlanet(name, 0,1);
        planetList.add(planet);
        travel = new Travel(player, makePlanet(0, 0), planetList);

        travel.findValidPlanets();
        assertEquals(1, travel.getValidPlanets().size());
        assertEquals(planet, travel.getValidPlanets().get(0));
        assertEquals(new Integer(1), travel.getPlanetDistances().get(planet));
        assertEquals(planet, travel.getMaxValidPlanetAway());
    }

    @Test(timeout = TIMEOUT)
    public void testFindingMultiValidPlanetsEasy() {
        CelestialName name = celestialNames[0];
        Planet planet = makePlanet(name, 0,1);
        planetList.add(planet);
        travel = new Travel(player, makePlanet(0, 0), planetList);

        travel.findValidPlanets();
        assertEquals(1, travel.getValidPlanets().size());
        assertEquals(planet, travel.getValidPlanets().get(0));
        assertEquals(new Integer(1), travel.getPlanetDistances().get(planet));
        assertEquals(planet, travel.getMaxValidPlanetAway());
    }

}