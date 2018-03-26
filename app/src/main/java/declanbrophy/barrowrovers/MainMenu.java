package declanbrophy.barrowrovers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Initialize and create contents of user interface
        Button createTeam = (Button) findViewById(R.id.createTeam);
        Button viewTeam = (Button) findViewById(R.id.viewTeam);
        Button createPlayers = (Button) findViewById(R.id.createPlayers);
        Button viewPlayers = (Button) findViewById(R.id.viewPlayers);
        Button createEvent = (Button) findViewById(R.id.createEvent);
        Button viewEvent = (Button) findViewById(R.id.viewEvent);

        //Brings user to create team details page
        createTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, CreateTeam.class);
                startActivity(intent);
            }
        });
        //Brings user to view the team/teams that have been created
        viewTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, ViewTeam.class);
                startActivity(intent);
            }
        });
        //Brings user to adding players to the team
        createPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, CreatePlayers.class);
                startActivity(intent);
            }
        });
        //Brings user to player details that have been added to the team
        viewPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, ViewPlayers.class);
            }
        });
        //Brings user to create a schedule of matches and training
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, CreateEvent.class);
            }
        });
        //Brings user to calendar of events organised for the team
        viewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, ViewEvent.class);
            }
        });
    }
}
