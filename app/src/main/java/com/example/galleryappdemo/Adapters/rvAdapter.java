package com.example.galleryappdemo.Adapters;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.galleryappdemo.Interfaces.Pojo;
import com.example.galleryappdemo.R;
import com.example.galleryappdemo.databinding.RvImgBinding;

import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.MyViewHolder> {

    private List<Pojo.Photo> photoList;

    public rvAdapter(List<Pojo.Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvImgBinding binding = RvImgBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Log the image URL for debugging
        Log.d("ImageLoading", "Loading image: " + photoList.get(position).getUrlS());

//        Glide.with(holder.itemView.getContext())
//                .load(photoList.get(position).getUrlS())
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_menu_gallery)
//                .into(holder.binding.photoimg);

        Glide.with(holder.itemView.getContext())
                .load(photoList.get(position).getUrlS())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_menu_gallery)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("Glide", "Image load failed", e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.binding.photoimg);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RvImgBinding binding;

        public MyViewHolder(@NonNull RvImgBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
