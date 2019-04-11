package cs2340.spacetraders.entity.Universe;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * A structure-r of all the solar systems and planets
 */
public class Galaxy implements Serializable {

    private Map<String, Planet> planetNameMap;
    private List<Planet> planetList;
    private List<RelativePosition> systemPositionList;
    private Set<CelestialName> usedCelestialNames;
    private List<SolarSystem> solarSystemList;
    private RelativePosition mapSize;
    private String[][] galaxyMap;
    private Random rand = new Random();
    private List<Wormhole[]> wormholePairList;
    private Planet currentPlanet;
    private int mapWidth = 25;
    private int mapHeight = 37;
    private int maxPlantsPerSystem = 6;
    private int maxWormHolePairs = 2;


    /**
     * Initializes Galaxy and creates solarSystem until there are no more names left.
     * Also, creates an prints out map of galaxy.
     */
    public Galaxy() {
        solarSystemList = new ArrayList<>();
        planetNameMap = new HashMap<>();
        systemPositionList = new ArrayList<>();
        usedCelestialNames = new HashSet<>();
        wormholePairList = new ArrayList<Wormhole[]>();
        mapSize = new RelativePosition(mapWidth, mapHeight);
        galaxyMap = new String[mapHeight][mapWidth];

        for (String[] row: galaxyMap) {Arrays.fill(row, " "); }
        while ( (usedCelestialNames.size() + maxPlantsPerSystem) <
                (CelestialName.values().length) ) {
            SolarSystem newSolarSystem = makeSolarSystem();
            if (newSolarSystem != null) {
                solarSystemList.add(newSolarSystem);
            }
        }

        planetList = new ArrayList<Planet>(planetNameMap.values());
        currentPlanet = chooseRandomPlanet();
        Log.d("Current Planet", currentPlanet.toString());

        for (int i = 0; i < maxWormHolePairs; i++) { placeWormholePair(); }
        printMap();
    }

    /**
     * Makes a solar system that randomizes all its attributes
     */
    private SolarSystem makeSolarSystem() {
        int planetNum = rand.nextInt(maxPlantsPerSystem) + 1;
        Log.d("Planet", "Getting new system's center position");
        RelativePosition center = getValidSystemPoint(planetNum);
        if (center == null) { return null; }

        String size = getSystemSize(planetNum);
        CelestialName systemName = getNonRepeatedCelestialName();
        Log.d("Planet", "-----System " + systemName.getName() + " created at "
                + center + " with " + planetNum + " planets----");
        placeSystemOnMap(center);
        return new SolarSystem(systemName, center, planetNum, size,this);
    }

    /**
     * picks two random systems to place a wormhole in
     */
    private void placeWormholePair(){
        SolarSystem solarsystem1 = solarSystemList.get(rand.nextInt(solarSystemList.size()));
        int attempt = 0;
        while (solarsystem1.getCenter().getRectRadius() < 2) {
            solarsystem1 = solarSystemList.get(rand.nextInt(solarSystemList.size()));
            if (++attempt > 500) { return; }
        }

        SolarSystem solarsystem2 = solarSystemList.get(rand.nextInt(solarSystemList.size()));
        attempt = 0;
        while ( (solarsystem2.getCenter().getRectRadius() < 2) || (solarsystem2 == solarsystem1) ) {
            solarsystem2 = solarSystemList.get(rand.nextInt(solarSystemList.size()));
            if (++attempt > 500) { return; }
        }

        Wormhole w1 = new Wormhole(this, solarsystem1.getValidUnusedPoint());
        Wormhole w2 = new Wormhole(this, solarsystem2.getValidUnusedPoint());

        w1.joinWormholes(w2);
        wormholePairList.add(new Wormhole[]{w1, w2});

        galaxyMap[w1.getPosition().getY()][w1.getPosition().getX()] = "@";
        galaxyMap[w2.getPosition().getY()][w2.getPosition().getX()] = "@";
    }

    /**
     * Gets a valid point within the galaxy to place the system. Checks location of previously
     * place systems and if the corners are within the map
     * @param planetNum the number of planets the system has
     * @return the center point for new system which doesn't overlay other systems
     */
    private RelativePosition getValidSystemPoint(int planetNum) {
        RelativePosition center;
        int attempts = 0;
        do {
            int r = (planetNum < 4) ? 1 : 2;
            int x = rand.nextInt((mapSize.getX() - 2 * r)) + r;
            int y = rand.nextInt(mapSize.getY() - 2 * r) + r;
            center = new RelativePosition(x, y, r, true);
            if (++attempts > 200) { return null; }
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
        return (0 <= x - r) && (x + r < mapSize.getX()) && (0 <= y - r) && (y + r < mapSize.getY());
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

        for (int i = -r; i <= r; galaxyMap[y + r][x + i] = "#", i++) ;
        for (int i = -r; i <= r; galaxyMap[y - r][x + i] = "#", i++) ;
        for (int i = -r; i <= r; galaxyMap[y + i][x + r] = "#", i++) ;
        for (int i = -r; i <= r; galaxyMap[y + i][x - r] = "#", i++) ;
        galaxyMap[y][x] = "O";
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
    public Planet searchPlanetByName(String name) {
        return planetNameMap.containsKey(name) ? planetNameMap.get(name) : null;
    }

    /**
     * @return a random planet in the galaxy
     */
    private Planet chooseRandomPlanet() {
        return planetList.get(rand.nextInt(planetList.size()));
    }

    /**
     * @return checks if current planet is null
     */
    public boolean isGalaxyCreated() {
        return (currentPlanet != null) || (currentPlanet.getInventory() != null);
    }

    /**
     * gets the galaxy map
     * @return gets the galaxy map
     */
    public String[][] getGalaxyMap() {
        return galaxyMap;
    }

    /**
     * @return the Planet Name pairing Listing
     */
    public Map<String, Planet> getPlanetNameMap() {
        return planetNameMap;
    }

    /**
     * @return the list of solar systems in the galaxy
     */
    public List<SolarSystem> getSolarSystemList() {
        return solarSystemList;
    }

    /**
     * @return the current planet
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    /**
     * @return all the planets in the galaxy
     */
    public List<Planet> getPlanetList() {
        return planetList;
    }


    /**
     * @return the map size
     */
    public RelativePosition getMapSize() {
        return mapSize;
    }

    /**
     * @param currentPlanet the planet that will be the current
     */
    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    /**
     * @return all the wormhole pairings
     */
    public List<Wormhole[]> getWormholePairList() {
        return wormholePairList;
    }
}
