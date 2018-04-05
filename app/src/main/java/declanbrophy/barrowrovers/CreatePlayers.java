package declanbrophy.barrowrovers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreatePlayers extends AppCompatActivity {

    Button add;
    EditText name,email,squadNumber,pinNumber;
    DatabaseReference databaseReference;
    Players playerOne;
    private static String playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_players);

        playerOne = new Players();
        databaseReference = FirebaseDatabase.getInstance().getReference("Players");

        add = (Button) findViewById(R.id.add);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        squadNumber = (EditText) findViewById(R.id.squadNumber);
        pinNumber = (EditText) findViewById(R.id.pinNumber);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Address = email.getText().toString();
                String NumberOne = squadNumber.getText().toString();
                String NumberTwo = pinNumber.getText().toString();

                playerOne.setpName(Name);
                playerOne.setEAddress(Address);
                playerOne.setSNumber(NumberOne);
                playerOne.setPNumber(NumberTwo);

                Intent intent = new Intent(CreatePlayers.this, ViewPlayers.class);
                intent.putExtra("players", playerOne);
                startActivity(intent);
                finish();

                String id = databaseReference.push().getKey();
                Players players = new Players(Name, Address, NumberOne, NumberTwo);
                databaseReference.child(id).setValue(players);

                Toast.makeText(CreatePlayers.this, "Player added successfully", Toast.LENGTH_LONG).show();

//                if (TextUtils.isEmpty(playerId)){
//                    String id = databaseReference.push().getKey();
//                    Players players = new Players(id,pName);
//                    databaseReference.child(id).setValue(players);
//                }else if (TextUtils.isEmpty(playerId)) {
//                    String playerId = databaseReference.push().getKey();
//                    String pEmail = email.getText().toString();
//                    Players players = new Players(playerId,pEmail);
//                    databaseReference.child(playerId).setValue(players);
//                }else if (TextUtils.isEmpty(playerId)) {
//                    String id = databaseReference.push().getKey();
//                    String pSquadNumber = squadNumber.getText().toString();
//                    Players players = new Players(id,pSquadNumber);
//                    databaseReference.child(id).setValue(players);
//                }else {
//                    String id = databaseReference.push().getKey();
//                    String pin = pinNumber.getText().toString();
//                    Players players = new Players(id,pin);
//                    databaseReference.child(id).setValue(players);

//                    Toast.makeText(CreatePlayers.this, "Player details added successfully ", Toast.LENGTH_SHORT).show();
//                }

                           }
        });


    }



}
