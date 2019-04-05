package cs2340.spacetraders.entity.Universe.Equipment;

public enum WeaponTypes {

    PulseLaser("Pulse Laser", "Weapon", 750, 1500, 15, -1, "Weakest weapon available that uses smallest amount of energy to shoot light"),
    BeamLaser("Beam Laser", "Weapon", 11500, 9375, 25, -1, "Shoots a constant laser beam that's similar to two pulse lasers"),
    MilitaryLaser("Military Laser", "Weapon", 32200, 26250, 35, -1, "Shoots a dense and concentrated beam similar to three pulse lasers"),
    PhotonDisrupter("Photon Disruptor", "Weapon", 13800, 11250, 20, -1, "Weak weapon with a chance to electrically disable ships");



    private String equipName;
    private String equipType;
    private int buyprice;
    private int sellprice;
    //prices equal to -1 should be displayed as NA in the equipment shipyard
    //these values are set to -1 instead of being omitted to maintain the format of the arguments
    //on how other enums in the equipment folder are formatted

    private int power;
    private int charge;
    private String description;

    WeaponTypes(String equipName, String equipType, int buyprice, int sellprice, int power, int charge, String description) {
        this.equipName = equipName;
        this.equipType = equipType;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.power = power;
        this.charge = charge;
        this.description = description;
    }

    public String getEquipName(){return equipName;}

    public String getEquipType() {return equipType;}

    public int getBuyprice() {return buyprice;}

    public int getSellprice() {return sellprice;}

    public int getPower() {return power;}

    public int getCharge() {return charge;}

    public String getDescription() {return description;}

    }

