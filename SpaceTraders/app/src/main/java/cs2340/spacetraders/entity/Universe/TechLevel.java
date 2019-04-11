package cs2340.spacetraders.entity.Universe;

/**
 * A Enumeration of the possible tech levels of a planet
 */
public enum TechLevel {
    PreAgriculture("Pre-Agriculture"),
    Agriculture("Agriculture"),
    Medieval("Medieval"),
    Renaissance("Renaissance"),
    EarlyIndustrial("Early Industrial"),
    Industrial("Industrial"),
    PostIndustrial("Post-Industrial"),
    HiTech("Hi-Tech");

    private final String techLevel;

    /**
     * @param techLevel the string representation of the tech level
     */
    TechLevel(String techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * @return a string representation of the tech level
     */
    public String getTechLevel() {
        return techLevel;
    }
}
