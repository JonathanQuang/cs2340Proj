package cs2340.spacetraders.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Travel.Travel;
import cs2340.spacetraders.entity.Universe.Galaxy;
import cs2340.spacetraders.entity.Universe.Planet;
import cs2340.spacetraders.entity.Universe.RelativePosition;
import cs2340.spacetraders.entity.Universe.SolarSystem;
import cs2340.spacetraders.entity.Universe.Wormhole;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.GalaxyMapViewModel;

public class GalaxyMapActivity extends AppCompatActivity {

    private GalaxyMapViewModel galaxyMapVM;
    private Travel travel;
    private int screenHeight;
    private int screenWidth;
    private RelativePosition mapSize;
    private Planet currentPlanet;

    /** Called when the application starts.*/
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxy_map);
        int[] planetButtonIDs = getPlanetButtonIDs();
        int[] ringIDs = getRingButtonIDs();
        int[][] wormholeIDs = getWormholeIDs();
        setScreenDimensions();

        //Get info for Planets
        Galaxy galaxy = Model.getInstance().getGame().getGalaxy();
        mapSize = galaxy.getMapSize();
        currentPlanet = galaxy.getCurrentPlanet();
        List<Planet> planetList = galaxy.getPlanetList();
        travel = new Travel(Model.getInstance().getPlayer(), currentPlanet);
        galaxyMapVM = new GalaxyMapViewModel(currentPlanet, planetList);

        //Place All Plants
        for (int i = 0; i < planetButtonIDs.length; i++) {
            Planet planet = i < planetList.size() ? planetList.get(i) : null;
            if (planet != null) {
                placePlanet(planet, planetButtonIDs[i]);
                if (planet.equals(currentPlanet)) {
                    Button curr_planet_button = findViewById(planetButtonIDs[i]);
                    curr_planet_button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.white_l));
                }
            } else { findViewById(planetButtonIDs[i]).setVisibility(View.GONE); }
        }

        //Circle Range of Travel
        placeRangeRing(travel.radiusOfTravel());

        //Circle All Systems
        List<SolarSystem> solarSystemsList = galaxy.getSolarSystemList();
        for (int i = 0; i < ringIDs.length; i++) {
            SolarSystem solarSystem = i < solarSystemsList.size() ? solarSystemsList.get(i) : null;
            if (solarSystem != null) {
                placeSystemRing(solarSystem, ringIDs[i]);
            } else { findViewById(ringIDs[i]).setVisibility(View.GONE);}
        }

        //Place Wormholes
        List<Wormhole[]> wormholePairList = galaxy.getWormholePairList();
        for (int i = 0; i < wormholeIDs.length; i++) {
            Wormhole[] wormhole = i < wormholePairList.size() ? wormholePairList.get(i) : null;
            if (wormhole != null) {
                placeWormhole(wormhole[0], wormholeIDs[i][0], wormholeIDs[i][2]);
                placeWormhole(wormhole[1], wormholeIDs[i][1], wormholeIDs[i][2]);
            } else {
                findViewById(wormholeIDs[i][0]).setVisibility(View.GONE);
                findViewById(wormholeIDs[i][1]).setVisibility(View.GONE);
            }
        }
    }

    /**
     * to place the planet on the map
     * @param planet planet considered
     * @param buttonID the ID of the planet button
     */
    private void placePlanet(final Planet planet, int buttonID) {
        Button planet_button = findViewById(buttonID);
        int imageID = Model.getInstance().getPlanetImageIDs().get(planet.getResources());
        int size = 25 + planet.getSizeAsInt() * 5;

        makeCustomView(planet_button, imageID, size, planet.getRelativePosition());

        planet_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                galaxyMapVM.setCurrentPlanet(planet);
                onButtonShowPlanetInfoClick(v, planet);
            }
        });
    }

    /**
     * to show the max radius you can travel to
     * @param radiusOfTravel the max radius you can travel to
     */
    private void placeRangeRing(int radiusOfTravel) {
        ImageView ring = findViewById(R.id.validRing);
        int size = 2 * (radiusOfTravel * 44);
        makeCustomView(ring, R.drawable.cyan_ring, size, currentPlanet.getRelativePosition());
    }

    /**
     * to place the ring outlining each solar system
     * @param solarSystem the solar system in consideration
     * @param ringID the ID of the ring
     */
    private void placeSystemRing(SolarSystem solarSystem, int ringID) {
        View ring = findViewById(ringID);
        int size = 130 + (solarSystem.getCenter().getRectRadius() - 1) * 100;
        makeCustomView(ring, R.drawable.ring, size, solarSystem.getCenter());
    }

    /**
     * to place wormholes on the map
     * @param wormhole wormhole in consideration
     * @param wormholeID the ID of the wormhole
     * @param wormholeImg the image used for it
     */
    private void placeWormhole(final Wormhole wormhole, int wormholeID, int wormholeImg) {
        Button wormhole_button = findViewById(wormholeID);
        makeCustomView(wormhole_button, wormholeImg, 25, wormhole.getPosition());

        wormhole_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                galaxyMapVM.setSelectedWormhole(wormhole);
                onButtonShowWormHoleClick(v, wormhole);
            }
        });
    }

    /**
     * to make the background and set the view
     * @param view the view object
     * @param imageID image used for background
     * @param size the size of the layout
     * @param position the position of layout
     */
    private void makeCustomView(View view, int imageID, int size, RelativePosition position) {
        //Set Picture
        view.setBackground(ContextCompat.getDrawable(getApplicationContext(), imageID));
        //Set Size
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = params.width = size;
        view.setLayoutParams(params);
        //Set Position
        setPositionOnScreen(view, position, params.height/2);
    }

    /**
     * to set the position on screen
     * @param view the view object
     * @param position position object
     * @param radius the distance from the planet in consideration
     */
    private void setPositionOnScreen(View view, RelativePosition position, int radius) {
        //Places based on proportion from (planet x to map width) is (screen x to screen width)
        double probX = ((double) position.getX()) / mapSize.getX();
        double probY = ((double) position.getY()) / mapSize.getY();

        view.setTranslationX((int) (screenWidth * probX - radius) + 20);
        view.setTranslationY((int) (screenHeight * probY - radius) + 20);
    }

    /**
     * the button to show planetary information
     * @param view the view object
     * @param planet the planet in consideration
     */
    private void onButtonShowPlanetInfoClick(View view, final Planet planet) {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.planet_info_popup, null);

        TextView textView = popupView.findViewById(R.id.planetText);
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

        Button travelButton = popupView.findViewById(R.id.travelButton);
        if (!travel.getValidPlanets().contains(planet)){ travelButton.setEnabled(false); }
        travelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (travel.travel(planet) == 0) {
                    Intent intent = new Intent(GalaxyMapActivity.this, MarketScreenActivity.class);
                    startActivityForResult(intent,0);
                }
            }
        });
    }

    /**
     * the button to show the wormhole options
     * @param view the view object
     * @param wormhole the wormhole in consideration
     */
    private void onButtonShowWormHoleClick(View view, final Wormhole wormhole) {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.wormhole_popup, null);

        TextView textView = popupView.findViewById(R.id.wormholeText);
        textView.setText(galaxyMapVM.popUpWormHoleInfo());

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

        Button warpButton = popupView.findViewById(R.id.warpButton);
        if (currentPlanet != wormhole.getShipportPlanet()){ warpButton.setEnabled(false); }
        warpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                travel.wormHoleTravel(wormhole.getConnectedWormhole().getShipportPlanet());
                Intent intent = new Intent(GalaxyMapActivity.this, MarketScreenActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    /**
     * to set the dimensions of the screen
     */
    public void setScreenDimensions() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x - 20;
        screenHeight = size.y - 250;
    }

    /**
     * pixels used per unit
     * @return
     */
    public int getPixelPerUnit() {
        return(int) (1.0 / mapSize.getY() * screenHeight);
    }

    /**
     * getter for all the IDs of planet buttons
     * @return all the IDs of planet buttons
     */
    public int[] getPlanetButtonIDs() {
        return new int[]{
                R.id.planet1, R.id.planet2, R.id.planet3, R.id.planet4, R.id.planet5,
                R.id.planet6, R.id.planet7, R.id.planet8, R.id.planet9, R.id.planet10,
                R.id.planet11, R.id.planet12, R.id.planet13, R.id.planet14, R.id.planet15,
                R.id.planet16, R.id.planet17, R.id.planet18, R.id.planet19, R.id.planet20,
                R.id.planet21, R.id.planet22, R.id.planet23, R.id.planet24, R.id.planet25,
                R.id.planet26, R.id.planet27, R.id.planet28, R.id.planet29, R.id.planet30,
                R.id.planet31, R.id.planet32, R.id.planet33, R.id.planet34, R.id.planet35,
                R.id.planet36, R.id.planet37, R.id.planet38, R.id.planet39, R.id.planet40,
                R.id.planet41, R.id.planet42, R.id.planet43, R.id.planet44, R.id.planet45,
                R.id.planet46, R.id.planet47, R.id.planet48, R.id.planet49, R.id.planet50,
                R.id.planet51, R.id.planet52, R.id.planet53, R.id.planet54, R.id.planet55,
                R.id.planet56, R.id.planet57, R.id.planet58, R.id.planet59, R.id.planet60,
                R.id.planet61, R.id.planet62, R.id.planet63, R.id.planet64, R.id.planet65,
                R.id.planet66, R.id.planet67, R.id.planet68, R.id.planet69, R.id.planet70,
                R.id.planet71, R.id.planet72, R.id.planet73, R.id.planet74, R.id.planet75,
                R.id.planet76, R.id.planet77, R.id.planet78, R.id.planet79, R.id.planet80,
                R.id.planet81, R.id.planet82, R.id.planet83, R.id.planet84, R.id.planet85,
                R.id.planet86, R.id.planet87, R.id.planet88, R.id.planet89, R.id.planet90,
                R.id.planet91, R.id.planet92, R.id.planet93, R.id.planet94, R.id.planet95,
                R.id.planet96, R.id.planet97, R.id.planet98, R.id.planet99, R.id.planet100,
                R.id.planet101, R.id.planet102, R.id.planet103, R.id.planet104, R.id.planet105,
                R.id.planet106, R.id.planet107, R.id.planet108, R.id.planet109, R.id.planet110,
                R.id.planet111, R.id.planet112, R.id.planet113, R.id.planet114, R.id.planet115,
                R.id.planet116, R.id.planet117, R.id.planet118, R.id.planet119, R.id.planet120,
                R.id.planet121, R.id.planet122, R.id.planet123};
    }

    /**
     * getter for all the IDs of rings
     * @return all the IDs of rings
     */
    public int[] getRingButtonIDs() {
        return new int[]{
                R.id.ring1, R.id.ring2, R.id.ring3, R.id.ring4, R.id.ring5,
                R.id.ring6, R.id.ring7, R.id.ring8, R.id.ring9, R.id.ring10,
                R.id.ring11, R.id.ring12, R.id.ring13, R.id.ring14, R.id.ring15,
                R.id.ring16, R.id.ring17, R.id.ring18, R.id.ring19, R.id.ring20,
                R.id.ring21, R.id.ring22, R.id.ring23, R.id.ring24, R.id.ring25,
                R.id.ring26, R.id.ring27, R.id.ring28, R.id.ring29, R.id.ring30,
                R.id.ring31, R.id.ring32, R.id.ring33, R.id.ring34, R.id.ring35,
                R.id.ring36, R.id.ring37, R.id.ring38, R.id.ring39, R.id.ring40,
                R.id.ring41, R.id.ring42, R.id.ring43, R.id.ring44, R.id.ring45,
                R.id.ring46, R.id.ring47, R.id.ring48, R.id.ring49, R.id.ring50,
                R.id.ring51, R.id.ring52, R.id.ring53, R.id.ring54, R.id.ring55,
                R.id.ring56, R.id.ring57, R.id.ring58, R.id.ring59, R.id.ring60,
                R.id.ring61, R.id.ring62};
    }

    /**
     * getter for all the IDs of wormholes
     * @return all the IDs of wormholes
     */
    public int[][] getWormholeIDs() {
        return new int[][]{{R.id.wormhole1, R.id.wormhole2, R.drawable.wormhole},
                { R.id.wormhole3, R.id.wormhole4, R.drawable.wormhole_green}};
    }
}
