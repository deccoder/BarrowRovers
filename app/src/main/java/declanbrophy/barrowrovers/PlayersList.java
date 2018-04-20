package declanbrophy.barrowrovers;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PlayersList extends ArrayAdapter<Players> {

    private Activity context;
    private List<Players> playersList;

    public PlayersList(Activity context, List<Players> playersList){
        super(context, R.layout.list_layout, playersList);
        this.context = context;
        this.playersList = playersList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView playersName = (TextView) listViewItem.findViewById(R.id.playersName);
        TextView playersEmail = (TextView) listViewItem.findViewById(R.id.playersEmail);
        TextView playersNumber = (TextView) listViewItem.findViewById(R.id.playersNumber);

        Players players = playersList.get(position);

//        playersName.setText(players.getpName());
//        playersEmail.setText(players.getEmail());
//        playersNumber.setText(players.getSNumber());

        return listViewItem;

    }
}
