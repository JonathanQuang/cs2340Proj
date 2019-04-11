package cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cs2340.spacetraders.R;
import cs2340.spacetraders.viewmodels.DefaultGameViewModel;

public class GameActivity extends AppCompatActivity {
    private DefaultGameViewModel viewModel;

    /**
     * called when viewing the menu screen
     * @param savedInstanceState the saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

        viewModel = ViewModelProviders.of(this).get(DefaultGameViewModel.class);
    }
}
