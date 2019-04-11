package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs2340.spacetraders.R;

public class ShipRepairActivity extends AppCompatActivity {
    private FloatingActionButton menuButton;

    /**
     * called when repairing the ship
     * @param savedInstanceState the saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_repair_screen);

        menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipRepairActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
