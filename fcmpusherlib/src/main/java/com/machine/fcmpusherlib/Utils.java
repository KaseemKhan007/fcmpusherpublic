package com.machine.fcmpusherlib;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.machine.fcmpusherlib.fcm.MySingleton;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class Utils {
    private static final String TAGC = Utils.class.getName();

    private static KProgressHUD hud;


    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0 || str.equals("null");
    }

    public static void subscribeToTopic(Context context,String subscribeToTopic)
    {
        FirebaseApp.initializeApp(context);

        FirebaseMessaging.getInstance().subscribeToTopic(subscribeToTopic+"");
    }


    public static void sendNotification(Context context, JSONObject notification) {

         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constants.FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(Utils.getTag()+" ", " onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(Utils.getTag()+" ", "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization",Constants.serverKey);
                params.put("Content-Type", Constants.contentType);
                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public static String getNotificationPayload(Context context, String title, String message, String images, String eventType)
    {
        String payloadBody = "";
        /// {body={"title":"Requested category succesfully Accepted ","message":"Your undefined Category request has been Accepted","eventType":"AddedCategory"}}

        JsonObject notificationJson = new JsonObject();

        notificationJson.addProperty("title", "" + title);
        notificationJson.addProperty("message",""+ message );
        notificationJson.addProperty("eventType", "" + eventType);
        notificationJson.addProperty("images", "" + images);


        Log.e(TAGC + " " , " getNotificationJson jsonObject ="+ notificationJson);

        payloadBody =  "{body="+notificationJson+"}";

        Log.e(TAGC + " " , " Payload jsonObject =:"+ "{body="+notificationJson+"}");

        return payloadBody;
    }


    public static void showProgressBar(Context context) {
        try {
            if (hud != null) {
                hud.dismiss();
            }
            hud = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideProgressBar() {
        try {
            if (hud != null) {
                hud.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void showSuccessAlert(Context context, String message, String title, String confirmText, int errorType) {
        if (context != null) {
            SweetAlertDialog pDialog = new SweetAlertDialog(context, errorType);
            pDialog.setCancelable(true);
            pDialog.setCanceledOnTouchOutside(true);
    /* new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
             .setCancelable(true)*/
            pDialog
                    .setTitleText(title)
                    .setContentText(message)
                    .setConfirmText(confirmText)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }
    }


    public static boolean isBlankOrNull(String s) {
        return s == null || s.trim().equals("") || s.trim().equals("null");
    }

    public static boolean isUriBlankOrNull(Uri s) {
        return s == null || s.equals(Uri.EMPTY);
    }

    public static String isStringBlankOrNull(String s) {
        String blankString = "";
        if (s == null || s.trim().equals("") || s.trim().equals("null")) {
            return blankString;
        } else {
            blankString = s;
        }
        return blankString;
    }

    public static int isIntNull(Integer s) {
        Integer value = s;
        if (s == null) {
            value = 0;
        }
        return value;
    }

    public static Gson getGson() {
        JsonSerializer<Date> ser = new JsonSerializer<Date>() {
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        };
        JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return json == null ? null : ISO8601.getDateInUTC(json.getAsString());
                } catch (ParseException e) {
                    return null;
                }
            }
        };

        UserDeserializer deserUserMap = new UserDeserializer();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, ser)
                .registerTypeAdapter(Date.class, deser)
                .registerTypeAdapter(UserMap.class, deserUserMap)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return gson;
    }

    static class UserDeserializer implements JsonDeserializer<UserMap> {
        public UserMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                String val = json.getAsString();
                UserMap map = val == null ? null : (getPrivateGson().fromJson(val, UserMap.class));
                return map;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }


    public static String getISOAbsoluteDate() {
        // ISO-8601 combined date and time format
        //2021-10-28'T'
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(new Date());
    }


    public static String getTag() {
        String tag = "";
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        for (int i = 0; i < ste.length; i++) {
            if (ste[i].getMethodName().equals("getTag")) {
                tag = "(" + ste[i + 1].getFileName() + ":" + ste[i + 1].getLineNumber() + ")";
            }
        }
        return tag;
    }


    private static Gson getPrivateGson() {
        JsonSerializer<Date> ser = new JsonSerializer<Date>() {
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        };
        JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return json == null ? null : ISO8601.getDateInUTC(json.getAsString());
                } catch (ParseException e) {
                    return null;
                }
            }
        };
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, ser).registerTypeAdapter(Date.class, deser).setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return gson;
    }
}
