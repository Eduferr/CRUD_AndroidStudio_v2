package com.eduferr2803gmail.agendaclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by Eduferr on 24/05/2017.
 */

public class SplashActivity extends Activity {

    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        //Barra de Progresso
        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    for (int i = 0; i <= 100; i++) {
                        final int progress = i;
                        //Atualiza a barra de progresso
                        runOnUiThread(new Thread() {
                            public void run() {
                                mProgress.setProgress(progress);
                            }
                        });
                        Thread.sleep(35);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }
}