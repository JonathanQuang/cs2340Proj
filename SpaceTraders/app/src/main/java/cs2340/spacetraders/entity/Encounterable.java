package cs2340.spacetraders.entity;

public abstract class Encounterable {

    private double fleeChance = 0.25;
    private double pursueChance = 0.5;
    private double surrenderChance = 0.75;

    /**
     *
     * @return
     */
    public abstract Ship getShip();

    /**
     *
     * @return
     */
    public abstract String createDialogue();

    /**
     *
     * @return
     */
    public abstract int attack();

    /**
     *
     * @return
     */
    public double getFleeChance() {
        return fleeChance;
    }

    /**
     *
     * @return
     */
    public double getPursueChance() {
        return pursueChance;
    }

    /**
     *
     * @return
     */
    public double getSurrenderChance() {
        return surrenderChance;
    }
}
