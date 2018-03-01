package declanbrophy.barrowrovers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        Button save = (Button) findViewById(R.id.save);
        EditText teamName = (EditText) findViewById(R.id.teamName);
        EditText systemAdmin = (EditText) findViewById(R.id.systemAdmin);
        EditText address = (EditText) findViewById(R.id.address);
        EditText email = (EditText) findViewById(R.id.email);
    }
}
