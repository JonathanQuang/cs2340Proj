package cs2340.spacetraders.entity.Universe;

/**
 * Enum for possible tech levels of a planet
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
//    private Good[] interestedGoods;
//    private Good[] disinterestedGoods;

    TechLevel(String techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * Getter for tech level
     * @return techLevel
     */
    public String getTechLevel() {
        return techLevel;
    }
}
