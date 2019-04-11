package cs2340.spacetraders.entity.Travel;

import java.util.Random;

import cs2340.spacetraders.entity.Difficulty;
import cs2340.spacetraders.entity.Game;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.model.Model;

/**
 * Abstract class for encounterable characters while traveling to planets
 */
public abstract class Encounterable {

    private Random random = new Random();
    private Model model = Model.getInstance();
    private Game game = model.getGame();
    private Difficulty difficulty = game.getDifficulty();
    private double difficultyMultiplier = difficulty.getMultipler();
    private double ignoreChance;
    private double attackChance;
    private final ShipType type = ShipType.Gnat;
    private Ship ship = new Ship(type.randomShipType());
    private Player player = model.getPlayer();

    /**
     * Returns the character's ship
     * @return ship
     */
    public Ship getShip(){
        return ship;
    }

    /**
     * Returns what the character says upon encounter
     * @return Resulting dialogue
     */
    public abstract String createDialogue();

    /**
     * Action the character does after the player surrenders
     */
    public abstract void surrenderResult();

    /**
     * Changes the character's hostile status and action probabilities
     * @return hostile status
     */
    public abstract boolean setHostile();

    /**
     * Performs the character's unique action such as trading
     * @return the unique action
     */
    public abstract String uniqueAction();

    /**
     * Character attacks player, player takes damage
     * @param damage Character ship's attack
     */
    public void attack(double damage){
        player.takeDamage(damage * difficultyMultiplier);
    }

    /**
     * Player attacks character, character takes damage
     * @param damage Player ship's attack
     */
    public void takeDamage(double damage){
        ship.takeDamage(damage / difficultyMultiplier);
    }

    /**
     * Gets a random double used later for probabilities
     * @return random double
     */
    public double getRandom() {
        return random.nextDouble();
    }

    /**
     * Getter for fleeChance
     * @return fleeChance
     */
    public double getFleeChance() {
        double fleeChance = 0.05;
        return fleeChance;
    }

    /**
     * Getter for pursueChance
     * @return pursueChance
     */
    public double getPursueChance() {
        double pursueChance = 0.1;
        return pursueChance;
    }

    /**
     * Getter for ignoreChance
     * @return ignoreChance
     */
    public double getIgnoreChance() {
        return ignoreChance;
    }

    /**
     * Setter for ignoreChance
     * @param ignoreChance chance character ignores player
     */
    public void setIgnoreChance(double ignoreChance) {
        this.ignoreChance = ignoreChance;
    }

    /**
     * Getter for attackChance
     * @return attackChance
     */
    public double getAttackChance() {
        return attackChance;
    }

    /**
     * Setter for attackChance
     * @param attackChance chance character attacks the player
     */
    public void setAttackChance(double attackChance) {
        this.attackChance = attackChance;
    }

    /**
     * Upon killing a character, reward the player
     */
    public void characterDestruction() {
        player.changeCredits((int) (500 * difficultyMultiplier));
    }

    /**
     * Getter for the current player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

}
