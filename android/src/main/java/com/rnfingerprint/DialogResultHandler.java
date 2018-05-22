package com.rnfingerprint;

import com.facebook.react.bridge.Callback;

import android.util.Log;

public class DialogResultHandler implements FingerprintDialog.DialogResultListener {
    private Callback errorCallback;
    private Callback successCallback;
    private boolean invoked = false;

    public DialogResultHandler(Callback reactErrorCallback, Callback reactSuccessCallback) {
      errorCallback = reactErrorCallback;
      successCallback = reactSuccessCallback;
    };

    @Override
    public void onAuthenticated() {
      FingerprintAuthModule.inProgress = false;
      if (!invoked) {
          successCallback.invoke("Successfully authenticated.");
          invoked = true;
      }
    }
    @Override
    public void onError(String errorString) {
      FingerprintAuthModule.inProgress = false;
      if (!invoked) {
          errorCallback.invoke(errorString);
          invoked = true;
      }
    }
    @Override
    public void onCancelled() {
      FingerprintAuthModule.inProgress = false;
      if (!invoked) {
          errorCallback.invoke("cancelled");
          invoked = true;
      }
    }
}

