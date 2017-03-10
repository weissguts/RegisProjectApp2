package com.example.owner.regisprojmgmtapp2.domain;

/**
 * Created by Owner on 3/9/2017.
 */

import android.support.v7.widget.CardView;
import android.widget.EditText;

/**
 * Created by Owner on 2/18/2017.
 */

public class Products {
    public String project;
    public String taskOne;
    public String taskTwo;



    public Products(String project, String taskOne, String taskTwo) {
        this.project = project;
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;


    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTaskOne() {
        return taskOne;
    }

    public void setTaskOne(String taskOne) {
        this.taskOne = taskOne;
    }

    public String getTaskTwo() {
        return taskTwo;
    }

    public void setTaskTwo(String taskTwo) {
        this.taskTwo = taskTwo;
    }
}
