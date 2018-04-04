package declanbrophy.barrowrovers;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by User on 04/04/2018.
 */

public class MyNotificationManager {

    private Context mCtx;
    private static MyNotificationManager mInstance;

    private MyNotificationManager(Context context){
        mCtx = context;
    }

    public static synchronized MyNotificationManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }

    public void dispalyNotification(String eventType, String location){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder();
    }
}
