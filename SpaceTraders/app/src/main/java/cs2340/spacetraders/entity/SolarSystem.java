package cs2340.spacetraders.entity;

import java.util.Random;

public class SolarSystem {
    private Planet[] plantList;
    private CelestialName name;
    private Position center;
    private int planetNum;
    private String size;
    private Random rand = new Random();

    public SolarSystem(Position center, int planetNum) {
        this.center = center;
        this.planetNum = planetNum;

        for (int i = 0; i < planetNum; i++) {
            makePlanet();
        }
    }

    public void makePlanet() {
        PoliticalSystem politicalSystem= PoliticalSystem.values()[rand.nextInt(PoliticalSystem.values().length)];
        TechLevel techLevel = TechLevel.values()[rand.nextInt(TechLevel.values().length)];
        Resources resources = Resources.values()[rand.nextInt(Resources.values().length)];
        
        CelestialName celestialName = CelestialName.values()[rand.nextInt(CelestialName.values().length)];

    }
}
