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

public class CreateTeam extends AppCompatActivity {
    //Initializing variables
    Button save, update;
    EditText teamName, systemAdmin, address, email;
    DatabaseReference databaseReference;
    public static String teamId;
    Team teamOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        //Declaring and creating objects
        teamOne = new Team();
        databaseReference = FirebaseDatabase.getInstance().getReference("teams");
        //Creating objects of user interface
        save = (Button) findViewById(R.id.save);
        update = (Button) findViewById(R.id.update);
        teamName = (EditText) findViewById(R.id.teamName);
        systemAdmin = (EditText) findViewById(R.id.systemAdmin);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);

        //Activation of button when pressed
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declaring a variable of type String and passing text to that String variable
                String name = teamName.getText().toString();
                String sAdmin = systemAdmin.getText().toString();
                String location = address.getText().toString();
                String contact = email.getText().toString();
                //Set method to store data entered in String variable in team object
                teamOne.setTeamName(name);
                teamOne.setSystemAdmin(sAdmin);
                teamOne.setAddress(location);
                teamOne.setEmail(contact);
                //Intent to move data from create team to view team class
                Intent intent = new Intent(CreateTeam.this, ViewTeam.class);
                intent.putExtra("team", teamOne);
                startActivity(intent);
                finish();
        //Calling database to save data
        String id = databaseReference.push().getKey();
        Team teamOne = new Team(name, sAdmin, location, contact);
        databaseReference.child(id).setValue(teamOne);

        Toast.makeText(CreateTeam.this, "Team Created Successfully", Toast.LENGTH_LONG).show();

//                if (TextUtils.isEmpty(teamId)) {
//                    //save
//                    String id = databaseReference.push().getKey();
//                    Team teams = new Team(teamId, name);
//                    databaseReference.child(teamId).setValue(teams);
//                }else if (TextUtils.isEmpty(teamId)) {
//                    String sAdmin = systemAdmin.getText().toString();
//                    String teamId = databaseReference.push().getKey();
//                    Team teams = new Team(teamId, sAdmin);
//                    databaseReference.child(teamId).setValue(teams);
//                }else if (TextUtils.isEmpty(teamId)) {
//                    String teamId = databaseReference.push().getKey();
//                    String teamAddress = address.getText().toString();
//                    Team teams = new Team(teamId, teamAddress);
//                    databaseReference.child(teamId).setValue(teams);
//                }else if (TextUtils.isEmpty(teamId)){
//                    String teamEmail = email.getText().toString();
//                    String teamId = databaseReference.push().getKey();
//                    Team teams = new Team(teamId,teamEmail);
//                    databaseReference.child(teamId).setValue(teams);
//
//                    Toast.makeText(CreateTeam.this,"Team created successfully", Toast.LENGTH_SHORT).show();
//                }




            }


        });


    }


}

