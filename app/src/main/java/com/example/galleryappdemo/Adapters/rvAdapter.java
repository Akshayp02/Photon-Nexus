package com.example.galleryappdemo.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.galleryappdemo.Interfaces.Pojo;
import com.example.galleryappdemo.Interfaces.Search;
import com.example.galleryappdemo.R;
import com.example.galleryappdemo.databinding.RvImgBinding;

import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    private List<Pojo.Photo> photoList;
    private List<Search.Photo> searchPhotoList;
    private boolean isLoading = false;

    public enum AdapterType {
        POJO, SEARCH
    }

    private final AdapterType adapterType;

    // Constructor for POJO adapter
    public rvAdapter(List<Pojo.Photo> pojoPhotoList, AdapterType search) {
        this.photoList = pojoPhotoList;
        this.searchPhotoList = null; // Ensure searchPhotoList is initialized
        this.adapterType = AdapterType.POJO;
    }

    // Constructor for SEARCH adapter
    public rvAdapter(List<Search.Photo> searchPhotoList, AdapterType adapterType) {
        this.searchPhotoList = searchPhotoList;
        this.photoList = null; // Ensure photoList is initialized
        this.adapterType = adapterType;
    }

    // Add photos to the existing list
    public void addPhotos(List<Pojo.Photo> newPhotos) {
        photoList.addAll(newPhotos);
        notifyDataSetChanged();
    }

    // Show loading item
    public void showLoading() {
        isLoading = true;
        notifyDataSetChanged();
    }

    // Hide loading item
    public void hideLoading() {
        isLoading = false;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            RvImgBinding binding = RvImgBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MyViewHolder(binding);
        } else {
            // Inflate your loading item layout here
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_img, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;

            // Bind your photo data
            Glide.with(holder.itemView.getContext())
                    .load(photoList.get(position).getUrlS())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.binding.photoimg);

        } else if (viewHolder instanceof LoadingViewHolder) {
            // Handle loading item UI if needed
        }
    }

    @Override
    public int getItemCount() {
        if (adapterType.equals(AdapterType.POJO)) {
            return photoList != null ? photoList.size() : 0;
        } else if (adapterType.equals(AdapterType.SEARCH)) {
            return searchPhotoList != null ? searchPhotoList.size() : 0;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == photoList.size() - 1 && isLoading) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    // ViewHolder for regular item
    public class MyViewHolder extends RecyclerView.ViewHolder {

        RvImgBinding binding;

        public MyViewHolder(@NonNull RvImgBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    // ViewHolder for loading item
    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(View view) {
            super(view);
        }
    }

    public AdapterType getAdapterType() {
        return adapterType;
    }
}
