package cs2340.spacetraders.entity;


public class Planet {
    private CelestialName name;
    private TechLevel techLevel;
    private Resources resources;
    private PoliticalSystem politicalSystem;
    private Position position;
    private String size;
    private String policeQuantity;
    private String pirateQuantit;
    private boolean isSpacePort;

    public Planet(CelestialName name, TechLevel techLevel, Resources resources,
                  PoliticalSystem politicalSystem, Position position, String size) {

        this.name = name;
        this.techLevel = techLevel;
        this.resources = resources;
        this.politicalSystem = politicalSystem;
        this.position = position;
        this.size = size;
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

    public Position getPosition() {
        return position;
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
