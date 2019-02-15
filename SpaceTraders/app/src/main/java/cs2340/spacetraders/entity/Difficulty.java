package cs2340.spacetraders.entity;

public enum Difficulty {
    Beginner("Beginner"),
    Easy("Easy"),
    Normal ("Normal"),
    Hard("Hard"),
    Impossible("Impossible");

    /** the full string representation of the difficulty */
    private final String difficulty;

    /**
     * Constructor for the enumeration
     *
     * @param difficulty  the difficulty
     */
    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     *
     * @return   the difficulty
     */
    public String getDifficulty() { return difficulty; }


    /**
     *
     * @return the display string representation of the difficulty
     */
    public String toString() { return difficulty; }
}
