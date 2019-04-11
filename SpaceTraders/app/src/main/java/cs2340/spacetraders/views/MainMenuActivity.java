package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Base64;

import cs2340.spacetraders.R;
import cs2340.spacetraders.model.Model;

public class MainMenuActivity extends AppCompatActivity implements Serializable {

    public static final int ADD_PLAYER_REQUEST_ID = 1;
    private DatabaseReference myRef;
    private final Model model = Model.getInstance();

    /** Called when the application starts. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //Set up Firebase
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        //New Game
        Button newGame =  findViewById(R.id.new_player);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ConfigurationActivity.class);
                startActivityForResult(intent, ADD_PLAYER_REQUEST_ID);
            }
        });

        //Load Old Game
        Button continueGame =  findViewById(R.id.continue_game);
        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadLastGame();
            }
        });
    }

    /**
     * to load the last instance of the game
     */
    private void loadLastGame() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Model previousGame = (Model) readMessage(value);
                Toast.makeText(getApplication(), "Loaded Game", Toast.LENGTH_LONG).show();
                if (previousGame != null) {
                    //Set up Model
                    model.setPlayer(previousGame.getPlayer());
                    model.setGame(previousGame.getGame());

                    Intent intent = new Intent(MainMenuActivity.this, MarketScreenActivity.class);
                    startActivityForResult(intent, ADD_PLAYER_REQUEST_ID);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("Loading Game", "Failed to read value.", error.toException());
            }
        });
    }

    /**
     * to read the log messages and send log messages
     * @param msg
     * @return the read in message
     */
    private Object readMessage(String msg) {
        try {
            byte[] bytes = Base64.getDecoder().decode(msg);
            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Object o = in.readObject();
            in.close();
            Log.d("Loading Game", "Model Read!!! = " + o);
            return o;
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
                Log.d("Loading Game", "Failed to load value.", e);
        }
        return null;
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