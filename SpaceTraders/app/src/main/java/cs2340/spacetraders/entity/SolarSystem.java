package cs2340.spacetraders.entity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SolarSystem {
    private Planet[] plantList;
    private Set<RelativePosition> planetPositions;
    private CelestialName name;
    private RelativePosition center;
    private String size;
    private Galaxy parentGalaxy;
    private Random rand = new Random();

    public SolarSystem(CelestialName name, RelativePosition center, int planetNum, String size, Galaxy parentGalaxy) {
        this.name = name;
        this.center = center;
        this.size = size;
        this.parentGalaxy = parentGalaxy;

        planetPositions = new HashSet<RelativePosition>();
        plantList = new Planet[planetNum];
        for (int i = 0; i < plantList.length; i++) {
            makePlanet(i);
        }
    }

    private void makePlanet(int i) {
        CelestialName celestialName = parentGalaxy.getNonRepeatedCelestialName();
        TechLevel techLevel = TechLevel.values()[rand.nextInt(TechLevel.values().length)];
        Resources resources = Resources.values()[rand.nextInt(Resources.values().length)];
        PoliticalSystem politicalSystem = PoliticalSystem.values()[rand.nextInt(PoliticalSystem.values().length)];
        RelativePosition point = getValidPlanetPoint();
        String size = getPlanetSize();
        placeSystemOnMap(point);

        plantList[i] = new Planet(celestialName, techLevel, resources, politicalSystem, point, size);
    }

    private RelativePosition getValidPlanetPoint() {
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
        else if (size == 1) { sizeStr = "Moderate"; }
        else if (size == 2) { sizeStr = "Large"; }
        return sizeStr;
    }

    private void placeSystemOnMap(RelativePosition position) {
        int x = position.getX();
        int y = position.getY();
        String[][] galaxyMap = parentGalaxy.getGalaxyMap();
        galaxyMap[x][y] = "*";
        parentGalaxy.setGalaxyMap(galaxyMap);
    }
}
