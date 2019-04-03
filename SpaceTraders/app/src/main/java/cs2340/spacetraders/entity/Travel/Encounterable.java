package cs2340.spacetraders.entity.Travel;

import java.util.Random;

import cs2340.spacetraders.entity.Game;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;

public abstract class Encounterable {

    private Random random = new Random();
    private double difficultyMultiplier = Game.getDifficulty().getMultipler();
    private double fleeChance = 0.05;
    private double pursueChance = 0.1;
    private double ignoreChance, attackChance;
    private Ship ship = new Ship(ShipType.randomShipType());

    /**
     *
     * @return
     */
    public Ship getShip(){
        return ship;
    }

    /**
     *
     * @return
     */
    public abstract String createDialogue();

    /**
     *
     * @return
     */
    public abstract void surrenderResult();

    /**
     *
     * @return
     */
    public abstract boolean setHostile();

    /**
     *
     * @return
     */
    public abstract String uniqueAction();

    /**
     *
     * @return
     */
    public void attack(double damage){
        Player.takeDamage(damage * difficultyMultiplier);
    }

    public void takeDamage(double damage){
        ship.takeDamage(damage / difficultyMultiplier);
    }

    public double getRandom() {
        return random.nextDouble();
    }

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

    public double getIgnoreChance() {
        return ignoreChance;
    }

    public void setIgnoreChance(double ignoreChance) {
        this.ignoreChance = ignoreChance;
    }

    public double getAttackChance() {
        return attackChance;
    }

    public void setAttackChance(double attackChance) {
        this.attackChance = attackChance;
    }

    public void characterDestruction() {
        Player.changeCredits((int) (500 * difficultyMultiplier));
    }

}
