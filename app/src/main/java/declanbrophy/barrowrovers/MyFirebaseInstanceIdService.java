package declanbrophy.barrowrovers;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by User on 04/04/2018.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override

    public void onTokenRefresh(){
        //Get updated InstanceId token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(getApplicationContext(), "Refreshed Token: "+refreshedToken, Toast.LENGTH_LONG).show();

    }

}
