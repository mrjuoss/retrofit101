package com.mrjuoss.udacity.retrofit101.models;

import com.google.gson.annotations.SerializedName;

public class Post {

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    private int id;
    private int userId;
    private String title;
    @SerializedName("body")
    private String text;
}
