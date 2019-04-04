package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.ShipType;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.ShipyardViewModel;

public class ShipyardActivity extends AppCompatActivity {
    private FloatingActionButton menuButton;
    private Button shipmarketButton;

    private TableLayout weaponTable;
    private TableRow modelWeaponRow;
    private Button modelWeaponBuyButton;
    private Button modelWeaponInfoButton;
    private TextView modelWeaponName;

    private TextView buyNum;
    private TextView sellNum;
    private TextView powerNum;
    private TextView chargeNum;

    private TextView creditCounter;
    private TextView equippedWeapons;

    ShipyardViewModel shipyardVM;

    public ShipyardActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipyard_screen);

        menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipyardActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });

        weaponTable = findViewById(R.id.weaponTable);
        modelWeaponRow = findViewById(R.id.modelWeaponRow);
        modelWeaponBuyButton = findViewById(R.id.modelWeaponBuyButton);
        modelWeaponInfoButton = findViewById(R.id.modelWeaponInfoButton);
        modelWeaponName = findViewById(R.id.modelWeaponName);

        buyNum = findViewById(R.id.buyNum);
        sellNum = findViewById(R.id.sellNum);
        powerNum = findViewById(R.id.powerNum);
        chargeNum = findViewById(R.id.chargeNum);

        creditCounter = findViewById(R.id.creditCounter);
        equippedWeapons = findViewById(R.id.equippedWeapons);
        shipyardVM = new ShipyardViewModel(Model.getInstance().getPlayer());
        updateCreditCounter();

        weaponTable.removeViewAt(0);
        generateWeaponTable();
        updateEquippedWeaponsList();

    }

    private void generateWeaponTable() {
        for (WeaponTypes wep: WeaponTypes.values()) {
            weaponTable.addView(makeWeaponTableRow(wep));
        }
    }

    private TableRow makeWeaponTableRow(WeaponTypes weapon) {
        TableRow tablerow = new TableRow(this);
        tablerow.setLayoutParams(modelWeaponRow.getLayoutParams());

        //Copy the weapon buy button
        Button weaponBuyButton = new Button(this);
        weaponBuyButton.setLayoutParams(modelWeaponBuyButton.getLayoutParams());
        attachWeaponBuyingEventListener(weaponBuyButton, weapon);
        weaponBuyButton.setText("BUY");

        //Copy the info button
        Button weaponInfoButton = new Button(this);
        weaponInfoButton.setLayoutParams(modelWeaponInfoButton.getLayoutParams());
        weaponInfoButton.setText("INFO");
        attachWeaponInfoEventListener(weaponInfoButton, weapon);

        TextView weaponName = new TextView(this);
        weaponName.setLayoutParams(modelWeaponName.getLayoutParams());
        weaponName.setText(weapon.getEquipName());

        tablerow.addView(weaponBuyButton);
        tablerow.addView(weaponInfoButton);
        tablerow.addView(weaponName);

        return tablerow;

    }

    private void attachWeaponBuyingEventListener(Button weaponBuyButton, final WeaponTypes weapon) {

        weaponBuyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String purchaseErrorString = shipyardVM.weaponBuyError(weapon);
                if (purchaseErrorString == null) {
                    shipyardVM.buyWeapon(weapon);
                    updateCreditCounter();
                    updateEquippedWeaponsList();
                } else {
                    Toast.makeText(ShipyardActivity.this, purchaseErrorString, Toast.LENGTH_SHORT).show();
                }
                updateInfo(weapon);
            }
        });

        return;
    }

    private void attachWeaponInfoEventListener(Button modelWeaponInfoButton, final WeaponTypes weapon) {




        modelWeaponInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateInfo(weapon);
            }
        });

    }

    private void updateInfo(final WeaponTypes weapon) {
        final int buyPrice = weapon.getBuyprice();
        final int sellPrice = weapon.getSellprice();
        final int power = weapon.getPower();
        final int charge = weapon.getCharge();

        buyNum.setText(Integer.toString(buyPrice));
        sellNum.setText(Integer.toString(sellPrice));

        if (power < 0) {
            powerNum.setText("NA");
        } else {
            powerNum.setText(Integer.toString(power));
        }
        if (charge < 0) {
            chargeNum.setText("NA");
        } else {
            chargeNum.setText(Integer.toString(charge));
        }
    }

    private void updateCreditCounter() {
        creditCounter.setText(Integer.toString(shipyardVM.getPlayerCredits()));
    }

    private void updateEquippedWeaponsList() {
        equippedWeapons.setText(shipyardVM.getEquippedWeaponsString());
    }


}
