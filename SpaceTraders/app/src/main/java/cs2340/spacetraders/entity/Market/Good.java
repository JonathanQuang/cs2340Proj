package cs2340.spacetraders.entity.Market;

import java.util.Random;

import cs2340.spacetraders.entity.Universe.PlanetaryEvent;
import cs2340.spacetraders.entity.Universe.Resources;
import cs2340.spacetraders.entity.Universe.TechLevel;

/**
 * A enum with all the good a planet can have
 */
public enum Good {
    Water("Water", 30, 3, 30, 50, 4, 2, 0, 0, PlanetaryEvent.Drought,
            Resources.LOTSOFWATER, Resources.DESERT),
    Furs("Furs", 250, 10, 230, 280, 10, 0, 0, 0, PlanetaryEvent.Cold,
            Resources.RICHFAUNA, Resources.LIFELESS),
    Food("Food", 100, 5, 90, 160, 5, 1, 1, 0, PlanetaryEvent.Cropfail,
            Resources.RICHSOIL, Resources.POORSOIL),
    Ores("Ores", 350, 20, 350, 420, 10, 3, 2, 2, PlanetaryEvent.War,
            Resources.MINERALRICH, Resources.MINERALPOOR),
    Games("Games", 250, -10, 160, 270, 5, 6, 3, 1, PlanetaryEvent.Boredom,
            Resources.ARTISTIC, null),
    Firearms("Firearms", 1250, -75, 600, 1100, 100, 5, 3, 1, PlanetaryEvent.War,
            Resources.WARLIKE, null),
    Medicine("Medicine", 650, -20, 400, 700, 10, 6, 4, 1, PlanetaryEvent.Plague,
            Resources.LOTSOFHERBS, null),
    Machines("Machines", 900, -30, 600, 800, 5, 5, 4, 3, PlanetaryEvent.LackOfWorkers,
            null, null),
    Narcotics("Narcotics", 3500, -125, 2000, 3000, 150, 5, 5, 0, PlanetaryEvent.Boredom,
            Resources.WEIRDMUSHROOMS, null),
    Robots("Robots", 5000, -150, 3500, 5000, 100, 7, 6, 4, PlanetaryEvent.LackOfWorkers,
            null, null);

    private final String goodName;
    private final int basePrice; //base price
    private final int priceIncrease; // price increase per level
    private final int variance; //how much the price can vary
    private final int maxProductionLevel; //tech level which produces most of this item
    private final int minProduceLevel; //min tech level to produce this resource
    private final int minUseLevel; //min tech level to use this resource
    private final int minPrice;
    private final int maxPrice;
    private final PlanetaryEvent priceIncreaseEvent;
    private final Resources lowPriceEnv;
    private final Resources highPriceEnv;
    private final Random rand;

    /**
     * Constructor for Good
     * @param goodName the name of the good
     * @param basePrice the base price of the good
     * @param priceIncrease the price increase per level
     * @param minPrice the minimum price a Trader will sell a good
     * @param maxPrice the maximum price a Trader will sell a good
     * @param variance how much the price can vary
     * @param maxLevel the tech level which produces most of this item
     * @param minProduceLevel the min tech level to produce this resource
     * @param minUseLevel min tech level to use this resource
     * @param priceIncreaseEvent an event that would increase the price of a good
     * @param lowPriceEnv environment where this good is produced the most
     * @param highPriceEnv environment where this good is least the most
     */
    Good(String goodName, int basePrice, int priceIncrease, int minPrice, int maxPrice,
         int variance, int maxLevel, int minProduceLevel, int minUseLevel,
         PlanetaryEvent priceIncreaseEvent, Resources lowPriceEnv, Resources highPriceEnv) {
        this.goodName = goodName;
        this.basePrice = basePrice;
        this.priceIncrease = priceIncrease;
        this.minPrice = minPrice;
        this.maxProductionLevel = maxLevel;
        this.maxPrice = maxPrice;
        this.variance = variance;
        this.minProduceLevel = minProduceLevel;
        this.minUseLevel = minUseLevel;
        this.priceIncreaseEvent = priceIncreaseEvent;
        this.lowPriceEnv = lowPriceEnv;
        this.highPriceEnv = highPriceEnv;
        rand = new Random();
    }


    /**
     * @return get the name of a good
     */
    public String getGoodName() {
        return goodName;
    }

    public int getTraderPrice() {
        return (minPrice + maxPrice + variance) / (rand.nextInt(2) + 1);
    }

    /**
     * @param techLevel the techLevel being checked if good can be produced by
     * @return if good can be produced by this tech level
     */
    public boolean canSellTo(TechLevel techLevel) {
        return techLevel.ordinal() >= minUseLevel;
    }

    /**
     * @param techLevel the techLevel being checked if good can be purchased from
     * @return if good can be purchased from by this tech level
     */
    public boolean canBuyFrom(TechLevel techLevel) {
        return techLevel.ordinal() >= minProduceLevel;
    }

    /**
     * @param techLevel the tech level of the planet that the price are being calculated for
     * @param resources the resources of the planet that the price are being calculated for
     * @param planetaryEvent the planetary events of the planet
     * @return the price of the good on that planet
     */
    public int calcBuyPrice(TechLevel techLevel,
                            Resources resources, PlanetaryEvent planetaryEvent) {
        int price = basePrice + ((techLevel.ordinal() - minProduceLevel) * priceIncrease);
        boolean heads = rand.nextInt(2) == 1;
        int var = (int) (basePrice * (rand.nextInt(variance + 1) / 100.0));
        int finalPrice = heads ? (price + var) : (price - var);

        finalPrice = (planetaryEvent == priceIncreaseEvent) ? (finalPrice * 3) : (finalPrice);
        finalPrice = (resources == lowPriceEnv) ? (int) (finalPrice * .5) : finalPrice;
        finalPrice = (resources == highPriceEnv) ? (int) (finalPrice * 1.5) : finalPrice;
        finalPrice = (finalPrice > 0) ? finalPrice : 0;
        return finalPrice;
    }

    /**
     * @param techLevel the tech level of the planet that the number of good is being calculated for
     * @return the count of the good
     */
    public int makeGoodCount(TechLevel techLevel){
        int count = rand.nextInt(10) + 1;
        count = techLevel.ordinal() == maxProductionLevel ? (count * 2) : (count);
        return count;
    }
}
