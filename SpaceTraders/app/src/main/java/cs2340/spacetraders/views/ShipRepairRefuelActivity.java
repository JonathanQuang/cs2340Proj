package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetraders.R;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.RepairRefuelViewModel;

public class ShipRepairRefuelActivity extends AppCompatActivity {
    private FloatingActionButton menuButton;

    private TextView currentFuelNum;
    private TextView maxFuelNum;
    private AppCompatEditText fuelToBuyUserInput;
    private RepairRefuelViewModel repairRefuelVM;
    private Button refuelButton;
    private TextView fuelUnitPrice;

    private TextView currentHealthNum;
    private TextView maxHealthNum;
    private AppCompatEditText healthToBuyUserInput;
    private Button repairButton;
    private TextView healthUnitPrice;

    private TextView currentCredits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_repair_refuel_screen);

        menuButton = findViewById(R.id.menuButton);
        currentFuelNum = findViewById(R.id.currentFuelNum);
        maxFuelNum = findViewById(R.id.maxFuelNum);
        fuelToBuyUserInput = findViewById(R.id.fuelToBuyUserInput);
        refuelButton = findViewById(R.id.refuelButton);
        fuelUnitPrice = findViewById(R.id.fuelUnitPrice);

        currentHealthNum = findViewById(R.id.currentHealthNum);
        maxHealthNum = findViewById(R.id.maxHealthNum);
        healthToBuyUserInput = findViewById(R.id.healthToBuyUserInput);
        repairButton = findViewById(R.id.repairButton);
        healthUnitPrice = findViewById(R.id.healthUnitPrice);


        repairRefuelVM = new RepairRefuelViewModel(Model.getInstance().getPlayer());
        fuelUnitPrice.setText(Integer.toString(repairRefuelVM.getFuelUnitPrice()));
        healthUnitPrice.setText(Integer.toString(repairRefuelVM.getFuelUnitPrice()));


        maxFuelNum.setText(Integer.toString(repairRefuelVM.getMaxFuel()));
        setFuelText(repairRefuelVM.getPlayerFuel());

        //code refuses to compile if textView15 has its name changed both here and the layout file
        currentCredits = findViewById(R.id.textView15);
        updatePlayerCredits();

        refuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fuelPurchased;
                try {
                    Editable fuelEditable = fuelToBuyUserInput.getText();
                    String fuelUserInputStr = fuelEditable.toString();
                    fuelPurchased = Integer.parseInt(fuelUserInputStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String errorText = repairRefuelVM.fuelError(fuelPurchased);
                if (errorText == null) {
                    repairRefuelVM.purchaseFuel(fuelPurchased);
                    setFuelText(repairRefuelVM.getPlayerFuel());
                    updatePlayerCredits();
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }
            }
        });

        maxHealthNum.setText(Double.toString(repairRefuelVM.getMaxHealth()));
        setHealthText(repairRefuelVM.getPlayerHealth());

        repairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int healthPurchased;
                try {
                    Editable healthEditable = healthToBuyUserInput.getText();
                    String healthUserInputStr = healthEditable.toString();
                    healthPurchased = Integer.parseInt(healthUserInputStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String errorText = repairRefuelVM.healthError(healthPurchased);
                if (errorText == null) {
                    repairRefuelVM.repairHealth(healthPurchased);
                    setHealthText(repairRefuelVM.getPlayerHealth());
                    updatePlayerCredits();
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }
            }
        });


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipRepairRefuelActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void setFuelText(int fuel) {
        currentFuelNum.setText(Integer.toString(fuel));
    }

    private void setHealthText(double health) {
        currentHealthNum.setText(Double.toString(health));
    }

    private void updatePlayerCredits() {
        currentCredits.setText(Integer.toString(repairRefuelVM.getPlayerCredits()));
    }
}
