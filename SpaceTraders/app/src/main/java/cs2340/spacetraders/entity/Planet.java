package cs2340.spacetraders.entity;


import android.util.Log;

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

    public Planet(CelestialName name, TechLevel techLevel, Resources resources,
                  PoliticalSystem politicalSystem, RelativePosition relativePosition, String size) {
        this.name = name;
        this.techLevel = techLevel;
        this.resources = resources;
        this.politicalSystem = politicalSystem;
        this.relativePosition = relativePosition;
        this.size = size;
        Log.d("Planet ", name.getName() + ", " + techLevel + ", " + resources + ", " + politicalSystem
            + ", " + relativePosition + ", " + size);
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
}
