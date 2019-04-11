package cs2340.spacetraders;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs2340.spacetraders.entity.Universe.CelestialName;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.PoliticalSystem;
import cs2340.spacetraders.entity.Universe.RelativePosition;
import cs2340.spacetraders.entity.Universe.Resources;
import cs2340.spacetraders.entity.Universe.TechLevel;
import cs2340.spacetraders.entity.Universe.Wormhole;

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
        planetList.add(new Planet(CelestialName.Acamar, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(0, 0), null, null)); //A0
        planetList.add(new Planet(CelestialName.Adahn, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(10, 0), null, null)); //A1
        planetList.add(new Planet(CelestialName.Aldea, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(20, 0), null, null)); //A2
        planetList.add(new Planet(CelestialName.Andevian, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(30, 0), null, null)); //A3

        planetList.add(new Planet(CelestialName.Balosnee, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(0, 10), null, null)); //B0
        planetList.add(new Planet(CelestialName.Brax, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(10, 10), null, null)); //B1
        planetList.add(new Planet(CelestialName.Bretel, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(20, 10), null, null)); //B2
        planetList.add(new Planet(CelestialName.Deneb, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(30, 10), null, null)); //B3

        planetList.add(new Planet(CelestialName.Endor, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(0, 20), null, null)); //C0
        planetList.add(new Planet(CelestialName.Esmee, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(10, 20), null, null)); //C1
        planetList.add(new Planet(CelestialName.Exo, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(20, 20), null, null)); //C2
        planetList.add(new Planet(CelestialName.Iodine, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(30, 20), null, null)); //C3

        planetList.add(new Planet(CelestialName.Janus, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(0, 30), null, null)); //D0
        planetList.add(new Planet(CelestialName.Japori, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(10, 30), null, null)); //D1
        planetList.add(new Planet(CelestialName.Jarada, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(20, 30), null, null)); //D2
        planetList.add(new Planet(CelestialName.Kaylon, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(30, 30), null, null)); //D3

        /*
        creates a galaxy which looks like:
            A0  A1  A2  A3

            B0  B1  B2  B3

            C0  C1  C2  C3

            D0  D1  D2  D3
         */


        planetList2 = new ArrayList<>();
        planetList2.add(new Planet(CelestialName.Acamar, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(0, 0), null, null)); //A0
        planetList2.add(new Planet(CelestialName.Adahn, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(5, 0), null, null)); //A1
        planetList2.add(new Planet(CelestialName.Aldea, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(15, 0), null, null)); //A2
        planetList2.add(new Planet(CelestialName.Andevian, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(35, 0), null, null)); //A3

        planetList2.add(new Planet(CelestialName.Balosnee, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(0, 5), null, null)); //B0
        planetList2.add(new Planet(CelestialName.Brax, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(5, 5), null, null)); //B1
        planetList2.add(new Planet(CelestialName.Bretel, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(15, 5), null, null)); //B2
        planetList2.add(new Planet(CelestialName.Deneb, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(35, 5), null, null)); //B3

        planetList2.add(new Planet(CelestialName.Endor, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(0, 15), null, null)); //C0
        planetList2.add(new Planet(CelestialName.Esmee, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(5, 15), null, null)); //C1
        planetList2.add(new Planet(CelestialName.Exo, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(15, 15), null, null)); //C2
        planetList2.add(new Planet(CelestialName.Iodine, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(35, 15), null, null)); //C3

        planetList2.add(new Planet(CelestialName.Janus, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(0, 35), null, null)); //D0
        planetList2.add(new Planet(CelestialName.Japori, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(5, 35), null, null)); //D1
        planetList2.add(new Planet(CelestialName.Jarada, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(15, 35), null, null)); //D2
        planetList2.add(new Planet(CelestialName.Kaylon, TechLevel.HiTech, Resources.DESERT, PoliticalSystem.Anarchy, new RelativePosition(35, 35), null, null)); //D3

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
        assertTrue(wormhole.getShipportPlanet().equals(planetList.get(10)));

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