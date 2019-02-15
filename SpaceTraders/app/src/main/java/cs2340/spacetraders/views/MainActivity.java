package cs2340.spacetraders.views;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Difficulty;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_player);

        Spinner difficultySpinner = findViewById(R.id.difficultySpinner);

        ArrayAdapter<Difficulty> adapter_standing = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Difficulty.values());
        adapter_standing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter_standing);

    }

}
