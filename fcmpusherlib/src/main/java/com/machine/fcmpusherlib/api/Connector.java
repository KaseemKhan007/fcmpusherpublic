package com.machine.fcmpusherlib.api;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.machine.fcmpusherlib.Constants;
import com.machine.fcmpusherlib.Utils;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Connector {

    private static Connector connector = null;
    private static OkHttpClient client = null;
    public static final int METHOD_POST = 1001;
    public static final int METHOD_PUT = 1002;
    public static final int METHOD_DELETE = 1003;
    public static final int METHOD_GET = 1004;
    public static final String JSON_CONTENT_TYPE = "application/json";

    private OnRequestResponseListener onRequestResponseListener;

    public void setOnRequestResponseListener(OnRequestResponseListener onRequestResponseListener) {
        this.onRequestResponseListener = onRequestResponseListener;
    }

    public OnRequestResponseListener getOnRequestResponseListener() {
        return onRequestResponseListener;
    }

    public static Connector getFirebaseConnector() {
        if (connector == null) {
            connector = new Connector();
            client = new OkHttpClient();

        }
        return connector;
    }

    public <T> void callServer(String URL, int method, String payLoad, final Class<T> resultClass) {

        String finalURL = Constants.API_URL + URL;

        Request request = new Request.Builder()
                .url(finalURL)
                .header("Content-Type", "application/json") // .addHeader("Content-Type", "application/json")
                .header("Accept", "*/*")
                .build();

        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        Log.d("8989", " Url " + finalURL);
        Log.d("9898", " Payload: " + payLoad);
        switch (method) {
            case METHOD_GET:

                break;
            case METHOD_POST:
               // if(!Utils.isBlankOrNull(payLoad)){
                    RequestBody body = RequestBody.create(JSON, payLoad);
                    request = new Request.Builder()
                            .url(finalURL)
                            .header("Content-Type", "application/json")
                            .header("Accept", "*/*")
                            .post(body)
                            .build();
               // }
                break;
            case METHOD_PUT:
                break;
            case METHOD_DELETE:
                break;
        }


        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response)
                    throws IOException {
                try {
                    JSONObject responseObj = new JSONObject(response.body().string());
                    Log.i(Utils.getTag() + " ", " responseObj: " + responseObj);

                    WebResponse webResponse = (WebResponse) Utils.getGson().fromJson(responseObj.toString(), resultClass);
                    onRequestResponseListener.onHttpResponse(webResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call call, IOException e) {
                WebErrorResponse response = new WebErrorResponse();
                response.setStatus(0);
                response.setMessage(e.getMessage());
                onRequestResponseListener.onVFRClientException(response);
                Log.i(Utils.getTag() + " ", " TAG API failed: " + e.getLocalizedMessage());
            }
        });
    }
}
