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
    DatabaseReference teamDetails;
    Team teamOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        //Declaring and creating objects
        teamOne = new Team();
        teamDetails = FirebaseDatabase.getInstance().getReference("Teams");
        //Getting value of objects by finding them from xml file
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
                //Add method to send data to the database
                addTeam();
                //Gets the text from the field where it was entered
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


            }


        });


    }
    private void addTeam(){
        String name = teamName.getText().toString();
        String sAdmin = systemAdmin.getText().toString();
        String location = address.getText().toString();
        String contact = email.getText().toString();

        String id = teamDetails.push().getKey();

        Team teamOne = new Team(id, name, sAdmin, location, contact);

        teamDetails.child(id).setValue(teamOne);

        Toast.makeText(CreateTeam.this, "Team Details Saved", Toast.LENGTH_LONG).show();
    }


}

