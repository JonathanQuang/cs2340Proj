package cs2340.spacetraders.entity;

public class Police extends Encounterable {

    private double bribeChance, searchChance, ignoreChance, attackChance;

    /**
     *
     */
    public Police() {
        super();
    }

    /**
     *
     * @return
     */
    public boolean bribe() {
        return false;
    }

    /**
     *
     * @return
     */
    public boolean checkCargo() {
        return false;
    }

    /**
     *
     */
    public void confiscate() {

    }

    /**
     *
     * @return
     */
    @Override
    public Ship getShip() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String createDialogue() {
        return "This is the police";
    }

    /**
     *
     * @return
     */
    @Override
    public int attack() {
        return 0;
    }
}
