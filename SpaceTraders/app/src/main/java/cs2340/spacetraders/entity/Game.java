package cs2340.spacetraders.entity;

import java.io.Serializable;

import cs2340.spacetraders.entity.Universe.Galaxy;

public class Game implements Serializable {
    private Difficulty difficulty;
    private Galaxy galaxy;
    private DataStorage storage = new DataStorage();

    /** Initializes the game difficulty
     * @param difficulty the game difficulty
     */
    public Game(Difficulty difficulty) {
        this.difficulty = difficulty;
        makeGalaxy();
    }

    /**
     * Makes a new Galaxy
     */
    private void makeGalaxy() {
        galaxy = new Galaxy();
    }

    /**
     * Getter for galaxy
     * @return galaxy
     */
    public Galaxy getGalaxy() {
        return galaxy;
    }

    /** Gets the game difficulty
     * @return the game difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /** Gets the string representation fo the game difficulty
     * @return the string representation fo the game difficulty
     */
    public String toString() {
        return "The Game Difficulty is" + difficulty.toString();
    }

    /** gets data
     * @return data storage
     */
    public DataStorage getDataStorage() {
        return storage;
    }
}
