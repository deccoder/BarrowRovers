package declanbrophy.barrowrovers;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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
    //Database reference object
    DatabaseReference teamDetails;
    Team teamOne;
    ListView teamlist;
    List<Team> teamArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        //Declaring and creating objects
        teamOne = new Team();
        //Makes an instance of Firebase database called team details
        teamDetails = FirebaseDatabase.getInstance().getReference("Teams");
        //Getting value of objects by finding them from xml file
        save = (Button) findViewById(R.id.save);

        teamName = (EditText) findViewById(R.id.teamName);
        systemAdmin = (EditText) findViewById(R.id.systemAdmin);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);
        teamlist = (ListView) findViewById(R.id.teamList);

        teamArray = new ArrayList<>();

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

        teamlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Team team = teamArray.get(position);

                Intent intent = new Intent(getApplicationContext(), Team.class);

                intent.putExtra("Team", team.getTeamName());
                intent.putExtra("Team", team.getAddress());
                intent.putExtra("Team", team.getEmail());
                intent.putExtra("Team", team.getId());

                startActivity(intent);
            }
        });

        teamlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Team team = teamArray.get(position);

                showUpdateBox(team.getId(), team.getTeamName(), team.getSystemAdmin(), team.getEmail(), team.getAddress());

                return true;
            }
        });


            }

    private void showUpdateBox(final String teamId, String newTeamName, String newSystemAdmin, String newEmail, String newAddress){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_team, null);

        dialogBuilder.setView(dialogView);

        final EditText tName = (EditText) dialogView.findViewById(R.id.teamName);
        final EditText sAdmin = (EditText) dialogView.findViewById(R.id.systemAdmin);
        final EditText contact = (EditText) dialogView.findViewById(R.id.email);
        final EditText location = (EditText) dialogView.findViewById(R.id.address);
        final Button update = (Button) dialogView.findViewById(R.id.update);

        dialogBuilder.setTitle("Update Team "+teamName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tName.getText().toString();
                String systemAd = sAdmin.getText().toString();
                String email = contact.getText().toString();
                String address = location.getText().toString();

                if (TextUtils.isEmpty(email)){
                    contact.setError("Email address required");
                    return;
                }

               updateTeam(teamId, name, systemAd, email, address);

                alertDialog.dismiss();

            }
        });



    }


        private void updateTeam(String teamId, String newName, String newSystemAdmin, String newEmail, String newAddress){

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Teams").child(teamId);

            Team team = new Team(teamId, newName, newAddress, newEmail, newSystemAdmin);

            databaseReference.setValue(team);

            Toast.makeText(this, "Team Updated Successfully", Toast.LENGTH_LONG).show();


        }



    private void addTeam(){
        //Get the details that have been entered by the user
        String name = teamName.getText().toString();
        String sAdmin = systemAdmin.getText().toString();
        String location = address.getText().toString();
        String contact = email.getText().toString();

        //Check to see if details have been filled in


        //Creates a unique id to the database called id
        String id = teamDetails.push().getKey();
        //Uses the id to pass the details that are in the brackets
        Team teamOne = new Team(id, name, sAdmin, location, contact);
        //Using the database reference called team details the values that are in teamOne are set to the id
        teamDetails.child(id).setValue(teamOne);
        //Message displayed to the user that the team details have been saved to the database
        Toast.makeText(CreateTeam.this, "Team Details Saved", Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onStart() {
        super.onStart();

        teamDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                teamArray.clear();

                for (DataSnapshot teamSnapshot : dataSnapshot.getChildren()){

                    Team team = teamSnapshot.getValue(Team.class);

                    teamArray.add(team);


                }

                TeamList adapter = new TeamList(CreateTeam.this, teamArray);

                teamlist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

