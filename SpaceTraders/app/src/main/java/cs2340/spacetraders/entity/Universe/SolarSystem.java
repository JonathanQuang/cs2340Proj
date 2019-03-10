package cs2340.spacetraders.entity.Universe;

import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SolarSystem {
    private Planet[] planetList;
    private Set<RelativePosition> planetPositions;
    private CelestialName name;
    private RelativePosition center;
    private String size;
    private Galaxy parentGalaxy;
    private Random rand;

    public SolarSystem(CelestialName name, RelativePosition center, int planetNum, String size, Galaxy parentGalaxy) {
        this.name = name;
        this.center = center;
        this.size = size;
        this.parentGalaxy = parentGalaxy;
        rand = new Random();

        planetPositions = new HashSet<RelativePosition>();

        planetList = new Planet[planetNum];
        for (int i = 0; i < planetList.length; i++) {
            planetList[i] = makePlanet(i);
            Log.d("Planet ", planetList[i].toString());
            parentGalaxy.getPlanetNameMap().put(planetList[i].getName().toString(), planetList[i]);
        }

    }

    /**
     * Creates random variable for planet's attributes and added planet's info to map
     * and list of planets
     * @param i the number of the planet
     * @return the created planet
     */
    private Planet makePlanet(int i) {
        CelestialName celestialName = parentGalaxy.getNonRepeatedCelestialName();
        TechLevel techLevel = TechLevel.values()[rand.nextInt(TechLevel.values().length)];
        Resources resources = Resources.values()[rand.nextInt(Resources.values().length)];
        PoliticalSystem politicalSystem = PoliticalSystem.values()[rand.nextInt(PoliticalSystem.values().length)];
        RelativePosition point = getValidUnusedPoint();
        String size = getPlanetSize();
        parentGalaxy.getGalaxyMap()[point.getX()][point.getY()] = "*";

        return new Planet(celestialName, techLevel, resources, politicalSystem, point, size, this);
    }


    /**
     * @return a random point with the system that instead overlapping another planet
     */
    public RelativePosition getValidUnusedPoint() {
        RelativePosition point;
        do {
            int x = rand.nextInt(2 * center.getRectRadius() + 1) + center.getX() - center.getRectRadius();
            int y = rand.nextInt(2 * center.getRectRadius() + 1) + center.getY() - center.getRectRadius();
            point = new RelativePosition(x, y);
        } while (planetPositions.contains(point));
        planetPositions.add(point);
        return point;
    }

    private String getPlanetSize() {
        int size = rand.nextInt(3);
        String sizeStr = "";
        if (size == 0) {sizeStr = "Small"; }
        else if (size == 1) { sizeStr = "Medium"; }
        else if (size == 2) { sizeStr = "Large"; }
        return sizeStr;
    }

    public String getSize() {
        return size;
    }

    public Planet[] getPlanetList() {
        return planetList;
    }
}
