package com.machine.fcmpusherlib.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.machine.fcmpusherlib.BasicResponse;
import com.machine.fcmpusherlib.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.machine.fcmpusherlib.R;
import com.machine.fcmpusherlib.Utils;
import com.machine.fcmpusherlib.api.Connector;
import com.machine.fcmpusherlib.api.OnRequestResponseListener;
import com.machine.fcmpusherlib.api.ServerCommunicator;
import com.machine.fcmpusherlib.api.WebErrorResponse;
import com.machine.fcmpusherlib.api.WebResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class MyFirebaseMessagingService extends FirebaseMessagingService implements OnRequestResponseListener{
    private static final String TAGC = MyFirebaseMessagingService.class.getName();

    Context mContext;
    private String title = "",message = "",eventType = "", messageBody = "";

    private final String UCLICKS_CHANNEL_ID = "uclicks_channel";

    String deviceToken = "";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.i(TAGC, " onMessageRecieved call ---" + remoteMessage.getData());

/// {body={"title":"Requested category succesfully Accepted ","message":"Your undefined Category request has been Accepted","eventType":"AddedCategory"}}

        Log.e(TAGC, " onMessageRecieved call ---" + remoteMessage.getData());
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        // Check if message contains a data payload.
        mContext = this;


        //body
        if (remoteMessage.getData().containsKey("body"))
            messageBody = remoteMessage.getData().get("body");

        try {
            JSONObject jsonObject = new JSONObject(messageBody);

            title = jsonObject.getString("title");
            message = jsonObject.getString("message");
            eventType = jsonObject.getString("eventType");


            Log.e(Utils.getTag()+ " " , " title =" +   jsonObject.getString("title"));
            Log.e(Utils.getTag()+ " " , " message =" +   jsonObject.getString("message"));
            Log.e(Utils.getTag()+ " " , " eventType =" +   jsonObject.getString("eventType"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    //    final Intent intent = new Intent(this, MainActivity.class);
   //     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationID = new Random().nextInt(3000);


//        Apps targeting SDK 26 or above (Android O) must implement notification channels and add its notifications
//        to at least one of them. Therefore, confirm if version is Oreo or higher, then setup notification channel

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupChannels(notificationManager);
        }


     ///   PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_ONE_SHOT);

        Log.e(Utils.getTag() + " ", " N_images =" + title);
        Log.e(Utils.getTag() + " ", " N_message =" + message);
        Log.e(Utils.getTag() + " ", " N_eventType =" + eventType);

        //   Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.uclick_appicon);



//        try {
//       //     largeIcon = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(remoteMessage.getData().get("images")));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder;

            notificationBuilder = new NotificationCompat.Builder(this, UCLICKS_CHANNEL_ID)
                    //      .setSmallIcon(R.drawable.uclick_appicon)
                    .setSmallIcon(R.drawable.ic_launcher)
                    //     .setLargeIcon(largeIcon)
                    .setContentTitle(title+"")
                    .setContentText(message+"")
                    .setAutoCancel(true)
                    .setSound(notificationSoundUri)
                    .setContentIntent(pendingIntent);


        //Set notification color to match your app color template
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            notificationBuilder.setColor(getResources().getColor(R.color.colorPrimaryDark));
//        }
        notificationManager.notify(notificationID, notificationBuilder.build());

   ///     AppSettings.setNotificationCounter(this, (AppSettings.getNotificationCounter(this) + 1));


//        Intent intentBroadcast = new Intent(Constants.CONS_INTENT_NEW_NOTIFICATION);
//        intentBroadcast.putExtra(Constants.CONS_INTENT_NEW_NOTIFICATION + "", AppSettings.getNotificationCounter(this) + "");
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intentBroadcast);

        deviceToken = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Connector connector = Connector.getFirebaseConnector();
        ServerCommunicator.getWebHook(connector, deviceToken);
        connector.setOnRequestResponseListener(this);

        // Now, once this dummy activity starts send a broad cast to your parent activity and finish the pending activity
        //(remember you need to register your broadcast action here to receive).
        BroadcastReceiver call_method = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action_name = intent.getAction();
                if (action_name.equals("call_method")) {
                    // call your method here and do what ever you want.

                    deviceToken = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

                    Log.e(Utils.getTag()+" " , " BroadcastReceiver deviceToken---- "+ deviceToken );

                  /*  Connector connector = Connector.getFirebaseConnector();
                    ServerCommunicator.getWebHook(connector, deviceToken);
                    connector.setOnRequestResponseListener(context);*/
                }
            };
        };
        registerReceiver(call_method, new IntentFilter("call_method"));
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(NotificationManager notificationManager) {
        CharSequence adminChannelName = "New notification";
        String adminChannelDescription = "Device to devie notification";

        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(UCLICKS_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }
    }



    @Override
    public void onAddMoreResponse(WebResponse webResponse) {

    }

    @Override
    public void onHttpResponse(WebResponse webResponse) {
        if (webResponse instanceof BasicResponse) {
            BasicResponse responseBody = (BasicResponse) webResponse;

            Log.i(TAGC + " " , " responseBody getChecksumId=" + responseBody.getStatus());
            Log.i(TAGC + " " , " responseBody getChecksumId=" + responseBody.getMessage());
        }
    }

    @Override
    public void onUploadComplete(WebResponse webResponse) {

    }

    @Override
    public void onVFRClientException(WebErrorResponse edErrorData) {
        Log.e(Utils.getTag()+" " , " edErrorData.getMessage() ="+ edErrorData.getMessage());
    }

    @Override
    public void onAuthException() {

    }

    @Override
    public void onNoConnectivityException(String message) {

    }

    @Override
    public void onNoCachedDataAvailable() {

    }
}
