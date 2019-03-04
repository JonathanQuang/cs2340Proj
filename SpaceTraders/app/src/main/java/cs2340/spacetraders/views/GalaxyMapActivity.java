package cs2340.spacetraders.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;

import java.util.Map;
import java.util.Random;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.CelestialName;

public class GalaxyMapActivity extends AppCompatActivity {

    int[] smallStars = {
            R.drawable.blueS,
            R.drawable.redS,
            R.drawable.yellowS,
            R.drawable.whiteS
    };

    int[] mediumStars = {
            R.drawable.blueM,
            R.drawable.redM,
            R.drawable.yellowM,
            R.drawable.whiteM
    };

    int[] largeStars = {
            R.drawable.blueL,
            R.drawable.redL,
            R.drawable.yellowL,
            R.drawable.whiteL
    };

    /** Called when the application starts. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxy_map);

        Map <CelestialName, String> wholePlanetList = gala

        //19 solar systems
        Random generator = new Random();
        for (int i = 0; i < 19; i++) {
            ImageButton star = (ImageButton) findViewById(R.id.star);
            if (starsmall){
                int randomSmallStar = smallStars[generator.nextInt(smallStars.length)];
                star.setImageResource(randomSmallStar);
            } else if (star is medium){
                int randomMediumStar = mediumStars[generator.nextInt(mediumStars.length)];
                star.setImageResource(randomMediumStar);
            } else{
                int randomLargeStar = largeStars[generator.nextInt(largeStars.length)];
                star.setImageResource(randomLargeStar);
            }
        }
    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d( "onStart","The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause", "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "The onDestroy() event");
    }
}
