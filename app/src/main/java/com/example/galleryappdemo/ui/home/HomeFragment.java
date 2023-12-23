package com.example.galleryappdemo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.galleryappdemo.Adapters.rvAdapter;
import com.example.galleryappdemo.Interfaces.ApiInterface;
import com.example.galleryappdemo.Interfaces.Pojo;
import com.example.galleryappdemo.Interfaces.Retrofit;
import com.example.galleryappdemo.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private int page = 1;

    private int pagesize = 30;
    private boolean isLoading ;
    private boolean isLastPage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // code here

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        binding.rvphoto.setLayoutManager(gridLayoutManager);
        Listingdata(30);

        binding.rvphoto.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage){
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= pagesize){
                        page++;
                        Listingdata(page);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return root;
    }


    private void Listingdata(int page) {
        isLoading = true;
        ApiInterface apiInterface = Retrofit.getRetrofit().create(ApiInterface.class);
        Call<Pojo> listingDataCall = apiInterface.getData(page);
        binding.progressBar.setVisibility(View.VISIBLE);

        listingDataCall.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Data success", Toast.LENGTH_SHORT).show();
                    Pojo.Photos photos = response.body().getPhotos();
                    List<Pojo.Photo> photoList = photos.getPhoto();
                    binding.progressBar.setVisibility(View.GONE);
                    isLoading = false;

                    // If it's the first page, create a new adapter
                    if (page == 1) {
                        rvAdapter adapter = new rvAdapter(photoList, rvAdapter.AdapterType.SEARCH);
                        binding.rvphoto.setAdapter(adapter);
                    } else {
                        // If it's not the first page, add new data to the existing adapter
                        rvAdapter adapter = (rvAdapter) binding.rvphoto.getAdapter();

                        // Check if the adapter is null, and initialize it
                        if (adapter == null) {
                            adapter = new rvAdapter(new ArrayList<>(), rvAdapter.AdapterType.SEARCH);
                            binding.rvphoto.setAdapter(adapter);
                        }

                        // Add new photos to the adapter
                        adapter.addPhotos(photoList);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}