package cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.model.PlayerInteractor;

public class ConfigurationViewModel extends AndroidViewModel {

    private PlayerInteractor model;
    private List<Player> players;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance().getPlayerInteractor();
        players = model.getAllPlayers();
    }

    public void addPlayer(String name, int engineerStat, int fighterStat, int traderStat, int pilotStat) {
        Player player = new Player(name, engineerStat, fighterStat, traderStat, pilotStat);
        model.addPlayer(player);
        players = model.getAllPlayers();
    }

    public void deletePlayer(Player player) {
        model.deletePlayer(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
