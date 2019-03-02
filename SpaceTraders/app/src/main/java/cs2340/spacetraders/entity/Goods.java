package cs2340.spacetraders.entity;

public enum Goods {
    Water("Water", 30, 3, 30, 50, 4, 2, 0, 0),
    Furs("Furs", 250, 10, 230, 280, 10, 0, 0, 0),
    Food("Food", 100, 5, 90, 160, 5, 1, 1, 0),
    Ores("Ores", 350, 20, 350, 420, 10, 3, 2, 2),
    Games("Games", 250, -10, 160, 270, 5, 6, 3, 1),
    Firearms("Firearms", 1250, -75, 600, 1100, 100, 5, 3, 1),
    Medicine("Medicine", 650, -20, 400, 700, 10, 6, 4, 1),
    Machines("Machines", 900, -30, 600, 800, 5, 5, 4, 3),
    Narcotics("Narcotics", 3500, -125, 2000, 3000, 150, 5, 5, 0),
    Robots("Robots", 5000, -150, 3500, 5000, 100, 7, 6, 4);

    private final String goodName;
    private final int basePrice; //base price
    private final int priceIncrease; // price increase per level
    private final int minPrice; //min price offered by trader
    private final int maxPrice; //max price offered by trader
    private final int variance; //how much the price can vary
    private final int  maxLevel; //tech level which produces most of this item
    private final int minProduceLevel; //min tech level to produce this resource
    private final int minUseLevel; //min tech level to use this resource

    Goods(String goodName, int basePrice, int priceIncrease, int minPrice, int maxPrice,
          int variance, int maxLevel, int minProduceLevel, int minUseLevel) {
        this.goodName = goodName;
        this.basePrice = basePrice;
        this.priceIncrease = priceIncrease;
        this.minPrice = minPrice;
        this.maxLevel = maxLevel;
        this.maxPrice = maxPrice;
        this.variance = variance;
        this.minProduceLevel = minProduceLevel;
        this.minUseLevel = minUseLevel;
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

    public int getMinProduceLevel() {
        return minProduceLevel;
    }

    public int getMinUseLevel() {
        return minUseLevel;
    }
}

