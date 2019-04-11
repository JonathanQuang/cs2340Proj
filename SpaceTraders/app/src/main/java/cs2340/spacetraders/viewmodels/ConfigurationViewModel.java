package cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import cs2340.spacetraders.entity.Difficulty;
import cs2340.spacetraders.model.Model;

/**
 * Model for Configuration player screen
 */
public class ConfigurationViewModel extends AndroidViewModel {

    public final int STARTING_POINTS = 16; //16

    /**
     * Default Constructor
     * @param application the current application
     */
    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Checks to see if player inputs are valid
     * @param name the name of the player
     * @param engineerStat the engineer stats of the player
     * @param fighterStat the fighter stats of the player
     * @param traderStat the trader stats of the player
     * @param pilotStat the pilot stats of the player
     * @param difficulty the difficulty of the game
     * @param pointsLeft the points left to assign
     * @return if player was properly created
     */
    public boolean onOkay(String name, int engineerStat, int fighterStat, int traderStat,
                          int pilotStat, Difficulty difficulty, int pointsLeft) {
//        if (name.length() == 0) {
//            Toast.makeText(getApplication(), "Name is Empty", Toast.LENGTH_LONG).show();
//            return false;
//        } else if (pointsLeft != 0) {
//            Toast.makeText(getApplication(), String.valueOf(pointsLeft)
//                            + " Skill Point Remaining", Toast.LENGTH_LONG).show();
//                return false;
//        }
        Log.d("debug", "Game Stared");

        Toast.makeText(getApplication(), "Starting Game!", Toast.LENGTH_LONG).show();
        Model.getInstance().createPlayer(name, engineerStat,fighterStat, traderStat, pilotStat);
        Model.getInstance().createGame(difficulty);

        return true;
    }
}
