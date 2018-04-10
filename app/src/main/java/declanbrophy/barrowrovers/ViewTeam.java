package declanbrophy.barrowrovers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewTeam extends AppCompatActivity {

    Team team;
    ListView teamBox;
    DatabaseReference teamDetails;
    //Creates a list of teams
    List<Team> teamList;

    @Override
    protected void onStart() {
        super.onStart();

        teamDetails.addValueEventListener(new ValueEventListener() {
            @Override
            //This method will be executed anytime something is changed in the database
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Clears the teamlist each time it is called from the database
                teamList.clear();

                for (DataSnapshot teamSnapshot : dataSnapshot.getChildren()){
                    //Using team object to get values
                    Team team = teamSnapshot.getValue(Team.class);
                    //Adds values to the team list
                    teamList.add(team);
                }

                TeamList adapter = new TeamList(ViewTeam.this, teamList);
                teamBox.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);

        teamBox = (ListView) findViewById(R.id.textBox);
        teamList = new ArrayList<>();

        team = (Team)getIntent().getSerializableExtra("team");

        Toast.makeText(ViewTeam.this, team+"\n+team", Toast.LENGTH_LONG).show();


    }
}
