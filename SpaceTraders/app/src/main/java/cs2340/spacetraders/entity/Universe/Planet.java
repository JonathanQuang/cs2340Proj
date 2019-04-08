package cs2340.spacetraders.entity.Universe;

import java.io.Serializable;

import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;

public class Planet implements Serializable {
    private String name;
    private TechLevel techLevel;
    private Resources resources;
    private PoliticalSystem politicalSystem;
    private RelativePosition relativePosition;
    private String size;
    private String policeQuantity;
    private String pirateQuantit;
    private boolean isSpacePort;
    private PlanetInventory inventory;
    private PlanetaryEvent event;
    private double tradersReturnRate;
    private SolarSystem parentSolarSystem;
    private Wormhole connectWormHole;

    public Planet(CelestialName name, TechLevel techLevel, Resources resources,
                  PoliticalSystem politicalSystem, RelativePosition relativePosition,
                  String size, SolarSystem parentSolarSystem) {
        this.name = name.getName();
        this.techLevel = techLevel;
        this.resources = resources;
        this.politicalSystem = politicalSystem;
        this.relativePosition = relativePosition;
        this.size = size;
        this.parentSolarSystem = parentSolarSystem;
        inventory = new PlanetInventory();

        event = PlanetaryEvent.Nothing;
        tradersReturnRate = .10;

        makePlanetInventory();
//        Log.d("Mark", "Inverntory Made");
    }

    public Planet(String name, RelativePosition relativePosition) {
        this.name = name;
        this.relativePosition = relativePosition;
    }

    public void makeSpaceport(Wormhole wormhole) {
        isSpacePort = true;
        connectWormHole = wormhole;
    }

    @Override
    public boolean equals(Object obj) {
        Planet that = (Planet) obj;
        return this.getName().equals(that.getName());
    }

    public String getName() {
        return name;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public Resources getResources() {
        return resources;
    }

    public PoliticalSystem getPoliticalSystem() {
        return politicalSystem;
    }

    public RelativePosition getRelativePosition() {
        return relativePosition;
    }

    public String getSize() {
        return size;
    }

    public String getPoliceQuantity() {
        return policeQuantity;
    }

    public String getPirateQuantit() {
        return pirateQuantit;
    }

    public boolean isSpacePort() {
        return isSpacePort;
    }

    public String toString() {
        return name + " is a " + size  + " planet at " + relativePosition + " with a " + techLevel
                + " " + politicalSystem + " possessing a " + resources + " environment";
    }

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

    public PlanetInventory getInventory() {
        return inventory;
    }


    public SolarSystem getParentSolarSystem() {
        return parentSolarSystem;
    }

    public int getSizeAsInt() {
        if (size.equals("Small")) {return 0;}
        if (size.equals("Medium")) {return 1;}
        else {return 2;}
    }



    public double getPlanetDistance(Planet otherPlanet) {
        return  Math.sqrt(
                Math.pow(relativePosition.getX() - otherPlanet.relativePosition.getX(), 2) +
                Math.pow(relativePosition.getY() - otherPlanet.relativePosition.getY(), 2));
    }

}
