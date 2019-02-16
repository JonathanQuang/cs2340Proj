package cs2340.spacetraders;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetraders.entity.Difficulty;

public class MainActivity extends AppCompatActivity {

    public TextView engineerView, fighterView, traderView, pilotView;
    public int pointsLeft = 16;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_player);

        Button engineerDecr = findViewById(R.id.engineerDecrement);
        Button engineerIncr = findViewById(R.id.engineerIncrement);
        engineerView = findViewById(R.id.engineerStat);
        setupIncrDecrButtons(engineerDecr, engineerIncr, engineerView);

        Button fighterDecr = findViewById(R.id.fighterDecrement);
        Button fighterIncr = findViewById(R.id.fighterIncrement);
        fighterView = findViewById(R.id.fighterStat);
        setupIncrDecrButtons(fighterDecr, fighterIncr, fighterView);

        Button traderDecr = findViewById(R.id.traderDecrement);
        Button traderIncr = findViewById(R.id.traderIncrement);
        traderView = findViewById(R.id.traderStat);
        setupIncrDecrButtons(traderDecr, traderIncr, traderView);

        Button pilotDecr = findViewById(R.id.pilotDecrement);
        Button pilotIncr = findViewById(R.id.pilotIncrement);
        pilotView = findViewById(R.id.pilotStat);
        setupIncrDecrButtons(pilotDecr, pilotIncr, pilotView);

        Spinner difficultySpinner = findViewById(R.id.difficultySpinner);
        ArrayAdapter<Difficulty> adapter_standing = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Difficulty.values());
        adapter_standing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter_standing);
    }

    public void onPlayPressed(View view) {
        Log.d("Config", "Play Button Pressed");
        TextInputEditText nameInput = findViewById(R.id.playerNameInput);

        String name = nameInput.getText().toString();
        if (nameInput.getText().toString().length() == 0) {
            Log.d("Config", "Name is Empty");
            Toast.makeText(this, "Name is Empty", Toast.LENGTH_LONG).show();
            return;
        }

        int engineerStat, fighterStat, traderStat, pilotStat;
        try {
            engineerStat = Integer.parseInt(engineerView.getText().toString());
            fighterStat = Integer.parseInt(fighterView.getText().toString());
            traderStat = Integer.parseInt(traderView.getText().toString());
            pilotStat = Integer.parseInt(pilotView.getText().toString());
        } catch (Exception E) {
            Log.d("Config", "Invalid Skill Point Inputs");
            Toast.makeText(this, "Invalid Skill Points Input", Toast.LENGTH_LONG).show();
            return;
        }

        if (pointsLeft != 0) {
            Log.d("Config", String.valueOf(pointsLeft) + " Skill Point Remaining");
            Toast.makeText(this, String.valueOf(pointsLeft) + " Skill Point Remaining", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d("Config", "Start Game!");
        Toast.makeText(this, "Start Game!", Toast.LENGTH_LONG).show();
    }


    public void setupIncrDecrButtons(Button decrement, Button increment, TextView view) {
        final TextView viewFinal = view;
        final TextView pointsLeftFinal = findViewById(R.id.PointsLeft);

        decrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int points = Integer.parseInt(viewFinal.getText().toString());
                if (points == 0) {return;}
                viewFinal.setText(String.valueOf(points - 1));
                pointsLeftFinal.setText("Skill Points Available: " + String.valueOf(++pointsLeft));
            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int points = Integer.parseInt(viewFinal.getText().toString());
                if (pointsLeft == 0) {return;}
                viewFinal.setText(String.valueOf(points + 1));
                pointsLeftFinal.setText("Skill Points Available: " + String.valueOf(--pointsLeft));
            }
        });
    }
}
