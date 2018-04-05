package declanbrophy.barrowrovers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewPlayers extends AppCompatActivity {

    Players player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_players);

        player = (Players)getIntent().getSerializableExtra("players");

        Toast.makeText(ViewPlayers.this, player+"\n+players", Toast.LENGTH_LONG).show();
    }
}
