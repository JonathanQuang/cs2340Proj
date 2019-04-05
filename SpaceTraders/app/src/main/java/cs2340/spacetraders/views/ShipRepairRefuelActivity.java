package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.RepairRefuelViewModel;

public class ShipRepairRefuelActivity extends AppCompatActivity {
    private FloatingActionButton menuButton;
    private TextView currentFuelNum;
    private TextView maxFuelNum;
    private AppCompatEditText fuelToBuyUserInput;
    private RepairRefuelViewModel repairRefuelVM;
    private Button refuelButton;

    private TextView currentHealthNum;
    private TextView maxHealthNum;
    private AppCompatEditText healthToBuyUserInput;
    private Button repairButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_repair_refuel_screen);

        menuButton = findViewById(R.id.menuButton);
        currentFuelNum = findViewById(R.id.currentFuelNum);
        maxFuelNum = findViewById(R.id.maxFuelNum);
        fuelToBuyUserInput = findViewById(R.id.fuelToBuyUserInput);
        refuelButton = findViewById(R.id.refuelButton);

        currentHealthNum = findViewById(R.id.currentHealthNum);
        maxHealthNum = findViewById(R.id.maxHealthNum);
        healthToBuyUserInput = findViewById(R.id.healthToBuyUserInput);
        repairButton = findViewById(R.id.repairButton);


        repairRefuelVM = new RepairRefuelViewModel(Model.getInstance().getPlayer());

        maxFuelNum.setText(Integer.toString(repairRefuelVM.getMaxFuel()));
        setFuelText(repairRefuelVM.getPlayerFuel());

        refuelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Editable fuelEditable = fuelToBuyUserInput.getText();

                if (fuelEditable == null) {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }
                String fuelUserInputStr = fuelEditable.toString();
                int fuelPurchased;
                try {
                    fuelPurchased = Integer.parseInt(fuelUserInputStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String errorText = repairRefuelVM.fuelError(fuelPurchased);
                if (errorText == null) {
                    repairRefuelVM.purchaseFuel(fuelPurchased);
                    setFuelText(repairRefuelVM.getPlayerFuel());
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }
            }
        });

        maxHealthNum.setText(Double.toString(repairRefuelVM.getMaxHealth()));
        setHealthText(repairRefuelVM.getPlayerHealth());

        repairButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Editable healthEditable = healthToBuyUserInput.getText();

                if (healthEditable == null) {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }
                String healthUserInputStr = healthEditable.toString();
                int healthPurchased;
                try {
                    healthPurchased = Integer.parseInt(healthUserInputStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String errorText = repairRefuelVM.healthError(healthPurchased);
                if (errorText == null) {
                    repairRefuelVM.repairHealth(healthPurchased);
                    setHealthText(repairRefuelVM.getPlayerHealth());
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }
            }
        });


        menuButton.setOnClickListener(new View.OnClickListener() {
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
}
