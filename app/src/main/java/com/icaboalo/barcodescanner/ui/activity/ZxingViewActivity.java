package com.icaboalo.barcodescanner.ui.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ZxingViewActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        mScannerView.setAutoFocus(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("CODE", result.getText());
        setResult(200, resultIntent);
        finish();
    }
}
