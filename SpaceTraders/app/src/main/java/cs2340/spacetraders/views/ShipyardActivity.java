package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cs2340.spacetraders.R;

public class ShipyardActivity extends AppCompatActivity {
<<<<<<< Updated upstream
    private FloatingActionButton menuButton;
    private Button shipmarketButton;
=======

    private TableLayout weaponTable;
    private TableRow modelWeaponRow;
    private Button modelWeaponBuyButton;
    private Button modelWeaponInfoButton;
    private TextView modelWeaponName;
    private Button modelSellButton;

    private TextView buyNum;
    private TextView sellNum;
    private TextView powerNum;
    private TextView chargeNum;

    private TextView creditCounter;
    private TextView equippedWeapons;

    private ShipyardViewModel shipyardVM;
    private final Model model = Model.getInstance();
    private RelativePosition center;
>>>>>>> Stashed changes

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
