package declanbrophy.barrowrovers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import static android.R.attr.resource;



public class TeamList extends ArrayAdapter<Team> {

    private Activity context;
    private List<Team> teamList;

    public TeamList(Activity context, List<Team> teamList){
        super(context,R.layout.layout_team_list, teamList);
        this.context = context;
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_team_list, null, true);

        TextView teamName = (TextView) listViewItem.findViewById(R.id.teamName);
        TextView systemAdmin = (TextView) listViewItem.findViewById(R.id.systemAdmin);
        TextView address = (TextView) listViewItem.findViewById(R.id.address);
        TextView email = (TextView) listViewItem.findViewById(R.id.email);

        Team team = teamList.get(position);

        teamName.setText(team.getTeamName());
        systemAdmin.setText(team.getSystemAdmin());
        address.setText(team.getAddress());
        email.setText(team.getEmail());

        return  listViewItem;


    }


    }

