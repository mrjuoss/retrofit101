package com.mrjuoss.udacity.retrofit101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import com.mrjuoss.udacity.retrofit101.interfaces.JsonPlaceHolderApi;
import com.mrjuoss.udacity.retrofit101.models.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPost();
    }
    private void getPost() {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "4");
        params.put("_sort", "id");
        params.put("_order", "desc");

        //Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{1,3,4}, "id", "desc");
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(params);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful())  {
                    textViewResult.setText("Code : "+response.code());
                    return;
                }


                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID : "+ post.getId() + "\n";
                    content += "User ID : "+ post.getUserId() + "\n";
                    content += "Title : "+ post.getTitle() + "\n";
                    content += "Text : "+post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
