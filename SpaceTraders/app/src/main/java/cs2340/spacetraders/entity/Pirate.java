package cs2340.spacetraders.entity;

public class Pirate extends Encounterable {

    /**
     *
     */
    public Pirate() {
        super();
    }

    /**
     *
     */
    public void steal() {

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
        return "Arrrr";
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
