package com.mifos.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by root on 13/2/18.
 */

@TargetApi(23)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private CancellationSignal cancellationSignal;
    private Context mContext;
    private ImageView mImageView;
    private boolean authenticationFlag;

    public FingerprintHandler(Context context,
                              View view) {
        mContext = context;
        mImageView = (ImageView) view;
        authenticationFlag = false;
    }

    public boolean getAuthenticationFlag() {
        return authenticationFlag;
    }

    public void setAuthenticationFlag(boolean authenticationFlag) {
        this.authenticationFlag = authenticationFlag;
    }

    public void startAuth(FingerprintManager fingerprintManager,
                          FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this,
                null);
        //return getAuthenticationFlag();
    }

    @Override
    public void onAuthenticationError(int errMsg,
                                      CharSequence errString) {
        Toast.makeText(mContext, "Authentication error\n" + errString,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(mContext, "Authentication failed",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationHelp(int messageID,
                                     CharSequence helpString) {
        Toast.makeText(mContext, helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult
                                                  authenticationResult) {
        Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
        setAuthenticationFlag(true);
    }
}
