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
    private static String teamId;
    ArrayList<teams> teams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        teams = new ArrayList<teams>();
        databaseReference = FirebaseDatabase.getInstance().getReference("teams");

        save = (Button) findViewById(R.id.save);
        teamName = (EditText) findViewById(R.id.teamName);
        systemAdmin = (EditText) findViewById(R.id.systemAdmin);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = teamName.getText().toString();

                if (TextUtils.isEmpty(teamId)) {
                    //save
                    String id = databaseReference.push().getKey();
                    teams teams = new teams(teamId, name);
                    databaseReference.child(teamId).setValue(teams);
                }else if (TextUtils.isEmpty(teamId)) {
                    String id = databaseReference.push().getKey();
                    String sAdmin = systemAdmin.getText().toString();
                    teams teams = new teams(id,sAdmin);
                    databaseReference.child(id).setValue(teams);
                }else if (TextUtils.isEmpty(teamId)) {
                    String id = databaseReference.push().getKey();
                    String teamAddress = address.getText().toString();
                    teams teams = new teams(id, teamAddress);
                    databaseReference.child(id).setValue(teams);
                }else {
                    String id = databaseReference.push().getKey();
                    String teamEmail = email.getText().toString();
                    teams teams = new teams(id,teamEmail);
                    databaseReference.child(id).setValue(teams);

                    Toast.makeText(CreateTeam.this, "Team created successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
