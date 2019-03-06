package cs2340.spacetraders.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import cs2340.spacetraders.R;

public class MarketScreenActivity extends AppCompatActivity {

    private TableRow modelRow;
    private TableLayout table;
    private TextView modelRowText;
    private Button modelBuyButton;
    private Button modelSellButton;
    private LinearLayout modelLinearLayout;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_screen);
        modelRow = findViewById(R.id.modelRow);
        table = findViewById(R.id.table);
        modelRowText = findViewById(R.id.modelRowText);
        modelBuyButton = findViewById(R.id.modelBuyButton);
        modelSellButton = findViewById(R.id.modelSellButton);
        modelLinearLayout = findViewById(R.id.modelLinearLayout);

        String[] testArrayString = new String[] {
                "Water",
                "Furs",
                "Food",
                "Ores",
                "Games",
                "Firearms",
                "Medicine",
                "Machines",
                "Narcotics",
                "Robots"
        };

        for (String x: testArrayString) {
            table.addView(generateTableRow(x, "$15 stored: 100", "13$ stored:100"));
        }

        table.removeView(modelRow);

        /*
        final List<String> strList = new ArrayList<String>(Arrays.asList(testArrayString));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList);
        resourceCol.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        */

        //table.addView(generateTableRow("Water", "15", "13"));

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
        ll.addView(buyButton);
        Button sellButton = new Button(this);
        sellButton.setTextSize(10);
        sellButton.setLayoutParams(modelSellButton.getLayoutParams());
        sellButton.setText(sellText);
        ll.addView(sellButton);


        retRow.addView(ll);
        return retRow;
    }

}
