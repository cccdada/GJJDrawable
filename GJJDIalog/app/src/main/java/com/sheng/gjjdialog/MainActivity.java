package com.sheng.gjjdialog;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GJJDrawable gjjDrawable;
    private TextView progress;

    private int progressInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gjjDrawable.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if ( progressInt >= 10) {
                            gjjDrawable.a(1.0f);
                        }
                    }
                },2000);
            }
        });
        progress = ((TextView) findViewById(R.id.progress));
    }

    private void start() {
        this.gjjDrawable = new GJJDrawable(this);
        this.gjjDrawable.a(new GJJDrawable.OnProgressListener() {
            public void a(int paramAnonymousInt) {
                progressInt = paramAnonymousInt;
                progress.setText(String.valueOf(paramAnonymousInt));
            }

            public void a(boolean paramAnonymousBoolean) {
                if (gjjDrawable.isRunning())
                    gjjDrawable.a();
            }
        });
        ((ImageView) findViewById(R.id.progress_animation)).setImageDrawable(gjjDrawable);

        gjjDrawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ( progressInt >= 10) {
                    gjjDrawable.a(1.0f);
                }
            }
        },2000);

    }

}
