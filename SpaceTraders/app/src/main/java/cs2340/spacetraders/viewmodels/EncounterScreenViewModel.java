package cs2340.spacetraders.viewmodels;

import android.util.Log;

import java.util.Random;

import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Travel.Pirate;
import cs2340.spacetraders.entity.Travel.Police;
import cs2340.spacetraders.entity.Travel.Trader;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.model.Model;

public class EncounterScreenViewModel {
    private Planet planet;
    private Player currentPlayer = Model.getInstance().getPlayer();
    private Random random = new Random();
    private Encounterable character;
    private String action = "";

    public EncounterScreenViewModel(Planet planet) {
        this.planet = planet;
    }


    public void playerAttack() {
        character.takeDamage(currentPlayer.getShip().getDamage());
        if (character.getShip().getHealth() <= 0) {
            character.characterDestruction();
        }
    }

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

    public String playerInfo() {
        return "Ship: " + currentPlayer.getShip() + "\n"
                + "Health: " + currentPlayer.getShip().getHealth();
    }

    public String encounterInfo(Encounterable character) {
        return "Ship: " + character.getShip() + "\n"
                + "Health: " + character.getShip().getHealth();
    }

    public void setPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Encounterable setCharacter() {
        if (random.nextDouble() < planet.getPoliticalSystem().determineProbability(planet.getPoliceQuantity())) {
            this.character = new Police(planet);
        } else if (random.nextDouble() < planet.getPoliticalSystem().determineProbability(planet.getPirateQuantity())) {
            this.character = new Pirate();
        } else if (random.nextDouble() < planet.getPoliticalSystem().determineProbability(planet.getTraderQuantity())) {
            this.character = new Trader(planet);
        } else {
            character = null;
        }
        return character;
    }

    public Encounterable getCharacter() {
        return character;
    }

    public String getAction() {
        return action;
    }
}
