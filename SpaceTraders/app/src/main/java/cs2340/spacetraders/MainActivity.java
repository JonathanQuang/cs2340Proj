package cs2340.spacetraders;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_player);
    }

    public void onPlayPressed(View view) {
        Log.d("Config", "Okay Button Pressed");

        TextInputEditText nameInput = findViewById(R.id.playerNameInput);
        EditText engineerInput = findViewById(R.id.engineerStat);
        EditText fighterInput = findViewById(R.id.fighterStat);
        EditText traderInput = findViewById(R.id.traderStat);
        EditText pilotInput = findViewById(R.id.pilotStat);


        String name = nameInput.getText().toString();
        if (nameInput.getText().toString().length() == 0) {
            Log.d("Config", "Name is Empty");
            Toast.makeText(getApplicationContext(), "Name is Empty", Toast.LENGTH_LONG).show();
            return;
        }

        int engineerStat, fighterStat, traderStat, pilotStat;
        try {
            engineerStat = Integer.parseInt(engineerInput.getText().toString());
            fighterStat = Integer.parseInt(fighterInput.getText().toString());
            traderStat = Integer.parseInt(traderInput.getText().toString());
            pilotStat = Integer.parseInt(pilotInput.getText().toString());
        } catch (Exception E) {
            Log.d("Config", "Invalid Skill Point Inputs");
            Toast.makeText(getApplicationContext(), "Invalid Skill Points Input", Toast.LENGTH_LONG).show();
            return;
        }

        if (engineerStat + fighterStat + traderStat + pilotStat != 16) {
            Log.d("Config", "Skill Point aren't equal to 16");
            Toast.makeText(this, "Skill Points aren't equal to 16", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d("Config", "Start Game!");
        Toast.makeText(this, "Start Game!", Toast.LENGTH_LONG).show();
    }
}
