package cs2340.spacetraders.entity.Universe;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * A method of transportation within the galaxy connect to spaceports
 */

public class Wormhole implements Serializable {
    private final List<Planet> galaxy;
    private Wormhole connectedWormhole;
    private final RelativePosition position;
    private Planet shipportPlanet;


    /**
     * Constructor for wormhole given Relative Position Object
     *
     * @param position RelativePosition object to represent where the wormhole is
     * @param galaxy the parent galaxy
     */
    public Wormhole(List<Planet> galaxy, RelativePosition position) {
        this.galaxy = galaxy;
        this.position = position;
        findSpacePort();
    }

    /**
     * Constructor for wormhole given Galaxy object
     * @param galaxy the parent galaxy
     * @param position RelativePosition object to represent where the wormhole is
     */
    public Wormhole(Galaxy galaxy, RelativePosition position) {
        this(galaxy.getPlanetList(), position);
    }

    private void findSpacePort() {
        Planet closestPlanet = galaxy.get(0);
        double min = Integer.MAX_VALUE;

        for (Planet otherPlanet: galaxy) {
            double dist = getDistance(otherPlanet);
            if (dist < min && !otherPlanet.isSpacePort()) {
                min = dist;
                closestPlanet = otherPlanet;
            }
        }

        closestPlanet.makeSpaceport(this);
        shipportPlanet = closestPlanet;
    }

    private double getDistance(Planet otherPlanet) {
        return  Math.sqrt(
                Math.pow(position.getX() - otherPlanet.getRelativePosition().getX(), 2)
                + Math.pow(position.getY() - otherPlanet.getRelativePosition().getY(), 2));
    }

    /**
     * Given wormhole A and B
     * A's wormhole pointer points to B, and B's pointer will point to A
     *
     * @param otherWormHole the second wormhole to join the two wormholes
     */
    public void joinWormholes(Wormhole otherWormHole) {
        this.connectedWormhole = otherWormHole;
        otherWormHole.connectedWormhole = this;
    }

    /**
     * @return the wormhole position
     */
    public RelativePosition getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Wormhole)) {
            return false;
        }
        Wormhole targetHole = (Wormhole) o;
        return this.position.getY() == targetHole.position.getY()
                && this.position.getX() == targetHole.position.getX();
    }

    @Override
    public int hashCode() {
        return 3 * position.getY() + 7 * position.getX();
    }

    @NonNull
    @Override
    public String toString(){
        String retStr = "this wormhole is located at " + this.position;
        if (connectedWormhole == null) {
            return retStr + " but not connected to another wormhole";
        }
        return retStr + " connected to a wormhole located at " + connectedWormhole.position;
    }

    /**
     * @return the planet that is this wormhole's spaceport
     */
    public Planet getShipportPlanet() {
        return shipportPlanet;
    }

    /**
     * @return the wormhole connected to this one
     */
    public Wormhole getConnectedWormhole() {
        return connectedWormhole;
    }
}
