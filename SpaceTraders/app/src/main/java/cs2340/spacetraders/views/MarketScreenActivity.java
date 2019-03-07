package cs2340.spacetraders.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Inventory;
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.MarketScreenViewModel;

public class MarketScreenActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private Button mButton;

    private PopupWindow mPopupWindow;

    private TableRow modelRow;
    private TableLayout table;
    private TextView modelRowText;
    private Button modelBuyButton;
    private Button modelSellButton;
    private LinearLayout modelLinearLayout;
    private TextView planetNametext;
    private MarketScreenViewModel marketScreeVM;

    private Inventory playerInventory;
    private PlanetInventory planetInventory;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_screen);
        modelRow = findViewById(R.id.modelRow);
        table = findViewById(R.id.table);
        modelRowText = findViewById(R.id.modelRowText);
        modelBuyButton = findViewById(R.id.modelBuyButton);
        modelSellButton = findViewById(R.id.modelSellButton);
        modelLinearLayout = findViewById(R.id.modelLinearLayout);
        planetNametext = findViewById(R.id.planetName);


        Planet currentPlanet = null;
        while(currentPlanet == null) {
            currentPlanet = Model.getInstance().getGame().getGalaxy().getCurrentPlanet();
        }
        planetInventory = currentPlanet.getInventory();
        playerInventory = Model.getInstance().getPlayer().getInventory();
        planetNametext.setText(currentPlanet.getName().toString());
        marketScreeVM = new MarketScreenViewModel(planetInventory, playerInventory);

        for (Good good: Good.values()) {
            table.addView(generateTableRow(good));
        }
        table.removeView(table.getChildAt(1));
    }

    private View generateTableRow(Good good) {
        //Make TableRow
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(modelRow.getLayoutParams());

        //Make LinearLayout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(modelLinearLayout.getLayoutParams());

        //Make TextView
        TextView textView = new TextView(this);
        textView.setText(good.toString());
        textView.setLayoutParams(modelRowText.getLayoutParams());
        linearLayout.addView(textView);


        //Make Buy Button
        //Make Sell Button
        Button sellButton = makeSellButton(good);
        Button buyButton = makeBuyButton(good, sellButton);

        linearLayout.addView(buyButton);
        linearLayout.addView(sellButton);


        //Add All
        tableRow.addView(linearLayout);
        return tableRow;
    }

    private Button makeBuyButton(Good good, Button sellButton) {
        Button buyButton = new Button(this);
        buyButton.setLayoutParams(modelBuyButton.getLayoutParams());
        buyButton.setText("$" + Integer.toString(planetInventory.getBuyPrice(good)));
        buyButton.setTextSize(10);

        final Good GOOD = good;
        final Button BUY_BUTTON = buyButton;
        final Button SELL_BUTTON = sellButton;
        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                marketScreeVM.setGood(GOOD);
                int i = onButtonShowBuyPopupWindowClick(view);
                Log.d("Market", Integer.toString(playerInventory.getGoodAmount(GOOD)));

                if (planetInventory.getGoodCount(GOOD) == 0) {
                    BUY_BUTTON.setEnabled(false);
                }

                if (playerInventory.getGoodAmount(GOOD) > 0) {
                    SELL_BUTTON.setEnabled(true);
                }
            }
        });

        if (planetInventory.getGoodCount(good) == 0) {
            buyButton.setEnabled(false);
            buyButton.setText("Not Sold");
        }
        return buyButton;
    }

    private Button makeSellButton(Good good) {
        Button sellButton = new Button(this);
        sellButton.setLayoutParams(modelBuyButton.getLayoutParams());
        sellButton.setText("$" + Integer.toString(planetInventory.getSellPrice(good)));
        sellButton.setTextSize(10);

        final Good GOOD = good;
        final Inventory PLAYER_INVENTORY = playerInventory;
        final Button SELL_BUTTON = sellButton;
        sellButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                marketScreeVM.setGood(GOOD);
                onButtonShowSellPopupWindowClick(view);
                if (PLAYER_INVENTORY.getGoodAmount(GOOD) == 0) {
                    SELL_BUTTON.setEnabled(false);
                }
            }
        });

        if (playerInventory.getGoodAmount(good) == 0) {
            sellButton.setEnabled(false);
            if (!planetInventory.canSellGood(good)) {
                sellButton.setText("No Buyers");
            }
        }
        return sellButton;
    }

    private int onButtonShowBuyPopupWindowClick(View view) {
        mContext = getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.buy_popup, null);
        TextView buyTest = popupView.findViewById(R.id.buyButtonText);
        buyTest.setText(marketScreeVM.popUpBuyStr());

        Button cancel_button = popupView.findViewById(R.id.cancel_button);
        Button purchase_button = popupView.findViewById(R.id.purchase_button);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setElevation(5.0f);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        purchase_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                marketScreeVM.buyGood();
            }
        });
        return 0;
    }

    private void onButtonShowSellPopupWindowClick(View view) {
        mContext = getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.sell_popup, null);
        TextView buyTest = popupView.findViewById(R.id.sellButtonText);
        buyTest.setText(marketScreeVM.popUpSellStr());

        Button cancel_button = popupView.findViewById(R.id.cancel_button);
        Button sell_button = popupView.findViewById(R.id.sell_button);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setElevation(5.0f);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        sell_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
    }
}
