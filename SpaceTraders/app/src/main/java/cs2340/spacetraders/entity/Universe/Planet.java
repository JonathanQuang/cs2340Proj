package cs2340.spacetraders.entity.Universe;


import android.util.Log;
import java.util.Random;
import java.io.Serializable;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;

/**
 * A Container for all the information about a planet such as its name, position
 * and other attributes
 */
public class Planet implements Serializable {
    private CelestialName name;
    private TechLevel techLevel;
    private Resources resources;
    private PoliticalSystem politicalSystem;
    private RelativePosition relativePosition;
    private String size;
    private String policeQuantity;
    private String traderQuantity;
    private String pirateQuantity;
    private String policeBriberyAcceptance;
    private String policeSmugglingAcceptance;
    private boolean isSpacePort;
    private PlanetInventory inventory;
    private PlanetaryEvent event;
    private double tradersReturnRate;
    private SolarSystem parentSolarSystem;
    private final Random RANDOM = new Random();
    private Wormhole connectWormHole;

    /**
     * @param name the name of the planet
     * @param techLevel the tech level of the planet
     * @param resources the resources of the planet
     * @param politicalSystem the political system of the planet
     * @param relativePosition the position of the planet
     * @param size the size of the planet
     * @param parentSolarSystem the solar system that the planet lies in
     */
    public Planet(CelestialName name, TechLevel techLevel, Resources resources,
                  PoliticalSystem politicalSystem, RelativePosition relativePosition,
                  String size, SolarSystem parentSolarSystem) {
        this.name = name;
        this.techLevel = techLevel;
        this.resources = resources;
        this.politicalSystem = politicalSystem;
        this.relativePosition = relativePosition;
        this.size = size;
        this.policeQuantity = politicalSystem.getPoliceQuantity();
        this.pirateQuantity = politicalSystem.getPirateQuantity();
        this.traderQuantity = politicalSystem.getTradersQuantity();
        this.policeBriberyAcceptance = politicalSystem.getPoliceBriberyAcceptance();
        this.policeSmugglingAcceptance = politicalSystem.getPoliceSmugglingAcceptance();
        this.parentSolarSystem = parentSolarSystem;
        inventory = new PlanetInventory();
        event = PlanetaryEvent.Nothing;

        if (RANDOM.nextDouble() > 0.8) {
            while ((event == null) || (event == PlanetaryEvent.Nothing)) {
                event = event.randomEvent();
            }
        }
        Log.d("Mark", "Random Event");
        tradersReturnRate = .10;

        makePlanetInventory();
        Log.d("Mark", "Inventory Made");
    }

    /**
     * @param wormhole the wormhole that use this planet as a spaceport
     */
    public void makeSpaceport(Wormhole wormhole) {
        isSpacePort = true;
        connectWormHole = wormhole;
    }


    @Override
    public boolean equals(Object obj) {
        Planet that = (Planet) obj;
        return this.getName().equals(that.getName());
    }

    /**
     * @return the name of the planet
     */
    public CelestialName getName() {
        return name;

    }

    /**
     * @return the tech level of the planet
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * @return the resources of the planet
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * @return the political system of the planet
     */
    public PoliticalSystem getPoliticalSystem() {
        return politicalSystem;
    }

    /**
     * @return the position of the planet
     */
    public RelativePosition getRelativePosition() {
        return relativePosition;
    }

    /**
     * @return the size of the planet
     */
    public String getSize() {
        return size;
    }

    /**
     * @return the planet's planetary event
     */
    public PlanetaryEvent getPlanetaryEvent() {
        return event;
    }

    /**
     * @return the police quantity of the planet
     */
    public String getPoliceQuantity() {
        return policeQuantity;
    }

    /**
     * @return the pirate quantity of the planet
     */
    public String getPirateQuantity() {
        return pirateQuantity;
    }


    /**
     * @return the trader quantity
     */
    public String getTraderQuantity() {
        return traderQuantity;
    }

    /**
     * @return the chance a police on this planet can be bribed
     */
    public String getPoliceBriberyAcceptance() {
        return policeBriberyAcceptance;
    }

    /**
     * @return the chance a police on this planet will check your ship
     */
    public String getPoliceSmugglingAcceptance() {
        return policeSmugglingAcceptance;
    }

    /**
     * @return if the planet is a spaceport
     */
    public boolean isSpacePort() {
        return isSpacePort;
    }

    /**
     * @return the string representation of the planet
     */
    @Override
    public String toString() {
        return name.getName() + " is a " + size  + " planet at " + relativePosition
                + " with a " + techLevel + " " + politicalSystem + " possessing a "
                + resources + " environment";
    }

    /**
     * Makes the planet's inventory
     */
    public void makePlanetInventory() {
        for (Good good: Good.values()) {
            boolean canBuy = good.canBuyFrom(techLevel);
            boolean canSell = good.canSellTo(techLevel);
            int buyPrice = good.calcBuyPrice(techLevel, resources, event);
            int count = good.makeGoodCount(techLevel);
            int sellPrice = (int) (buyPrice * (1 - tradersReturnRate));
            inventory.addToPlanetInventory(good, buyPrice, sellPrice, count, canBuy, canSell);
        }
    }

    /**
     * @return the planet's inventory
     */
    public PlanetInventory getInventory() {
        return inventory;
    }


    /**
     * @return the parent solar system of the planet
     */
    public SolarSystem getParentSolarSystem() {
        return parentSolarSystem;
    }

    /**
     * @return the size representation of the planet
     */
    public int getSizeAsInt() {
        if ("Small".equals(size)) {return 0;}
        if ("Medium".equals(size)) {return 1;}
        else {return 2;}
    }


    /**
     * @param otherPlanet the other planet being check for it distance
     * @return the distance between this and that planet
     */
    public double getPlanetDistance(Planet otherPlanet) {
        return  Math.sqrt(
                Math.pow(relativePosition.getX() - otherPlanet.relativePosition.getX(), 2) +
                Math.pow(relativePosition.getY() - otherPlanet.relativePosition.getY(), 2));
    }

}
