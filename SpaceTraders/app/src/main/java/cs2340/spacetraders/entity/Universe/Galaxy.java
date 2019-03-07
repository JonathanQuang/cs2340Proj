package cs2340.spacetraders.entity.Universe;

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

    private Map<String, Planet> planetNameMap;
    private List<Planet> planetList;
    private List<RelativePosition> systemPositionList;
    private Set<CelestialName> usedCelestialNames;
    private List<SolarSystem> solarSystemList;
    private RelativePosition mapSize;
    private String[][] galaxyMap;
    private Random rand = new Random();
    private Set<Wormhole> wormholeSet;
    private Planet currentPlanet;

    /**
     * Initializes Galaxy and creates solarSystem until there are no more names left.
     * Also, creates an prints out map of galaxy.
     */
    public Galaxy() {
        solarSystemList = new ArrayList<SolarSystem>();
        planetNameMap = new HashMap<String, Planet>();
        systemPositionList = new ArrayList<RelativePosition>();
        usedCelestialNames = new HashSet<CelestialName>();
        wormholeSet = new HashSet<Wormhole>();
        mapSize = new RelativePosition(35, 35);
        galaxyMap = new String[mapSize.getX()][mapSize.getY()];
        int maxWormmHolePairs = 2;

        for (String[] row: galaxyMap) {Arrays.fill(row, " "); }

        while (usedCelestialNames.size() + 5 < CelestialName.values().length) {
            solarSystemList.add(makeSolarSystem());
        }
        planetList = new ArrayList<Planet>(planetNameMap.values());
        currentPlanet = chooseRandomPlanet();
        Log.d("MarK", currentPlanet.toString());

        for (int i = 0; i < maxWormmHolePairs; i++) { placeWormholePair(); }

        printMap();
    }

    /**
     * Makes a solar system that randomizes all its attributes
     */
    private SolarSystem makeSolarSystem() {
        int planetNum = rand.nextInt(5) + 1;
        CelestialName systemName = getNonRepeatedCelestialName();
        Log.d("Planet", "Getting new system's center position");
        RelativePosition center = getValidSystemPoint(planetNum);
        String size = getSystemSize(planetNum);

        Log.d("Planet", "-----System " + systemName.getName() + " created at " + center + " with " + planetNum + " planets----");
        placeSystemOnMap(center);
        return new SolarSystem(systemName, center, planetNum, size,this);
    }

    /**
     * picks two random systems to place a wormhole in
     */
    private void placeWormholePair(){

        if (solarSystemList.size() <= 2) {
            return;
        }
        int p1 = rand.nextInt(solarSystemList.size());
        int p2 = p1;
        int attempts = 0;
        while (p1 == p2) {
            p2 = rand.nextInt(solarSystemList.size());
            attempts++;
            if (attempts > 20) {
                return;
            }
        }
        RelativePosition relPos1 = solarSystemList.get(p1).getValidUnusedPoint();
        RelativePosition relPos2 = solarSystemList.get(p2).getValidUnusedPoint();

        Wormhole w1 = new Wormhole(relPos1);
        Wormhole w2 = new Wormhole(relPos2);
        w1.joinWormholes(w2);
        galaxyMap[relPos1.getX()][relPos1.getY()] = "@";
        galaxyMap[relPos2.getX()][relPos2.getY()] = "@";
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
            int r = (planetNum + 2)/3;
            int x = rand.nextInt(mapSize.getX() - 2 * r) + r;
            int y = rand.nextInt(mapSize.getY() - 2 * r) + r;
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
        return planetNameMap.containsKey(name) ? planetNameMap.get(name) : null;
    }

    private Planet chooseRandomPlanet() {
        return planetList.get(rand.nextInt(planetList.size()));
    }

    public boolean isGalaxyCreated() {
        return currentPlanet != null || currentPlanet.getInventory() != null;
    }

    /**
     * gets the galaxy map
     * @return gets the galaxy map
     */
    public String[][] getGalaxyMap() {
        return galaxyMap;
    }

    public Map<String, Planet> getPlanetNameMap() {
        return planetNameMap;
    }

    public List<SolarSystem> getSolarSystemList() {
        return solarSystemList;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
}
