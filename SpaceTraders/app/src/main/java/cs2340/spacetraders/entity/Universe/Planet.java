package cs2340.spacetraders.entity.Universe;

import android.util.Log;

import java.util.Random;

import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;

public class Planet {
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

        if (RANDOM.nextDouble() < 0.8) {
            event = PlanetaryEvent.Nothing;
        } else {
            while (event == null || event == PlanetaryEvent.Nothing) {
                event = PlanetaryEvent.randomEvent();
            }
        }
        Log.d("Mark", "Random Event");
        tradersReturnRate = .10;

        makePlanetInventory();
        Log.d("Mark", "Inverntory Made");
    }

    public void makeSpaceport() {
        isSpacePort = true;
    }

    public CelestialName getName() {
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

    public PlanetaryEvent getPlanetaryEvent() {
        return event;
    }

    public String getPoliceQuantity() {
        return policeQuantity;
    }

    public String getPirateQuantity() {
        return pirateQuantity;
    }

    public String getTraderQuantity() {
        return traderQuantity;
    }

    public String getPoliceBriberyAcceptance() {
        return policeBriberyAcceptance;
    }

    public String getPoliceSmugglingAcceptance() {
        return policeSmugglingAcceptance;
    }

    public boolean isSpacePort() {
        return isSpacePort;
    }

    public String toString() {
        return name.getName() + " is a " + size  + " planet at " + relativePosition + " with a " + techLevel
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
