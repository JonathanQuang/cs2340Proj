package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cs2340.spacetraders.R;

public class ShipyardActivity extends AppCompatActivity {
    private FloatingActionButton menuButton;
    private Button shipmarketButton;

    /**
     * called when viewing the shipyard
     * @param savedInstanceState
     */
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

        /*
        shipmarketButton = findViewById(R.id.goToShipMarket);

        shipmarketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipyardActivity.this, ShipMarketActivity.class);
                startActivityForResult(intent, 0);
            }
        }); */
    }
}
