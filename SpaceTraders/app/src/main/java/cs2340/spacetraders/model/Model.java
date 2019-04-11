package cs2340.spacetraders.model;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Difficulty;
import cs2340.spacetraders.entity.Game;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Resources;

/**
 * Base model for the entire game
 */
public class Model implements Serializable {

    private Player player;
    private Game game;
    private Map<Resources, Integer> planetImageIDs;

    private static  Model instance = new Model();

    /**
     * Getter for the instance of the model
     * @return instance
     */
    public static Model getInstance() { return instance; }

    private Model() {
        planetImageIDs = new HashMap<>();
        setPlanetImageIDs();
    }

    /** Creates Player
     * @param name the name of the player
     * @param engineerStat the engineer stats of the player
     * @param fighterStat the fighter stats of the player
     * @param traderStat the trader stats of the player
     * @param pilotStat the pilot stats of the player
     */
    public void createPlayer(String name, int engineerStat, int fighterStat,
                             int traderStat, int pilotStat) {
        player = new Player(name, engineerStat,fighterStat, traderStat, pilotStat);
        Log.d("Player", player.toString());
    }

    /** Creates Game
     * @param difficulty the difficult of the game
     */
    public void createGame(Difficulty difficulty) {
        game = new Game(difficulty);
    }

    /** Gets player instance
     * @return player instance
     */
    public Player getPlayer() {
        return player;
    }

    /** gets game instance
     * @return game instance
     */
    public Game getGame() {
        return game;
    }

    private void setPlanetImageIDs() {
        int[] planetPNGs = new int[] {R.drawable.normie_l, R.drawable.mineral_l,
                                R.drawable.poor_soil_l,
                                R.drawable.desert_l, R.drawable.water_l, R.drawable.rich_soil_l,
                                R.drawable.poor_soil_l, R.drawable.fauna_l, R.drawable.lifeless_l,
                                R.drawable.mushrooms_l, R.drawable.herbs_l, R.drawable.artistic_l,
                                R.drawable.war_l
        };
        for (Resources resource: Resources.values()) {
            planetImageIDs.put(resource, planetPNGs[resource.ordinal()]);
        }
    }

    /**
     * Getter for the planet images
     * @return list of planet images
     */
    public Map<Resources, Integer> getPlanetImageIDs() {
        return planetImageIDs;
    }

    /**
     * Setter for the game
     * @param game current instance of the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Setter for the current player
     * @param player current player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
