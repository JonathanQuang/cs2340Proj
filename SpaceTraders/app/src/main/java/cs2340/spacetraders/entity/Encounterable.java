package cs2340.spacetraders.entity;

public abstract class Encounterable {

    private double fleeChance, pursueChance, surrenderChance;

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
