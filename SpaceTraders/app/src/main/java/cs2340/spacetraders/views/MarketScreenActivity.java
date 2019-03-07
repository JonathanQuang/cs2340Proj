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
import cs2340.spacetraders.entity.Market.Good;
import cs2340.spacetraders.entity.Market.PlanetInventory;
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
        PlanetInventory planetInventory = currentPlanet.getInventory();
        marketScreeVM = new MarketScreenViewModel(planetInventory);

        planetNametext.setText(currentPlanet.getName().toString());
        Good[] goodsList = Good.values();

        int rowCount = 1;
        for (Good good: goodsList) {
            rowCount++;
            table.addView(generateTableRow(good.toString(),
                    "$" + Integer.toString(planetInventory.getBuyPrice(good)),
                    "$" + Integer.toString(planetInventory.getSellPrice(good))));

            if (planetInventory.getGoodCount(good) == 0) {
//                System.out.println("Items 0 for " + good.toString());
                TableRow row = (TableRow) table.getChildAt(rowCount);
                LinearLayout linear = (LinearLayout) row.getChildAt(0);
                Button but = (Button) linear.getChildAt(1);
                but.setEnabled(false);
            }
        }
        table.removeView(table.getChildAt(1));

        /*
        final List<String> strList = new ArrayList<String>(Arrays.asList(testArrayString));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList);
        resourceCol.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        */

//        table.addView(generateTableRow("Water", "15", "13"));

    }

    private View generateTableRow(String resourceName, String buyText, String sellText) {
        TableRow retRow = new TableRow(this);
        //TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        retRow.setLayoutParams(modelRow.getLayoutParams());
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(modelLinearLayout.getLayoutParams());
        TextView tv = new TextView(this);
        tv.setText(resourceName);
        tv.setLayoutParams(modelRowText.getLayoutParams());
        ll.addView(tv);
        Button buyButton = new Button(this);
        buyButton.setLayoutParams(modelBuyButton.getLayoutParams());
        buyButton.setText(buyText);
        buyButton.setTextSize(10);

        final String storageString = new String(resourceName);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marketScreeVM.setGoodViaString(storageString);
                onButtonShowBuyPopupWindowClick(view);
            }
        });
        ll.addView(buyButton);
        Button sellButton = new Button(this);
        sellButton.setTextSize(10);
        sellButton.setLayoutParams(modelSellButton.getLayoutParams());
        sellButton.setText(sellText);
        ll.addView(sellButton);

        retRow.addView(ll);
        return retRow;
    }

    public View getTableLayoutCell(TableLayout layout, int rowNo, int columnNo) {
        if (rowNo >= layout.getChildCount()) return null;
        TableRow row = (TableRow) layout.getChildAt(rowNo);

        if (columnNo >= row.getChildCount()) return null;
        return row.getChildAt(columnNo);

    }

    private void onButtonShowBuyPopupWindowClick(View view) {

        mContext = getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.buy_popup, null);
        TextView buyTest = popupView.findViewById(R.id.buyButtonText);
        buyTest.setText(marketScreeVM.retBuyStr());


        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;



        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setElevation(5.0f);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true; }});
            Log.d("buy experiment","button pressed");
        }



}
