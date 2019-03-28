package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cs2340.spacetraders.R;

public class MenuScreen extends AppCompatActivity {
    private Button shipMarketButton;
    private Button shipyardButton;
    private Button shipRepairButton;
    private Button hireCrewButton;
    private Button starMapButton;
    private Button targetSystemButton;
    private Button planetMarketButton;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);
        shipMarketButton = findViewById(R.id.shipMarketButton);
        shipyardButton = findViewById(R.id.shipyardButton);
        shipRepairButton = findViewById(R.id.shipRepairButton);
        hireCrewButton = findViewById(R.id.hireCrewButton);
        starMapButton = findViewById(R.id.starMapButton);
        targetSystemButton = findViewById(R.id.targetSystemButton);
        planetMarketButton = findViewById(R.id.planetMarketButton);

        shipMarketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, ShipMarketActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        shipyardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, ShipyardActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        shipRepairButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, ShipRepairActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        hireCrewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, HireCrewActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        starMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, GalaxyMapActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        targetSystemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, TargetSystemActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        planetMarketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, MarketScreenActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
