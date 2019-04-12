package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs2340.spacetraders.R;

/**
 * RetireActivity controls how the RetireActivity UI behaves
 */
public class RetireActivity extends AppCompatActivity {

    /**
     * called when player is retiring
     * @param savedInstanceState the saved game instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retire_screen);

        FloatingActionButton menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetireActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });    }
}
