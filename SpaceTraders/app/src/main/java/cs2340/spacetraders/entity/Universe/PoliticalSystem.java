package cs2340.spacetraders.entity.Universe;

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

    public String getPirateQuantiy() {
        return pirateQuantity;
    }

    public String getPoliceQuantity() {
        return policeQuantity;
    }

    public String getTradersQuantity() {
        return tradersQuantity;
    }

    public String getPoliceBriberyAcceptence() {
        return policeBriberyAcceptance;
    }

    public String getPoliceSmugglingAcceptence() {
        return policeSmugglingAcceptance;
    }
}
