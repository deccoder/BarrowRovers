package declanbrophy.barrowrovers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreatePlayers extends AppCompatActivity {

    Button add;
    EditText name,email,squadNumber,pinNumber;
    DatabaseReference databaseReference;
    ListView listViewPlayers;
    List<Players> players;
    private static String playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_players);

        players = new ArrayList<Players>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Players");

        add = (Button) findViewById(R.id.add);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        squadNumber = (EditText) findViewById(R.id.squadNumber);
        pinNumber = (EditText) findViewById(R.id.pinNumber);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });


    }
}
