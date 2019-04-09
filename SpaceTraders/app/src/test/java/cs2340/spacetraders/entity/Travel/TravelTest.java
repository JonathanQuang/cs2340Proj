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
        Planet planet1 = makePlanet(celestialNames[0], 0,1);
        Planet planet2 = makePlanet(celestialNames[0], 2,2);
        Planet planet3 = makePlanet(celestialNames[0], 5,5);
        Planet planet4 = makePlanet(celestialNames[0], 100,100);

        planetList.add(planet1);
        planetList.add(planet2);
        planetList.add(planet3);
        planetList.add(planet4);

        travel = new Travel(player, makePlanet(0, 0), planetList);

        travel.findValidPlanets();
        assertEquals(3, travel.getValidPlanets().size());
        assert(travel.getValidPlanets().contains(planet1));
        assert(!travel.getValidPlanets().contains(planet4));
        assertEquals(planet3, travel.getMaxValidPlanetAway());
    }

    @Test(timeout = TIMEOUT)
    public void testFindingMultiValidPlanetsHard() {
        Planet planet1 = makePlanet(celestialNames[0], 0,1);
        Planet planet2 = makePlanet(celestialNames[2], 2,2);
        Planet planet3 = makePlanet(celestialNames[3], 5,5);
        Planet planet4 = makePlanet(celestialNames[4], 100,100);
        Planet planet5 = makePlanet(celestialNames[5], 5,5);
        Planet planet6 = makePlanet(celestialNames[6], -1,-1);
        Planet planet7 = makePlanet(celestialNames[7], -5,-5);
        Planet planet8 = makePlanet(celestialNames[8], 4,9);

        planetList.add(planet1);
        planetList.add(planet2);
        planetList.add(planet3);
        planetList.add(planet4);
        planetList.add(planet5);
        planetList.add(planet6);
        planetList.add(planet7);
        planetList.add(planet8);

        Planet currentPlanet = makePlanet(0, 0);
        travel = new Travel(player, currentPlanet, planetList);

        travel.findValidPlanets();
        assertEquals(7, travel.getValidPlanets().size());
        assert(travel.getValidPlanets().contains(planet1));
        assert(!travel.getValidPlanets().contains(planet4));
        assert(!travel.getValidPlanets().contains(currentPlanet));
        assertEquals(planet8, travel.getMaxValidPlanetAway());
    }
}