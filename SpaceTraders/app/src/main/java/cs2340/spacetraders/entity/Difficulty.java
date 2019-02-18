package cs2340.spacetraders.entity;

public enum Difficulty {
    Beginner("Beginner"),
    Easy("Easy"),
    Normal ("Normal"),
    Hard("Hard"),
    Impossible("Impossible");

    private final String difficulty;

    /**
     * Initializes difficulty string of enum
     * @param difficulty  the difficulty
     */
    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the string representation of the game difficultly
     * @return the difficulty
     */
    public String getDifficulty() { return difficulty; }


    /**
     * Gets the string representation of the game difficultly to print
     * @return the string representation of the difficulty
     */
    public String toString() { return difficulty; }
}
