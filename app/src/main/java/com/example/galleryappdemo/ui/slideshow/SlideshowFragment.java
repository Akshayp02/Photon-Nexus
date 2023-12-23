package com.example.galleryappdemo.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.galleryappdemo.Adapters.rvAdapter;
import com.example.galleryappdemo.Interfaces.ApiInterface;
import com.example.galleryappdemo.Interfaces.Retrofit;
import com.example.galleryappdemo.Interfaces.Search;
import com.example.galleryappdemo.R;
import com.example.galleryappdemo.databinding.FragmentSlideshowBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private rvAdapter adapter;
    private Search searchPhotoList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        binding.SearchrecyclerView.setLayoutManager(gridLayoutManager);
        performSearch("cat");

        return root;
    }

    private void performSearch(String searchText) {
        ApiInterface apiInterface = Retrofit.getRetrofit().create(ApiInterface.class);
        Call<Search> searchCall = apiInterface.searchPhotos(searchText);

        searchCall.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Search data fetched successfully", Toast.LENGTH_SHORT).show();

                    // Update the UI with the search results
                    updateUI(response.body());
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                // Handle network failure here (e.g., show RETRY snackbar)
                Toast.makeText(getContext(), "Network failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(Search search) {
        if (search != null && search.getPhotos() != null && search.getPhotos().getPhoto() != null) {
            // Check if the adapter is already set
            if (adapter == null) {
                // Create a single instance of rvAdapter
                adapter = new rvAdapter(search.getPhotos().getPhoto(), rvAdapter.AdapterType.SEARCH);

                // Set the adapter for the RecyclerView
                binding.SearchrecyclerView.setAdapter(adapter);
            } else {
                // Update existing adapter data
                adapter.addPhotos(search.getPhotos().getPhoto());
                adapter.hideLoading();

            }
        } else {
            // Log or handle the case when the data is null
            Log.e("UpdateUI", "Search data or photo list is null");
        }
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}