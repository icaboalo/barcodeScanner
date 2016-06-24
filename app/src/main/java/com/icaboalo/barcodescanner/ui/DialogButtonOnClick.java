package com.icaboalo.barcodescanner.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by icaboalo on 23/06/16.
 */
public interface DialogButtonOnClick {
    void onDialogPositiveClick(@Nullable Object object, @NonNull String TAG);
    void onDialogNeutralClick(@Nullable Object object, @NonNull String TAG);
    void onDialogNegativeClick(@Nullable Object object, @NonNull String TAG);
}
