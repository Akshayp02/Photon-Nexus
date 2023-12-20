package com.example.galleryappdemo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.galleryappdemo.Adapters.rvAdapter;
import com.example.galleryappdemo.ApiInterface;
import com.example.galleryappdemo.Pojo;
import com.example.galleryappdemo.Retrofit;
import com.example.galleryappdemo.databinding.FragmentHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // code here

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.rvphoto.setLayoutManager(linearLayoutManager);
        Listingdata();

        return root;
    }
    private void Listingdata(){
        // geting data from api

        ApiInterface apiInterface = Retrofit.getRetrofit().create(ApiInterface.class);
        Call<Pojo> Listingdata = apiInterface.getData();
        Listingdata.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "data success", Toast.LENGTH_SHORT).show();
                    // on response work here
                    Pojo.Photos photos = response.body().getPhotos();
                    List<Pojo.Photo> photoList = photos.getPhoto();

                    rvAdapter adapter = new rvAdapter(photoList);
                    binding.rvphoto.setAdapter(adapter);




                }
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Toast.makeText(getContext(), "Somthin wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}