package com.apps.lio.qizzo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    ListView list;
    TextView ques;
    ArrayList<ListItems> data;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        list = (ListView) findViewById(R.id.list);
        data = new ArrayList<>();

        ques = (TextView) findViewById(R.id.questions);

        final ItemAdapter Adapt = new ItemAdapter(this, R.layout.question_card, data);

        String[] Q = {"Which part of database holds only one info?",
                "Decade, when first transatlantic radio broadcast occur is:",
                ".MOV extension refers usually to what kind of file?",
                "Who is largely responsible for breaking the German Enigma Codes, created a test that provided foundation of AI?",
                "Who developed yahoo?",
                "Most common format for a home video recorder is VHS. VHS stands for..",
                "Blue is what number on resistor color code?",
                "In which decade was the transistor invented?",
                "Which company created the most used networking software in the 1980's",
                "What is the website code for Colombia?" };

        final String[] A = {"..,Report,Field,Record,File",
                "..,1850s,1860s,1870s,1890s",
                "..,Image,Animation/Movie,Audio,Document",
                "..,Alan Turing,Jeff Bezos,George Bool,Charles Babbage",
                "..,Dennis Ritchie-Ken Thompson,David Filo-Jerry Yang,Vint Cerf-Robert Kahn,Steve Case-Ken Thompson",
                "..,Video Home System,Very High Speed,Video Horizontal Standard,Voltage House Standard",
                "..,1,4,2,6",
                "..,1940s,1950s,1960s,1980s",
                "..,Microsoft,Sun,Novell,IBM",
                "..,CO,CM,CL,CB" };

        final int[] C = {2, 4, 2, 1, 2, 1, 4, 1, 3, 1};

        for (int i = 0; i < 10; i++) {
            String O[] = A[i].split(",");
            ListItems obj = new ListItems(Q[i], O);
            data.add(obj);
            list.setAdapter(Adapt);
        }
        ques.setText("Questions: 10");

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ok = new AlertDialog.Builder(Quiz.this);
                ok.setTitle("Are you sure to submit the answers?");
                ok.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int[] a = Adapt.Ans;
                        int score = 0;
                        for (int i : a) {
                            if (i == C[i])
                                score++;
                        }
                        Intent i = new Intent(Quiz.this, Score.class);
                        Bundle s = new Bundle();
                        s.putInt("score", score);
                        s.putString("name", getIntent().getStringExtra("name"));
                        i.putExtras(s);
                        startActivity(i);
                        finish();
                    }
                });
                ok.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ok.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(this);
        back.setTitle("Stuck?");
        back.setPositiveButton("Exit App!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        back.setNeutralButton("Carry On!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        back.setNegativeButton("Start Fresh!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent start = new Intent(Quiz.this, Welcome.class);
                startActivity(start);
                finish();
            }
        });
        back.show();
    }
}
