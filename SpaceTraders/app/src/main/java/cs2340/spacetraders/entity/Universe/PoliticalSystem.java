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

    private String pirateQuantity;
    private String policeQuantity;
    private String tradersQuantity;
    private String policeBriberyAcceptance;
    private String policeSmugglingAcceptance;

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
     * @param quantity the string representation of the amount of a character
     * @return the statistical proportion that character will be found
     */
    public static double determineProbability(String quantity) {
        if (quantity.equals("None")) {
            return 0;
        } else if (quantity.equals("Low")) {
            return 0.25;
        } else if (quantity.equals("Some")) {
            return 0.5;
        } else if (quantity.equals("High")) {
            return 0.75;
        } else {
            return 1;
        }
    }
}
