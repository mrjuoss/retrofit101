package com.mrjuoss.udacity.retrofit101.interfaces;

import com.mrjuoss.udacity.retrofit101.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();
}
