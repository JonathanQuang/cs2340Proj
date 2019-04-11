package cs2340.spacetraders.entity.Universe;

import java.io.Serializable;
import java.util.List;

import cs2340.spacetraders.model.Model;

public class Wormhole implements Serializable {
    private Galaxy galaxy;
    private Wormhole connectedWormhole;
    private RelativePosition position;
    private Planet shipportPlanet;


    /**
     * Constructor for wormhole given Relative Position Object
     *
     * @param position RelativePosition object to represent where the wormhole is
     */
    public Wormhole(Galaxy galaxy, RelativePosition position) {
        this.galaxy = galaxy;
        this.position = position;
        findSpacePort();
    }

    private void findSpacePort() {
        List<Planet> planetList = galaxy.getPlanetList();
        Planet closestPlanet = planetList.get(0);
        double min = Integer.MAX_VALUE;

        for (Planet otherPlanet: planetList) {
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
     * A's womrhole pointer points to B, and B's pointer will point to A
     *
     * @param otherWormHole the second wormhole to join the two wormholes
     */
    public void joinWormholes(Wormhole otherWormHole) {
        this.connectedWormhole = otherWormHole;
        otherWormHole.connectedWormhole = this;
    }

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
        return this.position.getY() == targetHole.position.getY() && this.position.getX() == targetHole.position.getX();
    }

    @Override
    public int hashCode() {
        return 3 * position.getY() + 7 * position.getX();
    }

    public String toString(){
        String retStr = "this wormhole is located at " + this.position;
        if (connectedWormhole == null) {
            return retStr + " but not connected to another wormhole";
        }
        return retStr + " connected to a wormhole located at " + connectedWormhole.position;
    }

    public Planet getShipportPlanet() {
        return shipportPlanet;
    }

    public Wormhole getConnectedWormhole() {
        return connectedWormhole;
    }
}
