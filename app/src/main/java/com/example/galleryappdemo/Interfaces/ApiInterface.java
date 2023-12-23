package com.example.galleryappdemo.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
    Call<Pojo> getData(
            @Query("page") int page
    );

    @GET("?method=flickr.photos.search&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
    Call<Search> searchPhotos(@Query("text") String searchText
    );
}
