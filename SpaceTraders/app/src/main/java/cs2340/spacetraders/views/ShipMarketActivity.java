package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.ShipType;

public class ShipMarketActivity extends AppCompatActivity {

    private FloatingActionButton menuButton;
    private ImageButton gnatButton;
    private ImageButton fleaButton;
    private ImageButton fireflyButton;
    private ImageButton bumblebeeButton;
    private ImageButton hornetButton;
    private ImageButton mosquitoButton;
    private ImageButton grasshopperButton;
    private ImageButton termiteButton;
    private ImageButton beetleButton;

    public ShipMarketActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_market_screen);

        menuButton = findViewById(R.id.menuButton);
        gnatButton = findViewById(R.id.gnatButton);
        fleaButton = findViewById(R.id.fleaButton);
        fireflyButton = findViewById(R.id.fireflyButton);
        grasshopperButton = findViewById(R.id.grasshopperButton);
        termiteButton = findViewById(R.id.termiteButton);
        hornetButton = findViewById(R.id.hornetButton);
        mosquitoButton = findViewById(R.id.mosquitoButton);
        beetleButton = findViewById(R.id.beetleButton);
        bumblebeeButton = findViewById(R.id.bumblebeeButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });

        fleaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Flea);
                startActivityForResult(intent, 0);
            }
        });

        gnatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Gnat);
                startActivityForResult(intent, 0);
            }
        });

        bumblebeeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Bumblebee);
                startActivityForResult(intent, 0);
            }
        });

        hornetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Hornet);
                startActivityForResult(intent, 0);
            }
        });

        grasshopperButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Grasshopper);
                startActivityForResult(intent, 0);
            }
        });

        mosquitoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Mosquito);
                startActivityForResult(intent, 0);
            }
        });

        beetleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Beetle);
                startActivityForResult(intent, 0);
            }
        });

        termiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Termite);
                startActivityForResult(intent, 0);
            }
        });

        fireflyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(ShipType.Firefly);
                startActivityForResult(intent, 0);
            }
        });
    }
}

