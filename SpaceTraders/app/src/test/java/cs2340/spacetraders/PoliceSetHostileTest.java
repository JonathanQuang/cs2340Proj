package cs2340.spacetraders;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs2340.spacetraders.entity.Difficulty;
import cs2340.spacetraders.entity.Game;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Travel.Police;
import cs2340.spacetraders.entity.Travel.Travel;
import cs2340.spacetraders.entity.Universe.CelestialName;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.PoliticalSystem;
import cs2340.spacetraders.entity.Universe.RelativePosition;
import cs2340.spacetraders.entity.Universe.Resources;
import cs2340.spacetraders.entity.Universe.TechLevel;
import cs2340.spacetraders.model.Model;

import static org.junit.Assert.*;

public class PoliceSetHostileTest {
    private Player player;
    private List<Planet> planetList;
    private CelestialName[] celestialNames;
    private Planet planet;
    private Police police;
    private Difficulty difficulty;
    private Game game;
    private Model model;

    private static final int TIMEOUT = 200;

    @Before
    public void setUp() throws Exception {
        model = Model.getInstance();
        model.createGame(Difficulty.Easy);
        game = model.getGame();
        player = new Player("Gabe", 0, 0, 0, 0);
        model.setPlayer(player);
        Ship ship = player.getShip();
        planetList = new ArrayList<Planet>();
        celestialNames = CelestialName.values();
    }

    private Planet makePlanet(int x, int y) {
        return makePlanet(CelestialName.values()[99], x, y);
    }

    private Planet makePlanet(CelestialName name, int x, int y) {
        return new Planet(name, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(x, y), null, null);
    }

    @Test(timeout = TIMEOUT)
    public void testDefaultNonhostile() {
        planet = makePlanet(0, 0);
        police = new Police(planet);
        assertEquals(false, police.setHostile());
    }

    @Test(timeout = TIMEOUT)
    public void testHostile() {
        planet = makePlanet(0, 0);
        police = new Police(planet);
        player.setCriminalStatus(true);
        assertEquals(true, police.setHostile());
        assertEquals(0, police.getIgnoreChance(), 0.1);
        assertEquals(0.9, police.getAttackChance(), 0.1);
    }
}