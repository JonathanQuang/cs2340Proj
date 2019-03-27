package cs2340.spacetraders.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.SolarSystem;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.GalaxyMapViewModel;

public class GalaxyMapActivity extends AppCompatActivity {

    private GalaxyMapViewModel galaxyMapVM;

    /** Called when the application starts. */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxy_map);

        Planet currentPlanet = Model.getInstance().getGame().getGalaxy().getCurrentPlanet();
        Planet[] planetList = currentPlanet.getParentSolarSystem().getPlanetList();
        galaxyMapVM = new GalaxyMapViewModel(currentPlanet, planetList);

        int[] planetButtonIDs = new int[]{R.id.planet1, R.id.planet2, R.id.planet3, R.id.planet4, R.id.planet5};

        for (int i = 0; i < planetButtonIDs.length; i++) {
            Planet planet = i < planetList.length ? planetList[i] : null;
            makePlanetButton(planet, planetButtonIDs[i]);
        }




        //BLAH-----------------------------------------------------------------

        final ImageView ImageView_BitmapView = (ImageView) findViewById(R.id.img);

        int bitmapWidth = ImageView_BitmapView.getMaxWidth();
        int bitmapHeight = ImageView_BitmapView.getMaxHeight();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        // set maximum scroll amount (based on center of image)
        int maxX = (int)((bitmapWidth / 2) - (screenWidth / 2));
        int maxY = (int)((bitmapHeight / 2) - (screenHeight / 2));
        System.out.println(bitmapWidth + "x" + bitmapHeight + " " + screenWidth + "x" + screenHeight);

        // set scroll limits
        final int maxLeft = (maxX * -1);
        final int maxRight = maxX;
        final int maxTop = (maxY * -1);
        final int maxBottom = maxY;


        // set touchlistener
        ImageView_BitmapView.setOnTouchListener(new View.OnTouchListener()
        {
            float downX, downY;
            int totalX, totalY;
            int scrollByX, scrollByY;
            public boolean onTouch(View view, MotionEvent event)
            {
                float currentX, currentY;
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("sjdfnskdjnfjksd");
                        downX = event.getX();
                        downY = event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        currentX = event.getX();
                        currentY = event.getY();
                        scrollByX = (int)(downX - currentX);
                        scrollByY = (int)(downY - currentY);

                        // scrolling to left side of image (pic moving to the right)
                        if (currentX > downX)
                        {
                            if (totalX == maxLeft)
                            {
                                scrollByX = 0;
                            }
                            if (totalX > maxLeft)
                            {
                                totalX = totalX + scrollByX;
                            }
                            if (totalX < maxLeft)
                            {
                                scrollByX = maxLeft - (totalX - scrollByX);
                                totalX = maxLeft;
                            }
                        }

                        // scrolling to right side of image (pic moving to the left)
                        if (currentX < downX)
                        {
                            if (totalX == maxRight)
                            {
                                scrollByX = 0;
                            }
                            if (totalX < maxRight)
                            {
                                totalX = totalX + scrollByX;
                            }
                            if (totalX > maxRight)
                            {
                                scrollByX = maxRight - (totalX - scrollByX);
                                totalX = maxRight;
                            }
                        }

                        // scrolling to top of image (pic moving to the bottom)
                        if (currentY > downY)
                        {
                            if (totalY == maxTop)
                            {
                                scrollByY = 0;
                            }
                            if (totalY > maxTop)
                            {
                                totalY = totalY + scrollByY;
                            }
                            if (totalY < maxTop)
                            {
                                scrollByY = maxTop - (totalY - scrollByY);
                                totalY = maxTop;
                            }
                        }

                        // scrolling to bottom of image (pic moving to the top)
                        if (currentY < downY)
                        {
                            if (totalY == maxBottom)
                            {
                                scrollByY = 0;
                            }
                            if (totalY < maxBottom)
                            {
                                totalY = totalY + scrollByY;
                            }
                            if (totalY > maxBottom)
                            {
                                scrollByY = maxBottom - (totalY - scrollByY);
                                totalY = maxBottom;
                            }
                        }

                        System.out.println("sjdfddddddddddddd"+scrollByX);
                        ImageView_BitmapView.scrollBy(scrollByX, scrollByY);
                        downX = currentX;
                        downY = currentY;
                        break;

                }

                return true;
            }
        });
    }

    private void makePlanetButton(final Planet planet, int buttonID) {
        Button planet_button = findViewById(buttonID);
        if (planet == null) {
            planet_button.setVisibility(View.GONE);
            return;
        }

        int imageID = Model.getInstance().getPlanetImageIDs().get(planet.getResources());
        planet_button.setBackground(ContextCompat.getDrawable(getApplicationContext(), imageID));

        ViewGroup.LayoutParams params = planet_button.getLayoutParams();
        params.height = params.width = 70 + planet.getSizeAsInt() * 40;
        planet_button.setLayoutParams(params);

        planet_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                galaxyMapVM.setCurrentPlanet(planet);
                onButtonShowPlanetInfoClick(v, planet);
            }
        });
    }

    private void onButtonShowPlanetInfoClick(View view, Planet planet) {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.planet_info_popup, null);

        TextView textView = popupView.findViewById(R.id.buyButtonText);
        textView.setText(galaxyMapVM.popUpPlanetInfo());

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
