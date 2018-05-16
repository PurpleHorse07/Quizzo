package com.apps.lio.qizzo;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    MediaPlayer sound;
    EditText name;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        thread();

        name = (EditText) findViewById(R.id.name);

        start = (Button) findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String N = name.getText().toString().trim();
                if (!N.equals("")) {
                    Bundle b = new Bundle();
                    b.putString("name", N);
                    Intent quiz = new Intent(Welcome.this, Quiz.class);
                    quiz.putExtras(b);
                    startActivity(quiz);
                    finish();
                } else {
                    Toast.makeText(Welcome.this, "ENTER A NAME", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void thread() {
        Thread T = new Thread() {
            @Override
            public void run() {
                sound = MediaPlayer.create(Welcome.this, R.raw.welcome);
                sound.start();
                while (sound.isPlaying()) {
                }
                sound = MediaPlayer.create(Welcome.this, R.raw.enteryourname);
                sound.start();
            }
        };
        T.start();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(this);
        back.setTitle("Are You Sure To Exit?");
        back.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        back.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        back.show();
    }
}
