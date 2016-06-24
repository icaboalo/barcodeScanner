package com.icaboalo.barcodescanner.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.icaboalo.barcodescanner.R;
import com.icaboalo.barcodescanner.io.ApiClient;
import com.icaboalo.barcodescanner.io.model.ProductApiModel;
import com.icaboalo.barcodescanner.ui.DialogButtonOnClick;
import com.icaboalo.barcodescanner.ui.adapter.ProductRecyclerAdapter;
import com.icaboalo.barcodescanner.ui.fragment.dialog.AddProductDialog;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogButtonOnClick{

    Button mScanButtonApp;
    TextView mCodeText;

    RecyclerView mProductRecycler;
    ProductRecyclerAdapter productRecyclerAdapter;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mScanButtonApp = (Button) findViewById(R.id.button_scan_from_app);
        mScanButtonApp.setOnClickListener(this);

        mProductRecycler = (RecyclerView) findViewById(R.id.product_recycler);
        setupProductRecycler();
    }

    void setupProductRecycler(){
        mProductRecycler.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerAdapter = new ProductRecyclerAdapter(this);
        mProductRecycler.setAdapter(productRecyclerAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_scan_from_app:
                Intent startScan = new Intent(MainActivity.this, ZxingViewActivity.class);
                startActivityForResult(startScan, 200);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case IntentIntegrator.REQUEST_CODE:
                IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

                if (scanningResult != null){
                    String scanContent = scanningResult.getContents();
                    String scanFormat = scanningResult.getFormatName();
                    mCodeText.setText(scanContent + " " + scanFormat);
                } else {
                    Toast.makeText(MainActivity.this, "No scan data!", Toast.LENGTH_SHORT).show();
                }
                break;

            case 200:
                if (data != null){
                    String barcode = data.getStringExtra("CODE");
                    barcode = barcode.substring(0, barcode.length()-1);
                    progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Searching...");
                    progressDialog.show();
                    getProduct(barcode);
                } else {
                    Toast.makeText(MainActivity.this, "No scan data!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    void getProduct(final String barcode){
        Call<ProductApiModel> call = ApiClient.getApiService().getProduct(barcode);
        call.enqueue(new Callback<ProductApiModel>() {
            @Override
            public void onResponse(Call<ProductApiModel> call, Response<ProductApiModel> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    if (response.body() == null){
                        Log.d("RESPONSE", "null");
                        showDialog();
                    } else {
                        ProductApiModel product = response.body();
                        product.setCode(barcode);
                        productRecyclerAdapter.addProduct(product);
                    }
                } else {
                    try {
                        Log.d("RETROFIT_ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductApiModel> call, Throwable t) {

            }
        });
    }

    void showDialog(){
        AddProductDialog alertDialog = AddProductDialog.newInstance();
        alertDialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onDialogPositiveClick(@Nullable Object object, @NonNull String TAG) {
        if (object != null){
            if (TAG.equals("ADD_PRODUCT")){
                productRecyclerAdapter.addProduct((ProductApiModel) object);
            }
        }
    }

    @Override
    public void onDialogNeutralClick(@Nullable Object object, @NonNull String TAG) {

    }

    @Override
    public void onDialogNegativeClick(@Nullable Object object, @NonNull String TAG) {

    }
}
