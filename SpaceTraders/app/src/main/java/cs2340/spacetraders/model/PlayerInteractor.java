package cs2340.spacetraders.model;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import cs2340.spacetraders.entity.Player;


/**
 * Provide operations associated with Courses
 *
 * Basically provides an interface between the UI (view model) and the Model classes
 */
public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);

    }

    public void addPlayer(Player player) {
        getRepository().addPlayer(player);
    }

    public List<Player> getAllPlayers() {
        return getRepository().getAllPlayers();
    }

    public void deletePlayer(Player player) {
        getRepository().deletePlayer(player);
    }
}

