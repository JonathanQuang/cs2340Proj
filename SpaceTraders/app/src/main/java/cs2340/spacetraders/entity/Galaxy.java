package cs2340.spacetraders.entity;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Galaxy {
    private Map<String, Planet> wholePlanetList;
    private List<RelativePosition> systemPositionList;
    private Set<CelestialName> usedCelestialNames;
    private List<SolarSystem> solarSystemList;
    private RelativePosition mapSize;
    private String[][] galaxyMap;
    private Random rand = new Random();

    /**
     * Initializes Galaxy and creates solarSystem until there are no more names left.
     * Also, creates an prints out map of galaxy.
     */
    public Galaxy() {
        wholePlanetList = new HashMap<String, Planet>();
        systemPositionList = new ArrayList<RelativePosition>();
        usedCelestialNames = new HashSet<CelestialName>();
        mapSize = new RelativePosition(30, 30);
        galaxyMap = new String[mapSize.getX()][mapSize.getY()];
        solarSystemList = new ArrayList<SolarSystem>();
        for (String[] row: galaxyMap)
            Arrays.fill(row, " ");

        while (usedCelestialNames.size() + 5 < CelestialName.values().length){
            solarSystemList.add(makeSolarSystem());
        }
        printMap();
    }

    /**
     * Makes a solar system that randomizes all its attributes
     */
    private SolarSystem makeSolarSystem() {
        int planetNum = rand.nextInt(5) + 1;
        RelativePosition center = getValidSystemPoint(planetNum);
        CelestialName systemName = getNonRepeatedCelestialName();
        String size = getSystemSize(planetNum);

        Log.d("Planet", "-----System " + systemName.getName() + " created at " + center + " with " + planetNum + " planets----");
        placeSystemOnMap(center);
        return new SolarSystem(systemName, center, planetNum, size,this);
    }

    /**
     * Gets a valid point within the galaxy to place the system. Checks location of previously
     * place systems and if the corners are within the map
     * @param planetNum the number of planets the system has
     * @return the center point for new system which doesn't overlay other systems
     */
    private RelativePosition getValidSystemPoint(int planetNum) {
        RelativePosition center;
        do {
            int x = rand.nextInt(mapSize.getX());
            int y = rand.nextInt(mapSize.getY());
            int r = (planetNum + 2)/3;
            center = new RelativePosition(x, y, r, true);
        } while (systemPositionList.contains(center) || !isValidCornerPoint(center));
        systemPositionList.add(center);
        return center;
    }

    /**
     * Checks if the system's area/corners is completely within the map
     * @param point the center of a system
     * @return if the system's corners are within the bounds of the map
     */
    private boolean isValidCornerPoint(RelativePosition point) {
        int x = point.getX();
        int y = point.getY();
        int r = point.getRectRadius();
        return 0 <= x - r && x + r < mapSize.getX() && 0 <= y - r && y + r < mapSize.getY();
    }

    /**
     * Gets a non-repeated celestial name to ensure all bodies haves different names
     * @return a non-repeated celestial name
     */
    public CelestialName getNonRepeatedCelestialName() {
        CelestialName celestialName;
        do {
            celestialName = CelestialName.values()[rand.nextInt(CelestialName.values().length)];
        } while (usedCelestialNames.contains(celestialName));
        usedCelestialNames.add(celestialName);
        return celestialName;
    }

    /**
     * @param planetNum the number of planets a system has
     * @return the string representation of size of planet
     */
    private String getSystemSize(int planetNum) {
        String sizeStr = "";
        if (planetNum <= 1) {sizeStr = "Small"; }
        else if (planetNum <= 3) { sizeStr = "Moderate"; }
        else if (planetNum <= 5) { sizeStr = "Large"; }
        return sizeStr;
    }

    /**
     * Draws the outline of a system on the map with "#" and the center "0"
     * @param position the center of a system
     */
    private void placeSystemOnMap(RelativePosition position) {
        int x = position.getX();
        int y = position.getY();
        int r = position.getRectRadius();

        for (int i = -r; i <= r; galaxyMap[x + i][y + r] = "#", i++) ;
        for (int i = -r; i <= r; galaxyMap[x + i][y - r] = "#", i++) ;
        for (int i = -r; i <= r; galaxyMap[x + r][y + i] = "#", i++) ;
        for (int i = -r; i <= r; galaxyMap[x - r][y + i] = "#", i++) ;
        galaxyMap[x][y] = "O";
    }

    /**
     * Prints the galaxy map
     */
    private void printMap() {
        Log.d("Planet", "-----MAP-----");
        for (int i = 0; i < galaxyMap.length; i++) {
            Log.d("Planet", Arrays.toString(galaxyMap[i]));
        }
    }

    /**
     * Gets the planet given its name
     * @param name the name of a planet that may or maybe not exist
     * @return the planet searched (null if not)
     */
    private Planet searchPlanetByName(String name) {
        return wholePlanetList.containsKey(name) ? wholePlanetList.get(name) : null;
    }

    /**
     * gets the galaxy map
     * @return gets the galaxy map
     */
    public String[][] getGalaxyMap() {
        return galaxyMap;
    }

    public Map<String, Planet> getWholePlanetList() {
        return wholePlanetList;
    }
}
