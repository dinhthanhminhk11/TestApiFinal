package com.example.testapifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.testapifinal.model.Comment;
import com.example.testapifinal.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvResuft;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResuft = (TextView) findViewById(R.id.tv_resuft);
        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//GET
//        getPost();
//            getComment(5);
//        getCommentByPostId(5);
//        getCommentByUrl("posts/1/comments");
//POST
//        createPost();
//        createPostByForUrlEncoded();

//PUT
        editPost();

//DELETE
//        deletePost();
    }

    private void getPost() {
        Call<List<Post>> callPost = jsonPlaceHolderApi.getPosts();
        callPost.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvResuft.setText("code ; " + response.code());
                }
                List<Post> listPost = new ArrayList<>();
                listPost = response.body();

                for (Post post : listPost) {
                    String content = "";
                    content += post.toString() + " \n";
                    tvResuft.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvResuft.setText(t.getMessage() + " error");
            }
        });
    }

    private void getComment(int postId) {
        Call<List<Comment>> callComment = jsonPlaceHolderApi.getCommentById(postId);
        callComment.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    tvResuft.setText("code ; " + response.code());
                }

                List<Comment> listPost = new ArrayList<>();
                listPost = response.body();

                for (Comment post : listPost) {
                    String content = "";
                    content += post.toString() + " \n";
                    tvResuft.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvResuft.setText(t.getMessage() + " error");
            }
        });
    }

    private void getCommentByPostId(int postId) {
        Call<List<Comment>> callComment = jsonPlaceHolderApi.getCommentByPostId(postId);
        callComment.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    tvResuft.setText("code ; " + response.code());
                }
                List<Comment> listPost = new ArrayList<>();
                listPost = response.body();

                for (Comment post : listPost) {
                    String content = "";
                    content += post.toString() + " \n";
                    tvResuft.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvResuft.setText(t.getMessage() + " error");
            }
        });
    }

    private void getCommentByUrl(String url) {
        Call<List<Comment>> callComment = jsonPlaceHolderApi.getCommentByUrl(url);
        callComment.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    tvResuft.setText("code ; " + response.code());
                }

                List<Comment> listPost = new ArrayList<>();
                listPost = response.body();

                for (Comment post : listPost) {
                    String content = "";
                    content += post.toString() + " \n";
                    tvResuft.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvResuft.setText(t.getMessage() + " error");
            }
        });
    }

    private void createPost() {
        Post post = new Post(23, "New Post", "New Post");
        Call<Post> callPost = jsonPlaceHolderApi.createPost(post);
        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvResuft.setText("code ; " + response.code());
                }
                Post post1 = response.body();
                String content = post1.toString() + " code " + response.code();
                tvResuft.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResuft.setText(t.getMessage() + " error");
            }
        });
    }

    private void createPostByForUrlEncoded() {
        Post post = new Post(23, "New Post", "New Post");
        Call<Post> callPost = jsonPlaceHolderApi.createPostByForUrlEncoded(post.getUserId(), post.getTitle(), post.getText());
        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvResuft.setText("code ; " + response.code());
                }
                Post post1 = response.body();
                String content = post1.toString() + " code " + response.code();
                tvResuft.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResuft.setText(t.getMessage() + " error");
            }
        });
    }

    private void editPost() {
        Post post = new Post(12, null, "edit post");

        Call<Post> call = jsonPlaceHolderApi.putPostHasHeader("abc",5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvResuft.setText("code ; " + response.code());
                }
                Post post1 = response.body();
                String content = post1.toString() + " code " + response.code();
                tvResuft.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResuft.setText(t.getMessage() + " error");
            }
        });
    }

    private void deletePost() {
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tvResuft.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tvResuft.setText(t.getMessage() + " error");
            }
        });
    }
}