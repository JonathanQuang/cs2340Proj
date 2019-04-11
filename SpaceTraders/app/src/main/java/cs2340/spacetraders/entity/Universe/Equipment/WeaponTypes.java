package cs2340.spacetraders.entity.Universe.Equipment;

/**
 * Enum for the types of weapons
 */
public enum WeaponTypes {

    PulseLaser("Pulse Laser", "Weapon", 1750, 1500, 15, -1, "Weakest weapon available that uses " +
            "smallest amount of energy to shoot light"),
    BeamLaser("Beam Laser", "Weapon", 11500, 9375, 25, -1, "Shoots a constant laser beam that's " +
            "similar to two pulse lasers"),
    MilitaryLaser("Military Laser", "Weapon", 32200, 26250, 35, -1, "Shoots a dense and " +
            "concentrated beam similar to three pulse lasers"),
    PhotonDisrupter("Photon Disruptor", "Weapon", 13800, 11250, 20, -1, "Weak weapon with a " +
            "chance to electrically disable ships");



    private final String equipName;
    private final String equipType;
    private final int buyPrice;
    private final int sellPrice;
    //prices equal to -1 should be displayed as NA in the equipment shipyard
    //these values are set to -1 instead of being omitted to maintain the format of the arguments
    //on how other enums in the equipment folder are formatted (or how they should be formatted
    // according to the actual
    //space traders game

    private final int power;
    private final int charge;
    private final String description;

    WeaponTypes(String equipName, String equipType, int buyprice, int sellprice, int power,
                int charge, String description) {
        this.equipName = equipName;
        this.equipType = equipType;
        this.buyPrice = buyprice;
        this.sellPrice = sellprice;
        this.power = power;
        this.charge = charge;
        this.description = description;
    }

    /**
     * Getter for weapon name
     * @return string representation of weapon
     */
    public String getEquipName(){return equipName;}

    /**
     * Getter for equip type
     * @return equipType
     */
    public String getEquipType() {return equipType;}

    /**
     * Getter for the buying price
     * @return buyPrice
     */
    public int getBuyPrice() {return buyPrice;}

    /**
     * Getter for the selling price
     * @return sellPrice
     */
    public int getSellPrice() {return sellPrice;}

    /**
     * getter for attack power
     * @return power
     */
    public int getPower() {return power;}

    /**
     * Getter for charge
     * @return charge
     */
    public int getCharge() {return charge;}

    /**
     * Getter for weapon description
     * @return description
     */
    public String getDescription() {return description;}

    }

