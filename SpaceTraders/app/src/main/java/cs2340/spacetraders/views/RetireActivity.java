package cs2340.spacetraders.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Game;
import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.model.Model;

/**
 * RetireActivity controls how the RetireActivity UI behaves
 */
public class RetireActivity extends AppCompatActivity {

    Player player;
    Game game;

    /**
     * called when player is retiring
     *
     * @param savedInstanceState the saved game instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retire_screen);

        TextView retireNameView = findViewById(R.id.retire_name);
        TextView retirePointsView = findViewById(R.id.retire_points);
        TextView retireStatsView = findViewById(R.id.retire_stats);
        Button newGameButtonView = findViewById(R.id.retire_new_game);
        TextView retireThanks = findViewById(R.id.retire_thanks);

        player = Model.getInstance().getPlayer();
        game = Model.getInstance().getGame();
        String name = (player.getName().isEmpty() ? "The Beast" : player.getName());
        retireNameView.setText("Space Trader: " + name );
        retirePointsView.setText(getLeftCol());
        retireStatsView.setText(getRightCol());
        retireThanks.setText("Thanks for Playing!");

        newGameButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetireActivity.this, MainMenuActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(600); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        newGameButtonView.startAnimation(anim);
        newGameButtonView.setBackgroundColor(Color.GREEN);
    }

    private String getLeftCol() {
        int[] points = player.getClassPoints();
        String[] pointText = new String[]{"Engineer Stat",  "Fighter Stat",  "Trader Stat",  "Pilot Stat"};
        String pointsStr = "";
        for (int i = 0; i < pointText.length; i++) {
            pointsStr += pointText[i] + ": " + points[i] + "\n \n";
        }
        pointsStr += "Difficulty" + ": " + game.getDifficulty() + "\n \n";
        return pointsStr;
    }

    private String getRightCol() {
        String criminalStatus = player.getCriminalStatus() ? "Criminal" : "Law-Abiding";
        String deadBy = player.getShip().getHealth() <= 0? "Battle" : "Retire";
        Object[] points = new Object[]{player.getCredits(), player.getShip().getShipType(),
                deadBy, game.getDataStorage().getTotalEncounters(), criminalStatus};
        String[] pointText = new String[]{"Credits",  "Ship Type", "Death By", "Total Encounters", "Criminal Status"};
        String pointsStr = "";
        for (int i = 0; i < pointText.length; i++) {
            pointsStr += pointText[i] + ": " + points[i] + "\n \n";
        }
        return pointsStr;
    }







    @Override
    public void onBackPressed() {}
}
