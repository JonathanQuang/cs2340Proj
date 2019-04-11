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
import cs2340.spacetraders.viewmodels.ShipDisplayViewModel;

public class ShipDisplayActivity extends AppCompatActivity {

    private ShipDisplayViewModel shipmarketVM;
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

    /**
     * called when the player is viewing a particular ship
     * @param savedInstanceState the saved state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_display);

        FloatingActionButton menuButton = findViewById(R.id.menuButton);
        Button buyButton = findViewById(R.id.buyButton);
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
        ImageView shipImage = findViewById(R.id.shipImage);

        shipmarketVM = new ShipDisplayViewModel(Model.getInstance().getPlayer());
        shipImage.setImageResource(getShipDrawables()[shipTypeVar.ordinal()]);
        setupShipInfo(shipTypeVar);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipDisplayActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buying mechanism
            }
        });
    }

    /**
     * to set the type of ship
     * @param shipType the ship type
     */
    public static void setShipType(ShipType shipType) {
        shipTypeVar = shipType;
    }

    /**
     * to set the info of the ship
     * @param shipType ship type
     */
    private void setupShipInfo(ShipType shipType) {
        nameText.setText("Name: " + shipType.getShipType());
        sizeText.setText("Size: " + shipType.getShipSize());
        cargoBayText.setText("Cargo Bays: " + shipType.getCargoCapacity());
        rangeText.setText("Range: " + shipType.getRange());
        hullStrengthText.setText("Hull Strength" + shipType.getHullStrength());
        weaponSlotsText.setText("Weapon slots: " + shipType.getWeaponSlots());
        shieldSlotsText.setText("Shield Slots: " + shipType.getShieldSlots());
        gadgetSlotsText.setText("Gadget Slots: " + shipType.getGadgetSlots());
        crewQuartersText.setText("Crew Quarters: " + shipType.getCrewQuarters());
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
