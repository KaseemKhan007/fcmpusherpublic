package com.machine.fcmpusherlib.api;



public interface OnRequestResponseListener {
    void onAddMoreResponse(WebResponse webResponse);

    void onHttpResponse(WebResponse webResponse);

    void onUploadComplete(WebResponse webResponse);

    void onVFRClientException(WebErrorResponse edErrorData);

    void onAuthException();

    void onNoConnectivityException(String message);

    void onNoCachedDataAvailable();
}
