package cs2340.spacetraders.entity;

/**
 * The Difficulty of the game
 */
public enum Difficulty {
    Beginner("Beginner", 0.25),
    Easy("Easy", 0.5),
    Normal ("Normal", 1),
    Hard("Hard", 3),
    Impossible("Impossible", 7);

    private final String difficulty;
    private final double multiplier;

    /**
     * Initializes difficulty string of enum
     * @param difficulty  the difficulty
     */
    Difficulty(String difficulty, double multiplier) {
        this.difficulty = difficulty;
        this.multiplier = multiplier;
    }

    /**
     * Gets the string representation of the game difficultly
     * @return the difficulty
     */
    public String getDifficulty() { return difficulty; }

    /**
     * Returns the difficulty multiplier
     * @return multiplier
     */
    public double getMultipler() { return multiplier; }


    /**
     * Gets the string representation of the game difficultly to print
     * @return the string representation of the difficulty
     */
    public String toString() { return difficulty; }
}
