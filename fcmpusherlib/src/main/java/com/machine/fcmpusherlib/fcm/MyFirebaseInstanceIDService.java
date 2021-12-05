package com.machine.fcmpusherlib.fcm;

import android.provider.Settings;
import android.util.Log;
import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.machine.fcmpusherlib.Utils;


public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseIDService";

  //  private static final String SUBSCRIBE_TO = AppSettings.getUId()+"";
  private String m_androidId;

    @Override
    public void onNewToken(@NonNull String s) {
       /* String refreshedToken = String.valueOf(FirebaseMessaging.getInstance().getToken());*/
        Log.i(Utils.getTag()+ " ", " Refreshed token: " + s);
        super.onNewToken(s);
        // TODO: Implement this method to send any registration to your app's servers.
       // sendRegistrationToServer(refreshedToken);

        m_androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        // Once the token is generated, subscribe to topic with the userId
        FirebaseMessaging.getInstance().subscribeToTopic("kaseemkhantesting");
        Log.i(Utils.getTag()+ " ", "onTokenRefresh completed with token: " + s);
    }


    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
      //  AppSettings.setUserToken(getApplicationContext(),token);

        // Add custom implementation, as needed.
    }
}

