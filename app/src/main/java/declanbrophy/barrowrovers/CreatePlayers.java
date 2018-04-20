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
    ListView playersView;
    Button add;
    EditText name,email,squadNumber,pinNumber;
    DatabaseReference playerDetails;
    Players playerOne;
    List<Players> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_players);

        playerOne = new Players();
        playerDetails = FirebaseDatabase.getInstance().getReference("Players");

        playersView = (ListView) findViewById(R.id.playersView);
        add = (Button) findViewById(R.id.add);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        squadNumber = (EditText) findViewById(R.id.squadNumber);
        pinNumber = (EditText) findViewById(R.id.pinNumber);


        playersList = new ArrayList<>();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add method to send data to database
                addPlayer();
                //Gets the text from the field where it was entered
                String Name = name.getText().toString();
                String Address = email.getText().toString();
                String NumberOne = squadNumber.getText().toString();
                String NumberTwo = pinNumber.getText().toString();
                //Set method used to store data entered in String variable in playerOne object
                playerOne.setpName(Name);
                playerOne.setEAddress(Address);
                playerOne.setSNumber(NumberOne);
                playerOne.setPNumber(NumberTwo);

                Intent intent = new Intent(CreatePlayers.this, ViewPlayers.class);
                intent.putExtra("players", playerOne);
                startActivity(intent);
                finish();


                           }
        });


    }

//    @Override
//    protected void onStart() {
//        super.onStart();

//        playerDetails.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                playersList.clear();
//                for (DataSnapshot playerSnapshot : dataSnapshot.getChildren()){
//                    Players players =playerSnapshot.getValue(Players.class);

//                    playersList.add(players);

//                }

//                PlayersList adapter = new PlayersList(CreatePlayers.this, playersList);
//                playersView.setAdapter(adapter);
//            }

//            @Override
//            public void onCancelled(DatabaseError databaseError) {

//            }
//        });
//    }

    private void addPlayer(){
    String Name = name.getText().toString();
    String Address = email.getText().toString();
    String NumberOne = squadNumber.getText().toString();
    String NumberTwo = pinNumber.getText().toString();

    String id = playerDetails.push().getKey();

    Players playerOne = new Players(id, Name, Address, NumberOne, NumberTwo);

    playerDetails.child(id).setValue(playerOne);

    Toast.makeText(CreatePlayers.this, "Player details saved", Toast.LENGTH_LONG).show();
}


}
