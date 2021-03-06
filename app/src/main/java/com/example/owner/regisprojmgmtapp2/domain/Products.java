package com.example.owner.regisprojmgmtapp2.domain;

/**
 * Created by Owner on 3/9/2017.
 * Object class to hold items for project card.
 */

import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Owner on 2/18/2017.
 */



public class Products {
    public String project;
    public String taskOne;
    public String taskTwo;


    //So my object contains 3 strings - on cardview - project, taskOne, taskTwo.
    public Products(String project, String taskOne, String taskTwo) {
        this.project = project;
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;
    }

    public Products() {

    }

    public String getProject() {
        return project;
    }

    /**
     * Getters and Setters.
     * @param project
     */
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
