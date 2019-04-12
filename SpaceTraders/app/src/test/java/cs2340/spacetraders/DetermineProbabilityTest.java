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
import cs2340.spacetraders.entity.Universe.CelestialName;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.PoliticalSystem;
import cs2340.spacetraders.entity.Universe.RelativePosition;
import cs2340.spacetraders.entity.Universe.Resources;
import cs2340.spacetraders.entity.Universe.TechLevel;
import cs2340.spacetraders.model.Model;

import static org.junit.Assert.*;

public class DetermineProbabilityTest {
    private PoliticalSystem politicalSystem;

    private static final int TIMEOUT = 200;

    @Before
    public void setUp() throws Exception {
        politicalSystem = PoliticalSystem.Theocracy;
    }

    @Test(timeout = TIMEOUT)
    public void testNone() {
        String bribery = politicalSystem.getPoliceBriberyAcceptance();
        assertEquals(0, politicalSystem.determineProbability(bribery), 0.1);
    }

    @Test(timeout = TIMEOUT)
    public void testLow() {
        String pirateQuantity = politicalSystem.getPirateQuantity();
        assertEquals(0.25, politicalSystem.determineProbability(pirateQuantity), 0.1);
    }

    @Test(timeout = TIMEOUT)
    public void testSome() {
        String trader = politicalSystem.getTradersQuantity();
        assertEquals(0.5, politicalSystem.determineProbability(trader), 0.1);
    }

    @Test(timeout = TIMEOUT)
    public void testHigh() {
        String police = politicalSystem.getPoliceQuantity();
        assertEquals(0.75, politicalSystem.determineProbability(police), 0.1);
    }

    @Test(timeout = TIMEOUT)
    public void testOther() {
        assertEquals(1, politicalSystem.determineProbability("Other"), 0.1);
    }

}