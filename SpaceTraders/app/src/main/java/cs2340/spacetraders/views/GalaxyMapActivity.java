package cs2340.spacetraders.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.SolarSystem;
import cs2340.spacetraders.model.Model;

public class GalaxyMapActivity extends AppCompatActivity {

//    int[] smallStars = {
//            R.drawable.blue_s,
//            R.drawable.red_s,
//            R.drawable.yellow_s,
//            R.drawable.white_s
//    };
//
//    int[] mediumStars = {
//            R.drawable.blue_m,
//            R.drawable.red_m,
//            R.drawable.yellow_m,
//            R.drawable.white_m
//    };
//
//    int[] largeStars = {
//            R.drawable.blue_l,
//            R.drawable.red_l,
//            R.drawable.yellow_l,
//            R.drawable.white_l
//    };

    /** Called when the application starts. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxy_map);

        Button map_button = findViewById(R.id.angry_btn1);
        map_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonShowPlanetInfoClick(v);
            }
        });

        Button map_button1 = findViewById(R.id.angry_btn2);
        map_button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonShowPlanetInfoClick(v);
            }
        });

        Planet currentPlanet = Model.getInstance().getGame().getGalaxy().getCurrentPlanet();

        List<Planet> wholePlanetList = Model.getInstance().getGame().getGalaxy().getPlanetList();
        List<SolarSystem> solarSystemList = Model.getInstance().getGame().getGalaxy().getSolarSystemList();
//        solarSystemList.get(0).getPlanetList();

        //19 solar systems
//        Random generator = new Random();
//        for (int i = 0; i < 19; i++) {
//            ImageButton star = (ImageButton) findViewById(R.id.star);
//            if (starsmall){
//                int randomSmallStar = smallStars[generator.nextInt(smallStars.length)];
//                star.setImageResource(randomSmallStar);
//            } else if (star.getS){
//                int randomMediumStar = mediumStars[generator.nextInt(mediumStars.length)];
//                star.setImageResource(randomMediumStar);
//            } else{
//                int randomLargeStar = largeStars[generator.nextInt(largeStars.length)];
//                star.setImageResource(randomLargeStar);
//            }
//        }
    }


    private void onButtonShowPlanetInfoClick(View view) {
        Context mContext = getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.planet_info_popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setElevation(5.0f);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
