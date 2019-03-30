package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.ShipMarketViewModel;

public class ShipDisplayActivity extends AppCompatActivity {

    private ShipMarketViewModel shipmarketVM;
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
    private ImageView shipImage;

    private FloatingActionButton menuButton;
    private Button buyButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_display);

        menuButton = findViewById(R.id.menuButton);
        buyButton = findViewById(R.id.buyButton);
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
        shipImage = findViewById(R.id.shipImage);
        shipmarketVM = new ShipMarketViewModel(Model.getInstance().getPlayer());
        attachInfoEventListener(shipTypeVar);
        if (shipTypeVar.equals(ShipType.Gnat)) {
            shipImage.setImageResource(R.drawable.gnat_l);
        } else if (shipTypeVar.equals(ShipType.Flea)) {
            shipImage.setImageResource(R.drawable.flea_l);
        } else if (shipTypeVar.equals(ShipType.Beetle)) {
            shipImage.setImageResource(R.drawable.beetle_l);
        } else if (shipTypeVar.equals(ShipType.Firefly)) {
            shipImage.setImageResource(R.drawable.firefly_l);
        } else if (shipTypeVar.equals(ShipType.Bumblebee)) {
            shipImage.setImageResource(R.drawable.bumblebee_l);
        } else if (shipTypeVar.equals(ShipType.Grasshopper)) {
            shipImage.setImageResource(R.drawable.grasshopper_l);
        } else if (shipTypeVar.equals(ShipType.Hornet)) {
            shipImage.setImageResource(R.drawable.hornet_l);
        } else if (shipTypeVar.equals(ShipType.Mosquito)) {
            shipImage.setImageResource(R.drawable.mosquito_l);
        } else if (shipTypeVar.equals(ShipType.Termite)) {
            shipImage.setImageResource(R.drawable.termite_l);
        }

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
        nameText.setText("Name:" + name);
        sizeText.setText("Size:" + size);
        cargoBayText.setText("Cargo Bays: " + cargoBays);
        rangeText.setText("Range: " + range);
        weaponSlotsText.setText("Weapon slots: " + weaponSlots);
        shieldSlotsText.setText("Shield Slots: " + shieldSlots);
        gadgetSlotsText.setText("Gadget Slots: " + gadgetSlots);
        crewQuartersText.setText("Crew Quarters: " + crewQuarters);
    }
}
