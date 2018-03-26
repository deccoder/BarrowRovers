package declanbrophy.barrowrovers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ViewTeam extends AppCompatActivity {

    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);

        team = (Team)getIntent().getSerializableExtra("team");

        Toast.makeText(getApplicationContext(),team+"\n+team",Toast.LENGTH_LONG).show();


    }
}
