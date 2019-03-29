package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.ShipMarketViewModel;

class ShipDisplayActivity extends AppCompatActivity {

    private ShipMarketViewModel shipmarketVM;
    private Button modelBuyButton;
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
    private static ShipType shipTypeVar;

    private Button menuButton;
    private Button buyButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuButton = findViewById(R.id.menuButton);
        modelBuyButton = findViewById(R.id.modelBuyButton);
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
        attachInfoEventListener(shipTypeVar);

        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipDisplayActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //buying mechanism
            }
        });
    }

    protected static void setShipType(ShipType shipType) {
        shipTypeVar = shipType;
    }

    private void attachInfoEventListener(final ShipType shipType) {
        updateInfoSection(shipType.getShipType(), shipType.getShipSize(), shipType.getCargoCapacity(), shipType.getRange(), shipType.getWeaponSlots(),
                        shipType.getShieldSlots(), shipType.getGadgetSlots(), shipType.getCrewQuarters());
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
