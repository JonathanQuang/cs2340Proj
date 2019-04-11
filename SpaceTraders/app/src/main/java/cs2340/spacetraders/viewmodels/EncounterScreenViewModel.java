package cs2340.spacetraders.viewmodels;

import java.util.Random;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Travel.Pirate;
import cs2340.spacetraders.entity.Travel.Police;
import cs2340.spacetraders.entity.Travel.Trader;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.model.Model;

/**
 * Model for encountering a character
 */
public class EncounterScreenViewModel {
    private Planet planet;
    private Player currentPlayer = Model.getInstance().getPlayer();
    private Random random = new Random();
    private Encounterable character;
    private String action = "";

    /**
     * Constructor for the model, based on the planet's information
     * @param planet current planet
     */
    public EncounterScreenViewModel(Planet planet) {
        this.planet = planet;
    }

    /**
     * Player attacks the character
     */
    public void playerAttack() {
        character.takeDamage(currentPlayer.getShip().getDamage());
        if (character.getShip().getHealth() <= 0) {
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
            character.attack(character.getShip().getDamage());
            action = character.toString() + " fled";
        } else if (random.nextDouble() < character.getAttackChance()) {
            character.attack(character.getShip().getDamage());
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
            character.attack(character.getShip().getDamage());
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
        return "Ship: " + currentPlayer.getShip() + "\n"
                + "Health: " + currentPlayer.getShip().getHealth();
    }

    /**
     * Returns the character's combat stats
     * @param character encountered character
     * @return String representation of stats
     */
    public String encounterInfo(Encounterable character) {
        return "Ship: " + character.getShip() + "\n"
                + "Health: " + character.getShip().getHealth();
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
        if (random.nextDouble() < planet.getPoliticalSystem().determineProbability(
                planet.getPoliceQuantity())) {
            this.character = new Police(planet);
        } else if (random.nextDouble() < planet.getPoliticalSystem().determineProbability(
                planet.getPirateQuantity())) {
            this.character = new Pirate();
        } else if (random.nextDouble() < planet.getPoliticalSystem().determineProbability(
                planet.getTraderQuantity())) {
            this.character = new Trader(planet);
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
