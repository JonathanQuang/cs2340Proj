package cs2340.spacetraders.entity.Universe;

import java.io.Serializable;

public class RelativePosition implements Serializable {
    private int x;
    private int y;
    private int rectRadius;
    private boolean compareWithOverlap;

    public RelativePosition(int x, int y) {
        this(x, y, 0);
    }

    /**
     * Makes a rectangle of influence around the center of a solar system
     * @param x the x-position of the system's center
     * @param y the y-position of the system's center
     * @param rectRadius the rectangular radius around the system's center
     */
    public RelativePosition(int x, int y, int rectRadius) {
        this(x, y, rectRadius, false);
    }

    public RelativePosition(int x, int y, int rectRadius, boolean compareWithOverlap) {
        this.x = x;
        this.y = y;
        this.rectRadius = rectRadius;
        this.compareWithOverlap = compareWithOverlap;
    }

    /**
     * @param o other point being compared
     * @return if a point is in the square of influence of another point
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RelativePosition)) {
            return false;
        }
        RelativePosition that = (RelativePosition) o;
        return isInSquareOfInfluence(that);
    }

    /**
     * Checks if any point in OTHER point's surrounding square is equal to THIS
     * point's surrounding square
     * Plants have a padding of 1, for solar system it depends on size
     *
     * if (compareWithOverlap) checks that area don't overlap as in
     *  #####
     *  #   #
     *  #  ###
     *  ####0#
     *     ###
     *
     * else check if point is not in the cross around it as in
     *      -
     *     -*-
     *      -*
     * @param that other point being compared
     * @return if a point is in the square of influence of another point
     */
    private boolean isInSquareOfInfluence(RelativePosition that) {
        if (compareWithOverlap) {
            for (int i = -rectRadius; i <= rectRadius; i++) {
                for (int j = -rectRadius; j <= rectRadius; j++) {
                    for (int k = -that.rectRadius; k <= that.rectRadius; k++) {
                        for (int l = -that.rectRadius; l <= that.rectRadius; l++) {
                            if (x + i == that.x + k && y + j == that.y + l) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return (x == that.x && y == that.y)
                    || (x == that.x && y - 1 == that.y) || (x == that.x && y + 1 == that.y)
                    || (x - 1 == that.x && y == that.y) || (x + 1 == that.x && y == that.y);
        }
    }

    public String toString() {
        return "Pos(" + x + ", " + y + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRectRadius() {
        return rectRadius;
    }
}
