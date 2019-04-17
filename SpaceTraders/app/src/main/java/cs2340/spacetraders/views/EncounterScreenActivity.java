package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.DataStorage;
import cs2340.spacetraders.entity.Game;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Ship;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Travel.Pirate;
import cs2340.spacetraders.entity.Travel.Police;
import cs2340.spacetraders.entity.Travel.Trader;
import cs2340.spacetraders.entity.Universe.Galaxy;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.EncounterScreenViewModel;

/**
 * Activity for encountering a character
 */
public class EncounterScreenActivity extends AppCompatActivity {

    private EncounterScreenViewModel encounterScreenVM;
    private Encounterable character;
    private Player player;
    private int totalEncounters;
    private final Model model = Model.getInstance();
    private final Game game = model.getGame();
    private final DataStorage dataStorage = game.getDataStorage();
    private final Galaxy galaxy = game.getGalaxy();
    private Ship playerShip;
    private Ship characterShip;
    private ShipType characterShipType;

    private Button modelFleeButton;
    private Button modelAttackButton;
    private Button modelSurrenderButton;
    private Button modelUniqueButton;
    private TextView playerInfo;
    private TextView encounterInfo;
    private TextView encounterType;
    private TextView action;
    private ShipType playerShipType;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int totalEncounters = dataStorage.getTotalEncounters();

        Planet currentPlanet = null;
        while(currentPlanet == null) {
            currentPlanet = galaxy.getCurrentPlanet();
        }
        player = model.getPlayer();
        playerShip = player.getShip();
        playerShipType = playerShip.getShipType();
        encounterScreenVM = new EncounterScreenViewModel(currentPlanet);
        character = encounterScreenVM.setCharacter();

        if(character != null) {
            characterShip = character.getShip();
            characterShipType = characterShip.getShipType();
            dataStorage.setTotalEncounters(++totalEncounters);
        }

        if ( (character == null) || (totalEncounters >= 5) ) {
            setContentView(R.layout.encounter_screen);
            Button okButton = findViewById(R.id.encounterButton);

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            MarketScreenActivity.class);
                    dataStorage.setTotalEncounters(0);
                    startActivityForResult(intent, 0);
                }
            });
        } else if ( (character instanceof Police) && (totalEncounters < 5) ) {
            setContentView(R.layout.police_popup);
            setUp();
            modelUniqueButton = findViewById(R.id.bribe_button);
            modelUniqueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    easyToast("You" + character.uniqueAction() + "bribe the officer");
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });
        } else if ( (character instanceof Pirate) && (totalEncounters < 5) ) {
            setContentView(R.layout.pirate_popup);
            setUp();

        } else if ( (character instanceof Trader) && (totalEncounters < 5) ) {
            setContentView(R.layout.trader_popup);
            setUp();
            modelUniqueButton = findViewById(R.id.trade_button);
            modelUniqueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.uniqueAction();
                    easyToast("You successfully traded");
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });
        }

    }

    private View.OnClickListener makeFleeListener() {
        View.OnClickListener flee = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String put = "You tried to flee";
                action.setText(put);
                if (encounterScreenVM.pursueAction()) {
                    easyToast(encounterScreenVM.getAction());
                    update();
                } else {
                    easyToast(encounterScreenVM.getAction());
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            }
        };
        return flee;
    }

    private View.OnClickListener makeSurrenderListener() {
        View.OnClickListener surrender = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                character.surrenderResult();
                easyToast("You surrendered");
                Intent intent = new Intent(EncounterScreenActivity.this,
                        EncounterScreenActivity.class);
                startActivityForResult(intent, 0);
            }
        };
        return surrender;
    }

    private View.OnClickListener makeAttackListener() {
        View.OnClickListener attack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.setText("You attack");
                encounterScreenVM.playerAttack();
                player.setCriminalStatus(true);
                character.setHostile();
                update();
                if (characterShip.getHealth() <= 0) {
                    easyToast("You won the battle");
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                } else if (player.getHealth() <= 0) {
                    easyToast("You died");
                    //death
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    encounterScreenVM.characterAction();
                    action.setText(encounterScreenVM.getAction());
                    update();
                }
            }
        };
        return attack;
    }

    private void setUp() {
        modelAttackButton = findViewById(R.id.attack_button);
        modelFleeButton = findViewById(R.id.flee_button);
        modelSurrenderButton = findViewById(R.id.surrender_button);
        playerInfo = findViewById(R.id.playerInfoText);
        encounterInfo = findViewById(R.id.encounterInfoText);
        encounterType = findViewById(R.id.encounterType);
        action = findViewById(R.id.action);
        encounterType.setText(character.createDialogue());
        playerInfo.setText(encounterScreenVM.playerInfo());
        encounterInfo.setText(encounterScreenVM.encounterInfo(character));
        ImageView background = findViewById(R.id.imageView2);
        background.setImageResource(R.drawable.starry_night);
        ImageView playerShipImage = findViewById(R.id.playerShip);
        ImageView encounterShipImage = findViewById(R.id.encounterShip);
        playerShipImage.setImageResource(getShipDrawables()[playerShipType.ordinal()]);
        encounterShipImage.setImageResource(getShipDrawables()[characterShipType.ordinal()]);
        modelFleeButton.setOnClickListener(makeFleeListener());
        modelSurrenderButton.setOnClickListener(makeSurrenderListener());
        modelAttackButton.setOnClickListener(makeAttackListener());
    }

    private void update() {
        playerInfo.setText(encounterScreenVM.playerInfo());
        encounterInfo.setText(encounterScreenVM.encounterInfo(character));
        encounterType.setText(character.createDialogue());
    }

    /**
     * Makes a toast with message
     * @param toastMessage message
     */
    private void easyToast(String toastMessage) {
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    /**
     * images of the ships
     * @return the images
     */
    private int [] getShipDrawables() {
        return new int[]{R.drawable.gnat_l, R.drawable.flea_l, R.drawable.beetle_l,
                R.drawable.firefly_l,
                R.drawable.bumblebee_l, R.drawable.grasshopper_l, R.drawable.hornet_l,
                R.drawable.mosquito_l, R.drawable.termite_l};
    }


}
