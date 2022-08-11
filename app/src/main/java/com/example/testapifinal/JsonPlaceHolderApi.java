package com.example.testapifinal;


import com.example.testapifinal.model.Comment;
import com.example.testapifinal.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    @GET("posts")
        // phuongư thức get đờng nghĩ với việc lấy từ link https://jsonplaceholder.typicode.com/posts
    Call<List<Post>> getPosts();


    //    //kiểu truyền thống không có tham số
//    @GET("posts/1/comments")
//    Call<List<Comment>> getComment();
    @GET("posts/{id}/comments")
    Call<List<Comment>> getCommentById(@Path("id") int postId);// path là gắn value cho data https://jsonplaceholder.typicode.com/posts/1/comments


    //https://jsonplaceholder.typicode.com/comments?postId=1
    // get như  bình thường mỗi tội là thêm thuộc tính postid
    @GET("comments")
    Call<List<Comment>> getCommentByPostId(
            @Query("postId") int postId
            //cách kết hợp các thuộc tính
//            ,
//                    @Query("_sort") String sort,
//                    @Query("_order") String order
    );

    //trường hợp get theo url
    @GET
    Call<List<Comment>> getCommentByUrl(@Url String url);

    //POST thuowng
    @POST("posts")
    Call<Post> createPost(@Body Post post);


    //POST URL
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostByForUrlEncoded(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    //PUT thuong
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

    //PUT header

    @Headers({"Static-Header1: 123", "Static-Header1: 456"})
    @PUT("posts/{id}")
    Call<Post> putPostHasHeader(
            @Header("Dynamic-Header") String header,
            @Path("id") int id,
            @Body Post post
    );

}
