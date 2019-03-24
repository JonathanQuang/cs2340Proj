package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs2340.spacetraders.R;

public class TargetSystemActivity extends AppCompatActivity {
    private FloatingActionButton menuButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target_system);

        menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TargetSystemActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });    }
}
