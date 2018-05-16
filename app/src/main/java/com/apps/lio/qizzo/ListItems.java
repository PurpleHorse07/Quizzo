package com.apps.lio.qizzo;

/**
 * Created by nEW u on 13-10-2017.
 */

public class ListItems {

    public String Question;
    public String[] Options;

    public ListItems(String question, String[] options) {
        Question = question;
        Options = options;
    }

    public String getQuestion() {
        return Question;
    }

    public String[] getOptions() {
        return Options;
    }
}
