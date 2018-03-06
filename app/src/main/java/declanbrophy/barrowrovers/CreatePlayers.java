package declanbrophy.barrowrovers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreatePlayers extends AppCompatActivity {

    Button add;
    EditText name,email,squadNumber,pinNumber;
    DatabaseReference databaseReference;
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
                String pName = name.getText().toString();

                if (TextUtils.isEmpty(playerId)){
                    String id = databaseReference.push().getKey();
                    Players players = new Players(id,pName);
                    databaseReference.child(id).setValue(players);

                    Toast.makeText(CreatePlayers.this, "Player added Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
