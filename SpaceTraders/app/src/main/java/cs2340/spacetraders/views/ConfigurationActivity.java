package cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Difficulty;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.ConfigurationViewModel;

/**
 * Creates the configuration screen
 */
public final class ConfigurationActivity extends AppCompatActivity {

    private TextView engineerView;
    private TextView fighterView;
    private TextView traderView;
    private TextView pilotView;
    private Spinner difficultySpinner;
    private ConfigurationViewModel viewModel;
    private int pointsLeft;
    private MediaPlayer mediaPlayer = Model.getMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_player);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.loading_screen);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        pointsLeft = viewModel.STARTING_POINTS;

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

        difficultySpinner = findViewById(R.id.difficultySpinner);
        ArrayAdapter<Difficulty> adapter_standing = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Difficulty.values());
        adapter_standing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter_standing);

        Button playButton = findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((TextInputEditText)
                        findViewById(R.id.playerNameInput)).getText().toString();
                int engineerStat = Integer.parseInt(engineerView.getText().toString());
                int fighterStat = Integer.parseInt(fighterView.getText().toString());
                int traderStat = Integer.parseInt(traderView.getText().toString());
                int pilotStat = Integer.parseInt(pilotView.getText().toString());
                Difficulty difficulty = (Difficulty) difficultySpinner.getSelectedItem();

                if (viewModel.onOkay(name, engineerStat, fighterStat, traderStat, pilotStat,
                        difficulty, pointsLeft)) {
                    Intent intent = new Intent(ConfigurationActivity.this,
                            MarketScreenActivity.class);
                    mediaPlayer.stop();
                    startActivityForResult(intent,0);
                }
            }
        });
    }

    /**
     * Increments or Decrements counter of class statistic when pressing corresponding button
     * @param decrement the decrementing button for the class
     * @param increment the incrementing button for the class
     * @param view the counter view for the class
     */
    private void setupIncrDecrButtons(Button decrement, Button increment, TextView view) {
        final TextView viewFinal = view;
        final TextView pointsLeftFinal = findViewById(R.id.pointsLeft);

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int points = Integer.parseInt(viewFinal.getText().toString());
                if (points == 0) {return;}
                viewFinal.setText(String.valueOf(points - 1));
                String put = "Skill Points Available: " + String.valueOf(++pointsLeft);
                pointsLeftFinal.setText(put);
            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int points = Integer.parseInt(viewFinal.getText().toString());
                if (pointsLeft == 0) {return;}
                viewFinal.setText(String.valueOf(points + 1));
                String put = "Skill Points Available: " + String.valueOf(--pointsLeft);
                pointsLeftFinal.setText(put);
            }
        });
    }
}
