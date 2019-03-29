package cs2340.spacetraders.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Difficulty;
import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Travel.Encounterable;
import cs2340.spacetraders.entity.Travel.Pirate;
import cs2340.spacetraders.entity.Travel.Police;
import cs2340.spacetraders.entity.Travel.Trader;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.EncounterScreenViewModel;

public class EncounterScreenActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private Button mButton;

    private PopupWindow mPopupWindow;

    private LinearLayout modelLinearLayout;
    private TextView planetNametext;
    private EncounterScreenViewModel encounterScreenVM;

    private Inventory playerInventory;
    private Encounterable character;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("EncounterGabe11", "Start!");
        int totalEncounters = 0;
        Planet currentPlanet = null;
        while(currentPlanet == null) {
            Log.d("EncounterGabe11", "Planet!");
            currentPlanet = Model.getInstance().getGame().getGalaxy().getCurrentPlanet();
        }
        //planetNametext = findViewById(R.id.planetName);
        //planetNametext.setText(currentPlanet.getName().toString());

        encounterScreenVM = new EncounterScreenViewModel(currentPlanet);
        Log.d("EncounterGabe11", "VM!");

        character = encounterScreenVM.setCharacter();
        Log.d("EncounterGabe11", "Character!");

        if (character == null) {
            Log.d("EncounterGabe11", "NoEncounter");
            setContentView(R.layout.encounter_screen);
            Button okButton = findViewById(R.id.encounterButton);

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this, MarketScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });
        } else {
            Log.d("EncounterGabe11", "Police");
            //showPolicePopupWindow(findViewById(android.R.id.content));
            setContentView(R.layout.police_popup);
            totalEncounters++;
            Button okButton = findViewById(R.id.attack_button);

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this, EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });
        }
        /*
        else if (character instanceof Police && totalEncounters <= 2) {
            Log.d("EncounterGabe11", "Police");
            //showPolicePopupWindow(findViewById(android.R.id.content));
            setContentView(R.layout.police_popup);
            totalEncounters++;
            Button okButton = findViewById(R.id.attack_button);

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this, EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });
        } else if (character instanceof Pirate && totalEncounters <= 2) {
            Log.d("EncounterGabe11", "Pirate");
            //showPiratePopupWindow(findViewById(android.R.id.content));
            setContentView(R.layout.pirate_popup);
            totalEncounters++;
            Button okButton = findViewById(R.id.attack_button);

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this, EncounterScreenActivity.class);
                    startActivityForResult(intent, 0);
                }
            });
        } else if (character instanceof Trader && totalEncounters <= 2) {
            Log.d("EncounterGabe11", "Trader");
            setContentView(R.layout.trader_popup);
            totalEncounters++;
            Button okButton = findViewById(R.id.encounterButton);

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this, EncounterScreenActivity.class);
                    startActivityForResult(intent,0);
                }
            });
        }
        */

    }
/*
        while (currentPlanet == null) {
            Log.d("EncounterGabe11", "Planet!");
            currentPlanet = Model.getInstance().getGame().getGalaxy().getCurrentPlanet();
            planetNametext.setText(currentPlanet.getName().toString());
        }

        while (playerInventory == null) {
            //Log.d("EncounterGabe11", "Player Inventory!");
            playerInventory = Model.getInstance().getPlayer().getInventory();
        }

        while (encounterScreenVM == null) {
            //Log.d("EncounterGabe11", "Encounter VM!");
            encounterScreenVM = new EncounterScreenViewModel(currentPlanet);
            encounterScreenVM.setPlayer(Model.getInstance().getPlayer());
        }

        //Log.d("EncounterGabe11", "Character!");
        character = encounterScreenVM.setCharacter();
        setContentView(R.layout.encounter_screen);
        Button okButton = findViewById(R.id.encounterButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EncounterScreenActivity.this, MarketScreenActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        if (character == null) {
            //Log.d("EncounterGabe11", "NoEncounter");
            setContentView(R.layout.encounter_screen);
            Button okButton = findViewById(R.id.encounterButton);

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this, MarketScreenActivity.class);
                    startActivityForResult(intent,0);
                }
            });
        } else if (character instanceof Police) {
            //Log.d("EncounterGabe11", "Police");
            showPolicePopupWindow(findViewById(android.R.id.content));
        } else if (character instanceof Pirate) {
            //Log.d("EncounterGabe11", "Pirate");
            showPiratePopupWindow(findViewById(android.R.id.content));
        } else if (character instanceof Trader) {
            //Log.d("EncounterGabe11", "Trader");
            setContentView(R.layout.trader_popup);
            Button okButton = findViewById(R.id.encounterButton);

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(EncounterScreenActivity.this, MarketScreenActivity.class);
                    startActivityForResult(intent,0);
                }
            });
        }
    }

*/
    //---------------------------------------------------------------------------

    private void showPiratePopupWindow(View view) {
        mContext = getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.pirate_popup, null);
        TextView buyTest = popupView.findViewById(R.id.buyButtonText);
        buyTest.setText(encounterScreenVM.popUpBuyStr());

        Button attack_button = popupView.findViewById(R.id.attack_button);
        Button flee_button = popupView.findViewById(R.id.flee_button);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setElevation(5.0f);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        flee_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                easyToast("You ran away");
                popupWindow.dismiss();
            }
        });

        attack_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                encounterScreenVM.playerAttack();
                if (encounterScreenVM.getCharacter().getShip().getHealth() <= 0) {
                    easyToast("You won the battle");
                    popupWindow.dismiss();
                } else if (Player.getHealth() <= 0) {
                    easyToast("You died");
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void showPolicePopupWindow(View view) {
        mContext = getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.police_popup, null);
        TextView buyTest = popupView.findViewById(R.id.buyButtonText);
        buyTest.setText(encounterScreenVM.popUpSellStr());

        Button flee_button = popupView.findViewById(R.id.flee_button);
        Button attack_button = popupView.findViewById(R.id.attack_button);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setElevation(5.0f);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        flee_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                easyToast("You ran away");
                popupWindow.dismiss();
            }
        });

        attack_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                encounterScreenVM.playerAttack();
                if (encounterScreenVM.getCharacter().getShip().getHealth() <= 0) {
                    easyToast("You won the battle");
                    popupWindow.dismiss();
                } else if (Player.getHealth() <= 0) {
                    easyToast("You died");
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void easyToast(String toastMessage) {
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

}
