package com.a21vianet.library.extdialog;

/**
 * Created by quincysx on 17-6-2.
 */

public abstract class OnClickCallback {
    public abstract void onConfirm(BirdDialog dialog);

    public void onCancel(BirdDialog dialog) {
        dialog.dismiss();
    }
}
