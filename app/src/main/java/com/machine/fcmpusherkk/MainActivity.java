package com.machine.fcmpusherkk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.machine.fcmpusherlib.Constants;
import com.machine.fcmpusherlib.Utils;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etSubscribeText;
    Button btnSubscribe;

    String TOPIC = "";
    String NOTIFICATION_TITLE = "";
    String NOTIFICATION_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initViewAndListener();

        Utils.subscribeToTopic( MainActivity.this,"kaseemkhantesting");

        Log.e(Utils.getTag() + " ", " subscribeToTopic ----- ");
    }

    public void initViewAndListener()
    {
        etSubscribeText = findViewById(R.id.etSubscribeText);
        btnSubscribe = findViewById(R.id.btnSubscribe);

        btnSubscribe.setOnClickListener(this);
    };

    void notification() {

        TOPIC = Constants.CONS_TOPICS + "kaseemkhantesting"; //topic must match with what the receiver subscribed to

        Log.e(Utils.getTag() + " ", " TOPIC_N =" + TOPIC);
        NOTIFICATION_TITLE = "SoftGrid Pvt. Ltd.";
        NOTIFICATION_MESSAGE = "This is softgrid test notification.";

        JSONObject notification = new JSONObject();
        try {


            JSONObject notifcationBody = new JSONObject(
                    Utils.getNotificationPayload(this,
                            NOTIFICATION_TITLE + "",
                            NOTIFICATION_MESSAGE + "",
                             "",
                             "ForAll"));

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e(Utils.getTag() + " ", "onCreate: " + e.getMessage());
        }
        Utils.sendNotification(MainActivity.this, notification);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.btnSubscribe)
        {
            notification();
        }
    }
}