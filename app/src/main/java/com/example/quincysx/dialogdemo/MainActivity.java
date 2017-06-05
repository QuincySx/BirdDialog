package com.example.quincysx.dialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.a21vianet.library.extdialog.BirdDialog;
import com.a21vianet.library.extdialog.OnClickCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        new BirdDialog.Builder(this)
                .setHeadDrawable(R.drawable.ico_w)
                .setContentText("请在终端APP打开我的二维码")
                .setConfirmText("扫一扫")
                .setCancelText("取消")
                .setOnCancelListener(new OnClickCallback() {
                    @Override
                    public void onConfirm(BirdDialog dialog) {

                    }
                })
                .build()
                .setBirdCancelable(false)
                .show();
    }
}
