package cs2340.spacetraders.model;

import java.util.HashMap;
import java.util.Map;

import cs2340.spacetraders.entity.Player;

public class Model {

    private Repository myRepository;
    private Map<String, Object> interactorMap;

    private static  Model instance = new Model();

    public static Model getInstance() { return instance; }

    /**
     * Make a new Model instance
     */
    private Model() {
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    private void registerInteractors() {
        //interactorMap.put("Testing", new Player(myRepository));
    }

    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }
}
