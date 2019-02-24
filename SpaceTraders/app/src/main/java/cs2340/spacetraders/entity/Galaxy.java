package cs2340.spacetraders.entity;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Galaxy {
    private Map<CelestialName, Planet> wholePlanetList;
    private Set<RelativePosition> systemPositionList;
    private Set<CelestialName> usedCelestialNames;
    private RelativePosition mapSize;
    private Random rand = new Random();

    public Galaxy() {
        wholePlanetList = new HashMap<CelestialName, Planet>();
        systemPositionList = new HashSet<RelativePosition>();
        usedCelestialNames = new HashSet<CelestialName>();
        mapSize = new RelativePosition(1000, 1000);

        makeSolarSystem();
    }

    private void makeSolarSystem() {
        int planetNum = rand.nextInt(5) + 1;
        RelativePosition center = getValidSystemPoint(planetNum);
        CelestialName systemName = getNonRepeatedCelestialName();
        String size = getSystemSize(planetNum);

        Log.d("Planet", "-----System " + systemName.getName() + " created at " + center + " with " + planetNum + " planets----");
        SolarSystem solarSystem = new SolarSystem(systemName, center, planetNum, size,this);
    }

    private RelativePosition getValidSystemPoint(int planetNum) {
        int toleranceSpaceBetweenPlanets = 2;
        RelativePosition center;
        do {
            center = new RelativePosition(rand.nextInt(mapSize.getX()), rand.nextInt(mapSize.getY()),
                    planetNum + toleranceSpaceBetweenPlanets, true);
        } while (systemPositionList.contains(center));
        systemPositionList.add(center);
        return center;
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


}
