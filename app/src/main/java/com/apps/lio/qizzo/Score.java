package com.apps.lio.qizzo;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    TextView Name, Score;
    MediaPlayer audio;
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Bundle prev = getIntent().getExtras();
        String N = prev.getString("name");
        int sc = prev.getInt("score");
        String S = sc + "/10";

        Name = (TextView) findViewById(R.id.name);
        Score = (TextView) findViewById(R.id.score);
        vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        Name.setText(N);
        Score.setText(S);

        if (sc == 0)
            audio = MediaPlayer.create(this, R.raw.zero);
        if (sc == 1)
            audio = MediaPlayer.create(this, R.raw.one);
        if (sc == 2)
            audio = MediaPlayer.create(this, R.raw.two);
        if (sc == 3)
            audio = MediaPlayer.create(this, R.raw.three);
        if (sc == 4)
            audio = MediaPlayer.create(this, R.raw.four);
        if (sc == 5)
            audio = MediaPlayer.create(this, R.raw.five);
        if (sc == 6)
            audio = MediaPlayer.create(this, R.raw.six);
        if (sc == 7)
            audio = MediaPlayer.create(this, R.raw.seven);
        if (sc == 8)
            audio = MediaPlayer.create(this, R.raw.eight);
        if (sc == 9)
            audio = MediaPlayer.create(this, R.raw.nine);
        if (sc == 10)
            audio = MediaPlayer.create(this, R.raw.ten);

        thread(sc);
        audio.start();

    }

    public void thread(final int S) {

        Thread T = new Thread() {
            @Override
            public void run() {
                vib.vibrate(S * 1000);
            }
        };
        T.start();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(this);
        back.setTitle("What do you want?");
        back.setPositiveButton("Exit App!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        back.setNeutralButton("Start Again!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent start = new Intent(Score.this, Welcome.class);
                startActivity(start);
                finish();
            }
        });
        back.setNegativeButton("Nothing!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        back.show();
    }
}
