package cs2340.spacetraders.entity.Universe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WormholeTest {
    private List<Planet> planetList;
    private List<Planet> planetList2;
    private Wormhole wormhole;

    private static final int TIMEOUT = 200;

    @SuppressWarnings("UnnecessaryBoxing")
    @Before
    public void setUp() {
        planetList = new ArrayList<>();
        planetList.add(new Planet("A0", new RelativePosition(0, 0)));
        planetList.add(new Planet("A1", new RelativePosition(10, 0)));
        planetList.add(new Planet("A2", new RelativePosition(20, 0)));
        planetList.add(new Planet("A3", new RelativePosition(30, 0)));

        planetList.add(new Planet("B0", new RelativePosition(0, 10)));
        planetList.add(new Planet("B1", new RelativePosition(10, 10)));
        planetList.add(new Planet("B2", new RelativePosition(20, 10)));
        planetList.add(new Planet("B3", new RelativePosition(30, 10)));

        planetList.add(new Planet("C0", new RelativePosition(0, 20)));
        planetList.add(new Planet("C1", new RelativePosition(10, 20)));
        planetList.add(new Planet("C2", new RelativePosition(20, 20)));
        planetList.add(new Planet("C3", new RelativePosition(30, 20)));

        planetList.add(new Planet("D0", new RelativePosition(0, 30)));
        planetList.add(new Planet("D1", new RelativePosition(10, 30)));
        planetList.add(new Planet("D2", new RelativePosition(20, 30)));
        planetList.add(new Planet("D3", new RelativePosition(30, 30)));

        /*
        creates a galaxy which looks like:
            A0  A1  A2  A3

            B0  B1  B2  B3

            C0  C1  C2  C3

            D0  D1  D2  D3
         */


        planetList2 = new ArrayList<>();
        planetList2.add(new Planet("A0", new RelativePosition(0, 0)));
        planetList2.add(new Planet("A1", new RelativePosition(5, 0)));
        planetList2.add(new Planet("A2", new RelativePosition(15, 0)));
        planetList2.add(new Planet("A3", new RelativePosition(35, 0)));

        planetList2.add(new Planet("B0", new RelativePosition(0, 5)));
        planetList2.add(new Planet("B1", new RelativePosition(5, 5)));
        planetList2.add(new Planet("B2", new RelativePosition(15, 5)));
        planetList2.add(new Planet("B3", new RelativePosition(35, 5)));

        planetList2.add(new Planet("C0", new RelativePosition(0, 15)));
        planetList2.add(new Planet("C1", new RelativePosition(5, 15)));
        planetList2.add(new Planet("C2", new RelativePosition(15, 15)));
        planetList2.add(new Planet("C3", new RelativePosition(35, 15)));

        planetList2.add(new Planet("D0", new RelativePosition(0, 35)));
        planetList2.add(new Planet("D1", new RelativePosition(5, 35)));
        planetList2.add(new Planet("D2", new RelativePosition(15, 35)));
        planetList2.add(new Planet("D3", new RelativePosition(35, 35)));

                /*
        creates a galaxy which looks like:
            A0  A1      A2              A3

            B0  B1      B2              B3


            C0  C1      C2              C3




            D0  D1      D2              D3
         */
    }

    @Test(timeout = TIMEOUT)
    public void equallyDistancedPlanetsTest() {
        wormhole = new Wormhole(planetList, new RelativePosition(1, 1));
        assertTrue(wormhole.getShipportPlanet().equals(planetList.get(0)));

        wormhole = new Wormhole(planetList, new RelativePosition(15, 15));
        assertTrue(wormhole.getShipportPlanet().equals(planetList.get(11)));

        wormhole = new Wormhole(planetList, new RelativePosition(2, 2));
        assertTrue(wormhole.getShipportPlanet().equals(planetList.get(0)));

        wormhole = new Wormhole(planetList, new RelativePosition(30, 30));
        assertTrue(wormhole.getShipportPlanet().equals(planetList.get(15)));
    }

    @Test(timeout = TIMEOUT)
    public void unequallyDistancedPlanetsTest() {
        wormhole = new Wormhole(planetList2, new RelativePosition(1, 1));
        assertTrue(wormhole.getShipportPlanet().equals(planetList2.get(0)));

        wormhole = new Wormhole(planetList2, new RelativePosition(10, 10));
        assertTrue(wormhole.getShipportPlanet().equals(planetList2.get(5)));

        wormhole = new Wormhole(planetList2, new RelativePosition(30, 30));
        assertTrue(wormhole.getShipportPlanet().equals(planetList2.get(15)));

        wormhole = new Wormhole(planetList2, new RelativePosition(25, 25));
        assertTrue(wormhole.getShipportPlanet().equals(planetList2.get(15)));
    }
}