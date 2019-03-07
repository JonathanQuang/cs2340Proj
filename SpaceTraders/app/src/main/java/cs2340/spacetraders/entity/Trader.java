package cs2340.spacetraders.entity;

public class Trader extends Encounterable {

    /**
     *
     */
    public Trader() {
        super();
    }

    /**
     *
     */
    public void trade() {

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
        return "I am a trader";
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
