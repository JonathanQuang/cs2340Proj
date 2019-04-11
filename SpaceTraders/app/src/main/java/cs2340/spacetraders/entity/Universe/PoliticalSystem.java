package cs2340.spacetraders.entity.Universe;

/**
 * A Enumeration of the possible political systems of a planet
 */
public enum PoliticalSystem {
    Anarchy("High", "None", "Low", "None", "None"),
    Capitalist_State("Low", "Some", "High", "None", "High"),
    Communist_State("Low", "High", "Some", "High", "Low"),
    Confederacy("Some", "High", "High", "None", "None"),
    Corporate_State("Low", "High", "High", "Low", "Some"),
    Cybernetic_State("High", "High", "High", "None", "High"),
    Democracy("Low", "Low", "High", "Some", "Some"),
    Dictatorship("High", "High", "High", "High", "High"),
    Fascist_State("Low", "High", "Low", "None", "None"),
    Feudal_State("High", "None", "Low", "None", "None"),
    Military_State("None", "High", "High", "None", "None"),
    Monarchy("Some", "High", "High", "None", "None"),
    Pacifist_State("Low", "Low", "Some", "None", "None"),
    Socialist_State("High", "Low", "Low", "High", "None"),
    State_Of_Satori("Low", "Low", "Low", "None", "None"),
    Technocracy("Low", "High", "High", "High", "High"),
    Theocracy("Low", "High", "Some", "None", "Some");

    private final String pirateQuantity;
    private final String policeQuantity;
    private final String tradersQuantity;
    private final String policeBriberyAcceptance;
    private final String policeSmugglingAcceptance;

    /**
     * @param pirateQuantity the pirate amount
     * @param policeQuantity the police amount
     * @param tradersQuantity the trader amount
     * @param policeBriberyAcceptance the chance a policeman will accept a bride
     * @param policeSmugglingAcceptance the chance a policeman will look for smuggled goods
     */
    PoliticalSystem(String pirateQuantity, String policeQuantity,
                    String tradersQuantity, String policeBriberyAcceptance,
                    String policeSmugglingAcceptance) {
        this.pirateQuantity = pirateQuantity;
        this.policeQuantity = policeQuantity;
        this.tradersQuantity = tradersQuantity;
        this.policeBriberyAcceptance = policeBriberyAcceptance;
        this.policeSmugglingAcceptance = policeSmugglingAcceptance;

    }


    /**
     * @return the pirate amount
     */
    public String getPirateQuantity() {
        return pirateQuantity;
    }

    /**
     * @return the police amount
     */
    public String getPoliceQuantity() {
        return policeQuantity;
    }

    /**
     * @return the trader amount
     */
    public String getTradersQuantity() {
        return tradersQuantity;
    }

    /**
     * @return the chance a policeman will accept a bride
     */
    public String getPoliceBriberyAcceptance() {
        return policeBriberyAcceptance;
    }

    /**
     * @return the chance a policeman will look for smuggled goods
     */
    public String getPoliceSmugglingAcceptance() {
        return policeSmugglingAcceptance;
    }

    /**
     * Sets numeric probabilities for the String representations
     * @param quantity given String
     * @return double for probability
     */
    public double determineProbability(String quantity) {
        if ("None".equals(quantity)) {
            return 0;
        } else if ("Low".equals(quantity)) {
            return 0.25;
        } else if ("Some".equals(quantity)) {
            return 0.5;
        } else if ("High".equals(quantity)) {
            return 0.75;
        } else {
            return 1;
        }
    }
}
