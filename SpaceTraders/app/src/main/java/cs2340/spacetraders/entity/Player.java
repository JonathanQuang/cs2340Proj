package cs2340.spacetraders.entity;

public class Player {

    private final int  initialCredits = 1000;

    private String name;
    private int engineerStat;
    private int fighterStat;
    private int traderStat;
    private int pilotStat;
    private int credits;
    private Ship ship;

    public Player(String name, int engineerStat, int fighterStat, int traderStat, int pilotStat) {
        this.name = name;
        this.engineerStat = engineerStat;
        this.fighterStat = fighterStat;
        this.traderStat = traderStat;
        this.pilotStat = pilotStat;
        this.credits = initialCredits;
        this.ship = new Ship();
    }

    public String toString() {
        String retStr = "";
        retStr += "Player name: " + name;
        retStr += ", Engineer stat: " + engineerStat;
        retStr += ", Fighter stat: " + fighterStat;
        retStr += ", Trader stat: " + traderStat;
        retStr += ", Pilot stat: " + pilotStat;
        retStr += ", Number of Credits: " + credits;
        retStr += ", Ship Info: " + ship;
        return retStr;
    }


}
