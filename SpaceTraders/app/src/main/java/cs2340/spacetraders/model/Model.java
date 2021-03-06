package cs2340.spacetraders.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import cs2340.spacetraders.entity.Difficulty;
import cs2340.spacetraders.entity.Game;
import cs2340.spacetraders.entity.Player;

public class Model {

    private Player player;
    private Game game;

    private static  Model instance = new Model();
    public static Model getInstance() { return instance; }

    private Model() {}

    /** Creates Player
     * @param name the name of the player
     * @param engineerStat the engineer stats of the player
     * @param fighterStat the fighter stats of the player
     * @param traderStat the trader stats of the player
     * @param pilotStat the pilot stats of the player
     */
    public void createPlayer(String name, int engineerStat, int fighterStat, int traderStat, int pilotStat) {
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
}
