package com.apps.lio.qizzo;

import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<ListItems> {

    public ArrayList<ListItems> list;
    public Context context;
    public int[] Ans = new int[10];

    public ItemAdapter(Context context, int resource, ArrayList<ListItems> objects) {
        super(context, resource, objects);
        list = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v;

        if (convertView != null)
            v = convertView;
        else                    //If view was null
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card, parent, false);

        TextView Q = (TextView) v.findViewById(R.id.ques);
        String S1 = list.get(position).getQuestion();
        Q.setText(S1);

        final Spinner A = (Spinner) v.findViewById(R.id.opt);
        String[] S2 = list.get(position).getOptions();
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, S2);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        A.setAdapter(adapter);

        final int p = position;

        A.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Vibrator vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(100);
                }
                Ans[p] = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }
}
