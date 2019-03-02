package cs2340.spacetraders.entity;

public enum Goods {
    Water("Water", 30, 3, 30, 50, 4, 2),
    Furs("Furs", 250, 10, 230, 280, 10, 0),
    Food("Food", 100, 5, 90, 160, 5, 1),
    Ores("Ores", 350, 20, 350, 420, 10, 3),
    Games("Games", 250, -10, 160, 270, 5, 6),
    Firearms("Firearms", 1250, -75, 600, 1100, 100, 5),
    Medicine("Medicine", 650, -20, 400, 700, 10, 6),
    Machines("Machines", 900, -30, 600, 800, 5, 5),
    Narcotics("Narcotics", 3500, -125, 2000, 3000, 150, 5),
    Robots("Robots", 5000, -150, 3500, 5000, 100, 7);

    private final String goodName;
    private final int basePrice; //base price
    private final int priceIncrease; // price increase per level
    private final int minPrice; //min price offered by trader
    private final int maxPrice; //max price offered by trader
    private final int variance; //how much the price can vary
    private final int  maxLevel; //tech level which produces most of this item

    Goods(String goodName, int basePrice, int priceIncrease, int minPrice,
           int maxPrice, int variance, int maxLevel) {
        this.goodName = goodName;
        this.basePrice = basePrice;
        this.priceIncrease = priceIncrease;
        this.minPrice = minPrice;
        this.maxLevel = maxLevel;
        this.maxPrice = maxPrice;
        this.variance = variance;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getPriceIncrease() {
        return priceIncrease;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public String getGoodName() {
        return goodName;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getVariance() {
        return variance;
    }
}

