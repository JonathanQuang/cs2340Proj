package cs2340.spacetraders.entity.Universe;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A structure-r to hold planets and their relative position in the galaxy
 */
public class SolarSystem implements Serializable {
    private final Planet[] planetList;
    private final List<RelativePosition> planetPositions;
    private final RelativePosition center;
    private final Galaxy parentGalaxy;
    private final Random rand;
    private final CelestialName name;
    private RelativePosition point;

    /**
     * @param name the name of the solar system
     * @param center the center of the solar system
     * @param planetNum the number of planet in the solar system
     * @param size the size of the solar system
     * @param parentGalaxy the parent galaxy of the solar system
     */
    public SolarSystem(CelestialName name, RelativePosition center,
                       int planetNum, String size, Galaxy parentGalaxy) {
        this.name = name;
        this.center = center;
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
        PoliticalSystem politicalSystem =
                PoliticalSystem.values()[rand.nextInt(PoliticalSystem.values().length)];
        point = getValidUnusedPoint();
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
            int x = ((rand.nextInt((2 * center.getRectRadius()) + 1))
                    + (center.getX() - center.getRectRadius()));
            int y = rand.nextInt(2 * center.getRectRadius() + 1)
                    + (center.getY() - center.getRectRadius());
            point = new RelativePosition(x, y);
        } while (planetPositions.contains(point));
        planetPositions.add(point);
        return point;
    }

    /**
     * @return a random string representation of a planet
     */
    private String getPlanetSize() {
        int size = rand.nextInt(3);
        if (size == 0) {return "Small"; }
        else if (size == 1) { return"Medium"; }
        else { return "Large"; }
    }

    /**
     * @return a list of the planet in the system
     */
    public Planet[] getPlanetList() {
        return planetList;
    }

    /**
     * @return the center of the solar system
     */
    public RelativePosition getCenter() {
        return center;
    }
}
