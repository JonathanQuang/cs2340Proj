package cs2340.spacetraders.entity.Travel;

import java.util.Random;

import cs2340.spacetraders.entity.Game;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;

public abstract class Encounterable {

    private Random random = new Random();
    private double difficultyMultiplier = Game.getDifficulty().getMultipler();
    private double fleeChance = 0.25;
    private double pursueChance = 0.5;
    private double surrenderChance = 0.75;
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

    /**
     *
     * @return
     */
    public double getSurrenderChance() {
        return surrenderChance;
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

    }

}
