package cs2340.spacetraders.entity.Universe;

/**
 * Enum for the types of political systems a planet may have
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
//    private Good mainDesiredGood

    PoliticalSystem() {
    }


    PoliticalSystem(String pirateQuantity, String policeQuantity, String tradersQuantity, String policeBriberyAcceptance,
                    String policeSmugglingAcceptance) {
        this.pirateQuantity = pirateQuantity;
        this.policeQuantity = policeQuantity;
        this.tradersQuantity = tradersQuantity;
        this.policeBriberyAcceptance = policeBriberyAcceptance;
        this.policeSmugglingAcceptance = policeSmugglingAcceptance;

    }

    /**
     * Getter for the amount of pirates
     * @return pirateQuantity
     */
    public String getPirateQuantity() {
        return pirateQuantity;
    }

    /**
     * Getter for the amount of police
     * @return policeQuantity
     */
    public String getPoliceQuantity() {
        return policeQuantity;
    }

    /**
     * Getter for the amount of traders
     * @return tradersQuantity
     */
    public String getTradersQuantity() {
        return tradersQuantity;
    }

    /**
     * Getter for the chance of police accepting bribes
     * @return policeBriberyAcceptance
     */
    public String getPoliceBriberyAcceptance() {
        return policeBriberyAcceptance;
    }

    /**
     * Getter for the chance of police allowing illegal goods
     * @return policeSmugglingAcceptance
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
