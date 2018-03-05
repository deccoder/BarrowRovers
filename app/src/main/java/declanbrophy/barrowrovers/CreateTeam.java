package declanbrophy.barrowrovers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreateTeam extends AppCompatActivity {

    Button save;
    EditText teamName, systemAdmin, address, email;
    DatabaseReference databaseReference;
    private static String userId;
    List<teams> teams;
    ListView teamlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        teams = new ArrayList<teams>();
        databaseReference = FirebaseDatabase.getInstance().getReference("teams");

        Button save = (Button) findViewById(R.id.save);
        final EditText teamName = (EditText) findViewById(R.id.teamName);
        final EditText systemAdmin = (EditText) findViewById(R.id.systemAdmin);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText email = (EditText) findViewById(R.id.email);
        ListView teamlist = (ListView) findViewById(R.id.teamlist);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = teamName.getText().toString();

                if (TextUtils.isEmpty(userId)) {
                    //save
                    String id = databaseReference.push().getKey();
                    teams teams = new teams(teams);
                    databaseReference.child(id).setValue(teams);

                    Toast.makeText(CreateTeam.this, "Team Created Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
