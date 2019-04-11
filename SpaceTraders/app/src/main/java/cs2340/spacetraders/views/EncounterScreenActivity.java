package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Travel.Pirate;
import cs2340.spacetraders.entity.Travel.Police;
import cs2340.spacetraders.entity.Travel.Trader;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        totalEncounters = Model.getInstance().getGame().getDataStorage().getTotalEncounters();

        Planet currentPlanet = null;
        while(currentPlanet == null) {
            currentPlanet = Model.getInstance().getGame().getGalaxy().getCurrentPlanet();
        }
        player = Model.getInstance().getPlayer();
        encounterScreenVM = new EncounterScreenViewModel(currentPlanet);
        character = encounterScreenVM.setCharacter();

        if ( (character == null) || (totalEncounters >= 5) ) {
            setContentView(R.layout.encounter_screen);
            Button okButton = findViewById(R.id.encounterButton);

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            MarketScreenActivity.class);
                    Model.getInstance().getGame().getDataStorage().setTotalEncounters(0);
                    startActivityForResult(intent, 0);
                }
            });
        } else if ( (character instanceof Police) && (totalEncounters < 5) ) {
            setContentView(R.layout.police_popup);
            Model.getInstance().getGame().getDataStorage().setTotalEncounters(++totalEncounters);
            Button attackButton = findViewById(R.id.attack_button);
            Button fleeButton = findViewById(R.id.flee_button);
            Button surrenderButton = findViewById(R.id.surrender_button);
            Button bribeButton = findViewById(R.id.bribe_button);
            final TextView playerInfo = findViewById(R.id.playerInfoText);
            final TextView encounterInfo = findViewById(R.id.encounterInfoText);
            final TextView encounterType = findViewById(R.id.encounterType);
            final TextView action = findViewById(R.id.action);

            encounterType.setText(character.createDialogue());
            playerInfo.setText(encounterScreenVM.playerInfo());
            encounterInfo.setText(encounterScreenVM.encounterInfo(character));

            fleeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.setText("You tried to flee");
                    if (encounterScreenVM.pursueAction()) {
                        easyToast(encounterScreenVM.getAction());
                        playerInfo.setText(encounterScreenVM.playerInfo());
                    } else {
                        easyToast(encounterScreenVM.getAction());
                        Intent intent = new Intent(EncounterScreenActivity.this,
                                EncounterScreenActivity.class);
                        startActivityForResult(intent, 0);
                    }
                }
            });

            surrenderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.surrenderResult();
                    easyToast("You surrendered");
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });

            bribeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    easyToast("You" + character.uniqueAction() + "bribe the officer");
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });

            attackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.setText("You attack");
                    encounterScreenVM.playerAttack();
                    player.setCriminalStatus(true);
                    character.setHostile();
                    playerInfo.setText(encounterScreenVM.playerInfo());
                    encounterInfo.setText(encounterScreenVM.encounterInfo(character));
                    encounterType.setText(character.createDialogue());
                    if (encounterScreenVM.getCharacter().getShip().getHealth() <= 0) {
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
                    }
                }
            });
        } else if ( (character instanceof Pirate) && (totalEncounters < 5) ) {
            setContentView(R.layout.pirate_popup);
            Model.getInstance().getGame().getDataStorage().setTotalEncounters(++totalEncounters);
            Button attackButton = findViewById(R.id.attack_button);
            Button fleeButton = findViewById(R.id.flee_button);
            Button surrenderButton = findViewById(R.id.surrender_button);
            final TextView playerInfo = findViewById(R.id.playerInfoText);
            final TextView encounterInfo = findViewById(R.id.encounterInfoText);
            final TextView encounterType = findViewById(R.id.encounterType);
            final TextView action = findViewById(R.id.action);

            encounterType.setText(character.createDialogue());
            playerInfo.setText(encounterScreenVM.playerInfo());
            encounterInfo.setText(encounterScreenVM.encounterInfo(character));

            fleeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.setText("You tried to flee");
                    if (encounterScreenVM.pursueAction()) {
                        easyToast(encounterScreenVM.getAction());
                        playerInfo.setText(encounterScreenVM.playerInfo());
                    } else {
                        easyToast(encounterScreenVM.getAction());
                        Intent intent = new Intent(EncounterScreenActivity.this,
                                EncounterScreenActivity.class);
                        startActivityForResult(intent, 0);
                    }
                }
            });

            surrenderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.surrenderResult();
                    easyToast("You surrendered");
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });

            attackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.setText("You attack");
                    encounterScreenVM.playerAttack();
                    character.setHostile();
                    playerInfo.setText(encounterScreenVM.playerInfo());
                    encounterInfo.setText(encounterScreenVM.encounterInfo(character));
                    if (encounterScreenVM.getCharacter().getShip().getHealth() <= 0) {
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
                    }
                }
            });
        } else if ( (character instanceof Trader) && (totalEncounters < 5) ) {
            setContentView(R.layout.trader_popup);
            Model.getInstance().getGame().getDataStorage().setTotalEncounters(++totalEncounters);
            Button attackButton = findViewById(R.id.attack_button);
            Button fleeButton = findViewById(R.id.flee_button);
            Button surrenderButton = findViewById(R.id.surrender_button);
            Button tradeButton = findViewById(R.id.trade_button);
            final TextView playerInfo = findViewById(R.id.playerInfoText);
            final TextView encounterInfo = findViewById(R.id.encounterInfoText);
            final TextView encounterType = findViewById(R.id.encounterType);
            final TextView action = findViewById(R.id.action);

            encounterType.setText(character.createDialogue());
            playerInfo.setText(encounterScreenVM.playerInfo());
            encounterInfo.setText(encounterScreenVM.encounterInfo(character));

            fleeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.setText("You tried to flee");
                    if (encounterScreenVM.pursueAction()) {
                        easyToast(encounterScreenVM.getAction());
                        playerInfo.setText(encounterScreenVM.playerInfo());
                    } else {
                        easyToast(encounterScreenVM.getAction());
                        Intent intent = new Intent(EncounterScreenActivity.this,
                                EncounterScreenActivity.class);
                        startActivityForResult(intent, 0);
                    }
                }
            });

            surrenderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.surrenderResult();
                    easyToast("You surrendered");
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });

            tradeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.uniqueAction();
                    easyToast("You successfully traded");
                    Intent intent = new Intent(EncounterScreenActivity.this,
                            EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });

            attackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.setText("You attack");
                    encounterScreenVM.playerAttack();
                    player.setCriminalStatus(true);
                    character.setHostile();
                    playerInfo.setText(encounterScreenVM.playerInfo());
                    encounterInfo.setText(encounterScreenVM.encounterInfo(character));
                    encounterType.setText(character.createDialogue());
                    if (encounterScreenVM.getCharacter().getShip().getHealth() <= 0) {
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
                    }
                }
            });
        }

    }

    private void easyToast(String toastMessage) {
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

}
