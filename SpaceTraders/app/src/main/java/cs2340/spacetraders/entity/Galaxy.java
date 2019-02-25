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
    private Map<CelestialName, Planet> wholePlanetList;
    private List<RelativePosition> systemPositionList;
    private Set<CelestialName> usedCelestialNames;
    private RelativePosition mapSize;
    private String[][] galaxyMap;
    private Random rand = new Random();

    public Galaxy() {
        wholePlanetList = new HashMap<CelestialName, Planet>();
        systemPositionList = new ArrayList<RelativePosition>();
        usedCelestialNames = new HashSet<CelestialName>();
        mapSize = new RelativePosition(40, 40);
        galaxyMap = new String[mapSize.getX()][mapSize.getY()];
        for (String[] row: galaxyMap)
            Arrays.fill(row, " ");

        while (usedCelestialNames.size() + 6 < CelestialName.values().length){
            makeSolarSystem();
        }
        printMap();
    }

    private void makeSolarSystem() {
        int planetNum = rand.nextInt(5) + 1;
        CelestialName systemName = getNonRepeatedCelestialName();
        Log.d("Planet", "Getting new system's center position");
        RelativePosition center = getValidSystemPoint(planetNum);
        String size = getSystemSize(planetNum);

        Log.d("Planet", "-----System " + systemName.getName() + " created at " + center + " with " + planetNum + " planets----");
        placeSystemOnMap(center);
        SolarSystem solarSystem = new SolarSystem(systemName, center, planetNum, size,this);
    }

    private RelativePosition getValidSystemPoint(int planetNum) {
        int toleranceSpaceBetweenPlanets = 0;
        RelativePosition center;
        int x, y, rectRadius;

        do {
            x = rand.nextInt(mapSize.getX());
            y = rand.nextInt(mapSize.getY());
            rectRadius = (planetNum + 2)/3;
            center = new RelativePosition(x, y, rectRadius, true);
        } while (systemPositionList.contains(center) || !isValidCornerPoint(center));
        systemPositionList.add(center);
        return center;
    }

    private boolean isValidCornerPoint(RelativePosition point) {
        int x = point.getX();
        int y = point.getY();
        int r = point.getRectRadius();
        return 0 <= x - r && x + r < mapSize.getX() && 0 <= y - r && y + r < mapSize.getY();
    }

    public CelestialName getNonRepeatedCelestialName() {
        CelestialName celestialName;
        do {
            celestialName = CelestialName.values()[rand.nextInt(CelestialName.values().length)];
            Log.d("okay", celestialName.getName());
        } while (usedCelestialNames.contains(celestialName));
        usedCelestialNames.add(celestialName);
        return celestialName;
    }

    private String getSystemSize(int planetNum) {
        String sizeStr = "";
        if (planetNum <= 1) {sizeStr = "Small"; }
        else if (planetNum <= 3) { sizeStr = "Moderate"; }
        else if (planetNum <= 5) { sizeStr = "Large"; }
        return sizeStr;
    }

    private void placeSystemOnMap(RelativePosition position) {
        int rectRadius = position.getRectRadius();
        int x = position.getX();
        int y = position.getY();

        for (int i = -rectRadius; i <= rectRadius; galaxyMap[x + i][y + rectRadius] = "#", i++) ;
        for (int i = -rectRadius; i <= rectRadius; galaxyMap[x + i][y - rectRadius] = "#", i++) ;
        for (int i = -rectRadius; i <= rectRadius; galaxyMap[x + rectRadius][y + i] = "#", i++) ;
        for (int i = -rectRadius; i <= rectRadius; galaxyMap[x - rectRadius][y + i] = "#", i++) ;

        galaxyMap[x][y] = "O";
    }

    private void printMap() {
        Log.d("Planet", "-----MAP-----");
        for (int i = 0; i < galaxyMap.length; i++) {
            Log.d("Planet", Arrays.toString(galaxyMap[i]));
        }
    }

    public String[][] getGalaxyMap() {
        return galaxyMap;
    }

    public void setGalaxyMap(String[][] galaxyMap) {
        this.galaxyMap = galaxyMap;
    }
}
