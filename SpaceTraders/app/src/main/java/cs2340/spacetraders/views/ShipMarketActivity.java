package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.ShipMarketViewModel;

public class ShipMarketActivity extends AppCompatActivity {

    private FloatingActionButton menuButton;

    private TableRow modelRow;
    private TableLayout table;
    private Button modelBuyButton;
    private TextView modelShiptext;
    private Button modelInfoButton;
    private TextView modelCreditsText;

    private TextView nameText;
    private TextView sizeText;
    private TextView cargoBayText;
    private TextView rangeText;
    private TextView hullStrengthText;
    private TextView weaponSlotsText;
    private TextView shieldSlotsText;
    private TextView gadgetSlotsText;
    private TextView crewQuartersText;

    private ShipMarketViewModel shipmarketVM;

    public ShipMarketActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_market_screen);

        menuButton = findViewById(R.id.menuButton);
        modelRow = findViewById(R.id.modelRow);
        table = findViewById(R.id.table);
        modelBuyButton = findViewById(R.id.modelBuyButton);
        modelShiptext = findViewById(R.id.modelShipText);
        modelInfoButton = findViewById(R.id.modelInfoButton);
        modelCreditsText = findViewById(R.id.modelCreditsText);
        nameText = findViewById(R.id.nameText);
        sizeText = findViewById(R.id.sizeText);
        cargoBayText = findViewById(R.id.cargoBayText);
        rangeText = findViewById(R.id.rangeText);
        hullStrengthText = findViewById(R.id.hullStrengthText);
        weaponSlotsText = findViewById(R.id.weaponSlotsText);
        shieldSlotsText = findViewById(R.id.shieldSlotsText);
        gadgetSlotsText = findViewById(R.id.gadgetSlotsText);
        crewQuartersText = findViewById(R.id.crewQuartersText);

        shipmarketVM = new ShipMarketViewModel(Model.getInstance().getPlayer());

        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });

        table.removeViewAt(0);
        generateTable();
    }

    private void generateTable() {
        for (ShipType st : ShipType.values()) {
            table.addView(generateTableRow(st));
        }
    }

    private View generateTableRow(ShipType shipType) {
        //Copy the model row
        TableRow tablerow = new TableRow(this);
        tablerow.setLayoutParams(modelRow.getLayoutParams());

        //Copy the model buy button
        Button buyButton = new Button(this);
        buyButton.setLayoutParams(modelBuyButton.getLayoutParams());
        attachBuyingEventListener(buyButton, shipType);
        buyButton.setText("BUY");

        //Copy the ship text
        TextView shipText = new TextView(this);
        shipText.setLayoutParams(modelShiptext.getLayoutParams());
        shipText.setText(shipType.getShipType());

        //Copy the info button
        Button infoButton = new Button(this);
        infoButton.setLayoutParams(modelInfoButton.getLayoutParams());
        infoButton.setText("INFO");
        attachInfoEventListener(infoButton, shipType);

        //Copy the credits text
        TextView creditsText = new TextView(this);
        creditsText.setLayoutParams(modelCreditsText.getLayoutParams());
        int priceDiff = shipmarketVM.getPriceDiffForShipType(shipType);
        String negativeSign = (priceDiff < 0) ? "-" : "";
        creditsText.setText(negativeSign + Integer.toString(priceDiff));

        //disable buy button if the player already owns the ship
        if (shipmarketVM.isSameShip(shipType)) {
            buyButton.setEnabled(false);
        }

        tablerow.addView(buyButton);
        tablerow.addView(shipText);
        tablerow.addView(infoButton);
        tablerow.addView(creditsText);

        return tablerow;
    }

    private void attachBuyingEventListener(Button buyButton, final ShipType shipType) {
        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), shipmarketVM.playerCanTradeMessage(shipType), Toast.LENGTH_SHORT).show();
                generateTable();
                //remove the old table
                for (int i = 0; i < shipmarketVM.getShipMarketDiffMapSize(); i++) {
                        table.removeViewAt(0);
                }
            }
        });
    }

    private void attachInfoEventListener(Button infoButton, final ShipType shipType) {
        infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateInfoSection(shipType.getShipType(), shipType.getShipSize(), shipType.getCargoCapacity(), shipType.getRange(), shipType.getWeaponSlots(),
                        shipType.getShieldSlots(), shipType.getGadgetSlots(), shipType.getCrewQuarters());
            }
        });
    }

    private void updateInfoSection(String name, String size, int cargoBays, int range, int weaponSlots,
                                   int shieldSlots, int gadgetSlots, int crewQuarters) {
        nameText.setText("Name :" + name);
        sizeText.setText("Size :" + size);
        cargoBayText.setText("Cargo Bays: " + cargoBays);
        rangeText.setText("Range: " + range);
        weaponSlotsText.setText("Weapon slots: " + weaponSlots);
        shieldSlotsText.setText("Shield Slots: " + shieldSlots);
        gadgetSlotsText.setText("Gadget Slots: " + gadgetSlots);
        crewQuartersText.setText("Crew Quarters: " + crewQuarters);
    }
}
