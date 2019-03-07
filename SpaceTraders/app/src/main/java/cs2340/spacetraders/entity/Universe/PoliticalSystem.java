package cs2340.spacetraders.entity.Universe;

public enum PoliticalSystem {
    Anarchy,
    Capitalist_State,
    Communist_State,
    Confederacy,
    Corporate_State,
    Cybernetic_State,
    Democracy,
    Dictatorship,
    Fascist_State ,
    Feudal_State,
    Military_State,
    Monarchy,
    Pacifist_State,
    Socialist_State,
    State_Of_Satori,
    Technocracy,
    Theocracy;

    private String pirateQuantiy;
    private String policeQuantity;
    private String tradersQuantity;
    private String policeBriberyAcceptence;
    private String policeSmugglingAcceptence;
//    private Good mainDesiredGood

    PoliticalSystem() {
    }


    PoliticalSystem(String pirateQuantiy, String policeQuantity, String tradersQuantity, String policeBriberyAcceptence,
                    String policeSmugglingAcceptence) {
        this.pirateQuantiy = pirateQuantiy;
        this.policeQuantity = policeQuantity;
        this.tradersQuantity = tradersQuantity;
        this.policeBriberyAcceptence = policeBriberyAcceptence;
        this.policeSmugglingAcceptence = policeSmugglingAcceptence;

    }

    public String getPirateQuantiy() {
        return pirateQuantiy;
    }

    public String getPoliceQuantity() {
        return policeQuantity;
    }

    public String getTradersQuantity() {
        return tradersQuantity;
    }

    public String getPoliceBriberyAcceptence() {
        return policeBriberyAcceptence;
    }

    public String getPoliceSmugglingAcceptence() {
        return policeSmugglingAcceptence;
    }
}
