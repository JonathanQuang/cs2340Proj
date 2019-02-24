package cs2340.spacetraders.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Galaxy {
    private HashSet<Position> planetPositions;
    private HashMap<CelestialName, Planet> wholePlanetList;
    private int[][] availableArea;
    private Position mapSize;
    private Random rand = new Random();

    public Galaxy() {
        mapSize = new Position(300, 300);
        availableArea = new int[mapSize.getX()][mapSize.getY()];
        makeSolarSystem();
    }

    private void makeSolarSystem() {
        Position center;
        do {
            center = new Position(rand.nextInt(mapSize.getX()), rand.nextInt(mapSize.getY()));
        } while (validPosition(center));
        int planetNum = rand.nextInt(5) + 1;
        SolarSystem solarSystem = new SolarSystem(center, planetNum);
        blockArea(center, planetNum);
    }

    private boolean validPosition(Position point) {
        //Checkout Available Areas
        return true;
    }

    private void blockArea(Position point, int radius) {
        return;
    }
}
