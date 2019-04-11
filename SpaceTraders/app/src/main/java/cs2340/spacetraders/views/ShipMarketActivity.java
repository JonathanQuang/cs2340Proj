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
    /**
     * created when viewing all of the ships
     * @param savedInstanceState the saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_market_screen);

        FloatingActionButton menuButton = findViewById(R.id.menuButton);
        ImageButton gnatButton = findViewById(R.id.gnatButton);
        ImageButton fleaButton = findViewById(R.id.fleaButton);
        ImageButton fireflyButton = findViewById(R.id.fireflyButton);
        ImageButton grasshopperButton = findViewById(R.id.grasshopperButton);
        ImageButton termiteButton = findViewById(R.id.termiteButton);
        ImageButton hornetButton = findViewById(R.id.hornetButton);
        ImageButton mosquitoButton = findViewById(R.id.mosquitoButton);
        ImageButton beetleButton = findViewById(R.id.beetleButton);
        ImageButton bumblebeeButton = findViewById(R.id.bumblebeeButton);

        setupShipButton(fleaButton, ShipType.Flea);
        setupShipButton(gnatButton, ShipType.Gnat);
        setupShipButton(bumblebeeButton, ShipType.Bumblebee);
        setupShipButton(hornetButton, ShipType.Hornet);
        setupShipButton(grasshopperButton, ShipType.Grasshopper);
        setupShipButton(mosquitoButton, ShipType.Mosquito);
        setupShipButton(beetleButton, ShipType.Beetle);
        setupShipButton(mosquitoButton, ShipType.Mosquito);
        setupShipButton(termiteButton, ShipType.Termite);
        setupShipButton(fireflyButton, ShipType.Firefly);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * to set-up the ship button
     * @param button the button in consideration
     * @param shipType ship type in consideration
     */
    private void setupShipButton(ImageButton button, final ShipType shipType) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipMarketActivity.this, ShipDisplayActivity.class);
                ShipDisplayActivity.setShipType(shipType);
                startActivityForResult(intent, 0);
            }
        });
    }
}
