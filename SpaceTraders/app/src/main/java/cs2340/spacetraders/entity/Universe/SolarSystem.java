package cs2340.spacetraders.entity.Universe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolarSystem implements Serializable {
    private Planet[] planetList;
    private List<RelativePosition> planetPositions;
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

        planetPositions = new ArrayList<>();

        planetList = new Planet[planetNum];
        for (int i = 0; i < planetList.length; i++) {
            planetList[i] = makePlanet(i);
//            Log.d("Planet ", planetList[i].toString());
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
        parentGalaxy.getGalaxyMap()[point.getY()][point.getX()] = "*";

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
        if (size == 0) {return "Small"; }
        else if (size == 1) { return"Medium"; }
        else { return "Large"; }
    }

    public String getSize() {
        return size;
    }

    public Planet[] getPlanetList() {
        return planetList;
    }

    public RelativePosition getCenter() {
        return center;
    }
}
