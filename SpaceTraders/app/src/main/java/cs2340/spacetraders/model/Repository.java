package cs2340.spacetraders.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;

class Repository {

    /***
     * This provides a mechanism to generate simple unique numbers to be used as
     * keys in the application
     */
    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /***************************************************************/

    private List<Player> allPlayers;

    /**
     * Make a new Repository object
     */
    public Repository() {
        allPlayers = new ArrayList<>();
    }

    public List<Player> getAllPlayers() { return allPlayers;}

    public void addPlayer(Player player) {
        //player.setId(Repository.getNextUniqueID());
        allPlayers.add(player);
    }


    public void deletePlayer(Player player) {
        allPlayers.remove(player);
    }

    public void updatePlayer(Player p) {
        /*
        for (Player player: allPlayers) {
            if (player.getId() == p.getId()) {
                return;
            }
        }
        */
    }
}

