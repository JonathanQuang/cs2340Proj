package cs2340.spacetraders.viewmodels;

import java.util.Random;

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
        if (random.nextDouble() < character.getIgnoreChance()) {
            action = character.toString() + " ignored you";
        } else if (random.nextDouble() < character.getFleeChance()) {
            character.attack(characterShip.getDamage());
            action = character.toString() + " fled";
        } else if (random.nextDouble() < character.getAttackChance()) {
            character.attack(characterShip.getDamage());
            action = character.toString() + " attacked you";
        } else {
            action = character.toString() + " watched you";
        }
    }

    /**
     * If the player flees, determines if the character will pursue
     * @return true or false, depending if chase occurs
     */
    public boolean pursueAction() {
        if (random.nextDouble() < character.getPursueChance()) {
            character.attack(characterShip.getDamage());
            action = character.toString() + " chased you down and attacked you";
            return true;
        } else {
            action = character.toString() + " let you go";
            return false;
        }
    }

    /**
     * Returns the player's combat stats
     * @return String representation of stats
     */
    public String playerInfo() {
        return "Ship: " + currentPlayer.getShip().getShipType() + "\n"
                + "Health: " + playerShip.getHealth();
    }

    /**
     * Returns the character's combat stats
     * @param character encountered character
     * @return String representation of stats
     */
    public String encounterInfo(Encounterable character) {
        return "Ship: " + character.getShip().getShipType() + "\n"
                + "Health: " + characterShip.getHealth();
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
        if (random.nextDouble() < politicalSystem.determineProbability(policeQuantity)) {
            this.character = new Police(planet);
            characterShip = character.getShip();
        } else if (random.nextDouble() < politicalSystem.determineProbability(pirateQuantity)) {
            this.character = new Pirate();
            characterShip = character.getShip();
        } else if (random.nextDouble() < politicalSystem.determineProbability(traderQuantity)) {
            this.character = new Trader(planet);
            characterShip = character.getShip();
        } else {
            character = null;
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
}
