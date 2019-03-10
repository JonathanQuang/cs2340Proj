package cs2340.spacetraders.entity.Universe;

import android.util.Log;

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
    private String pirateQuantit;
    private boolean isSpacePort;
    private PlanetInventory inventory;
    private PlanetaryEvent event;
    private double tradersReturnRate;
    private SolarSystem parentSolarSystem;

    public Planet(CelestialName name, TechLevel techLevel, Resources resources,
                  PoliticalSystem politicalSystem, RelativePosition relativePosition,
                  String size, SolarSystem parentSolarSystem) {
        this.name = name;
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
//        Log.d("Market", techLevel.toString() + "-> " + inventory.toString());
    }

    public PlanetInventory getInventory() {
        return inventory;
    }
}
