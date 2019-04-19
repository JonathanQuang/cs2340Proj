package cs2340.spacetraders.viewmodels;

import java.util.Random;

import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.Market;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Travel.Pirate;
import cs2340.spacetraders.entity.Travel.Police;
import cs2340.spacetraders.entity.Travel.Trader;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.PoliticalSystem;
import cs2340.spacetraders.model.Model;

/**
 * Model for encountering a character
 */
public class EncounterScreenViewModel {
    private final Planet planet;
    private final Model model = Model.getInstance();
    private Player currentPlayer = model.getPlayer();
    private final Random random = new Random();
    private Encounterable character;
    private String action = "";
    private final Ship playerShip = currentPlayer.getShip();
    private Ship characterShip;
    private final PoliticalSystem politicalSystem;
    private final String policeQuantity;
    private final String traderQuantity;
    private final String pirateQuantity;
    private boolean ignore = false;
    private Market currentMarket;


    /**
     * Constructor for the model, based on the planet's information
     * @param planet current planet
     */
    public EncounterScreenViewModel(Planet planet) {
        this.planet = planet;
        this.politicalSystem = planet.getPoliticalSystem();
        this.policeQuantity = politicalSystem.getPoliceQuantity();
        this.pirateQuantity = politicalSystem.getPirateQuantity();
        this.traderQuantity = politicalSystem.getTradersQuantity();
        character = null;
    }

    /**
     * Player attacks the character
     */
    public void playerAttack() {
        character.takeDamage(playerShip.getDamage());
        if (characterShip.getHealth() <= 0) {
            character.characterDestruction();
        }
    }

    /**
     * The character performs an action
     */
    public void characterAction() {
        if (random.nextDouble() < character.getUniqueChance()) {
            action = character.uniqueAction();
        } else if (random.nextDouble() < character.getIgnoreChance()) {
            action = " ignored you";
            ignore = true;
        } else if (random.nextDouble() < character.getFleeChance()) {
            action = " fled";
        } else if (random.nextDouble() < character.getAttackChance()) {
            character.attack(characterShip.getDamage());
            action = " attacked you";
        } else {
            action = " was stunned";
            ignore = true;
        }
    }

    /**
     * If the player flees, determines if the character will pursue
     * @return true or false, depending if chase occurs
     */
    public boolean pursueAction() {
        if (random.nextDouble() < character.getPursueChance()) {
            character.attack(characterShip.getDamage());
            action = " chased you down and attacked you";
            return true;
        } else {
            action = " let you go";
            return false;
        }
    }

    /**
     * Returns the player's combat stats
     * @return String representation of stats
     */
    public String playerInfo() {
        if (playerShip.getHealth() > 0) {
            return "Ship: " + currentPlayer.getShip().getShipType() + "\n"
                    + "Health: " + playerShip.getHealth();
        }
        else {
            return "Ship: " + currentPlayer.getShip().getShipType() + "\n"
                    + "Health: 0";
        }

    }

    /**
     * Returns the character's combat stats
     * @param character encountered character
     * @return String representation of stats
     */
    public String encounterInfo(Encounterable character) {
        if (characterShip.getHealth() > 0) {
            return "Ship: " + character.getShip().getShipType() + "\n"
                + "Health: " + characterShip.getHealth();
        } else {
            return "Ship: " + character.getShip().getShipType() + "\n"
                    + "Health: 0";
        }

    }

    /**
     * Setter for player
     * @param currentPlayer player
     */
    public void setPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Randomly sets the encountered character
     * @return character
     */
    public Encounterable setCharacter() {
        for (int i = 0; i < 5; i++) {
            int testing = random.nextInt(3);
            if (testing == 0 && random.nextDouble() < politicalSystem.determineProbability(policeQuantity)) {
                this.character = new Police(planet);
                characterShip = character.getShip();
            } else if (testing == 1 && random.nextDouble() < politicalSystem.determineProbability(pirateQuantity)) {
                this.character = new Pirate();
                characterShip = character.getShip();
            } else if (testing == 2 && random.nextDouble() < politicalSystem.determineProbability(traderQuantity)) {
                this.character = new Trader();
                currentMarket = new Market(((Trader) character).getTraderInventory(), currentPlayer.getInventory());
                characterShip = character.getShip();
            }
        }
        return character;
    }

    /**
     * Getter for character
     * @return character
     */
    public Encounterable getCharacter() {
        return character;
    }

    /**
     * Getter for the action the character takes
     * @return action
     */
    public String getAction() {
        return action;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public String popUpBuyStr() {
        return "Planet " + currentMarket.getCurrentGood().toString() + " Supply: "
                + currentMarket.getCurrentGoodCountInPlanet() + "\n"
                + "Buying Price: $" + currentMarket.getPlanetBuyPrice() + "\n"
                //+ "Quantity Purchasing: ---" + "\n"
                //+ "Total Cost: ---" + "\n";
                + "You currently have " + currentMarket.getCurrentGoodCountInPlayer() + " "
                + currentMarket.getGoodName() + "\n"
                + "You have " + currentMarket.getPlayerCredits() + " credits" + "\n"
                + "Type below amount purchase";
    }

    public boolean validQuantityToBuy(String buyText) {
        return currentMarket.validQuantityToBuy(buyText);
    }

    public void buyGood(int amount) {
        currentMarket.buyGood(amount);
    }

    public void setGood(Good currentGood) {
        currentMarket.setGood(currentGood);
    }
}
