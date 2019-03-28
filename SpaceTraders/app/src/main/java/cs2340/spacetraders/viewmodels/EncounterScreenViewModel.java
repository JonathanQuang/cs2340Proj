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

public class EncounterScreenViewModel {
    private Planet planet;
    private Player currentPlayer;
    private Random random = new Random();
    private Encounterable character;

    public EncounterScreenViewModel(Planet planet) {
        this.planet = planet;
    }


    public void playerAttack() {
        character.takeDamage(currentPlayer.getShip().getDamage());
        if (character.getShip().getHealth() <= 0) {
            character.characterDestruction();
        }
    }

    public void characterAttack() {
        character.attack(character.getShip().getDamage());
        if (currentPlayer.getShip().getHealth() <= 0) {
            currentPlayer.death();
        }
    }

    public String popUpBuyStr() {
        return "Planet " + " Supply: " + "\n"
                + "Buying Price: $" + "\n"
                //+ "Quantity Purchasing: ---" + "\n"
                //+ "Total Cost: ---" + "\n";
                + "You currently have " + " " + "\n"
                + "You have " + currentPlayer.getCredits() + " credits" + "\n"
                + "Type below amount purchase";
    }

    public String popUpSellStr() {
        return "Your current " + " Supply: " + "\n"
                + "Selling Price: $" + "\n"
                //+ "Quantity Selling: ---" + "\n"
                //+ "Total Revenue: ---" + "\n"
                //+ "Average Revenue per Unit: ---";
                + "You currently have " + " " + "\n"
                + "You have " + currentPlayer.getCredits() + " credits" + "\n"
                + "Type below amount to sell";
    }

    public void setPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Encounterable setCharacter() {
        if (random.nextDouble() > planet.getPoliticalSystem().determineProbability(planet.getPoliceQuantity())) {
            this.character = new Police(planet);
        } else if (random.nextDouble() > planet.getPoliticalSystem().determineProbability(planet.getPirateQuantity())) {
            this.character = new Pirate();
        } else if (random.nextDouble() > planet.getPoliticalSystem().determineProbability(planet.getTraderQuantity())) {
            this.character = new Trader(planet);
        } else {
            character = null;
        }
        return character;
    }

    public Encounterable getCharacter() {
        return character;
    }
}
