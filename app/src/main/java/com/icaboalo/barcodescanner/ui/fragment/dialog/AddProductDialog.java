package com.icaboalo.barcodescanner.ui.fragment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.icaboalo.barcodescanner.R;
import com.icaboalo.barcodescanner.io.model.ProductApiModel;
import com.icaboalo.barcodescanner.ui.DialogButtonOnClick;

/**
 * Created by icaboalo on 23/06/16.
 */
public class AddProductDialog extends DialogFragment {

    DialogButtonOnClick mButtonClick;

    EditText productName, productPrice;

    public static AddProductDialog newInstance(){
        return new AddProductDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mButtonClick = ((DialogButtonOnClick) context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("NOT FOUND");
        View view = View.inflate(getActivity(), R.layout.dialog_add_product, null);
        alertDialog.setView(view);

        productName = (EditText) view.findViewById(R.id.product_name_input);
        productPrice = (EditText) view.findViewById(R.id.product_price_input);

        alertDialog.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProductApiModel product = new ProductApiModel();
                product.setName(productName.getText().toString());

                mButtonClick.onDialogPositiveClick(product, "ADD_PRODUCT");
                dialogInterface.dismiss();
            }
        });
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mButtonClick.onDialogNegativeClick(null, "CANCEL");
                dialogInterface.dismiss();
            }
        });
        return alertDialog.create();
    }
}
