package com.example.videoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;
import java.util.zip.Inflater;

public class customAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private Context context;
    private List<File> files;
    private  SelectListener listener;

    public customAdapter(Context context, List<File> files,SelectListener listener) {
        this.context = context;
        this.files = files;
        this.listener =listener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtView.setText(files.get(position).getName());
        holder.txtView.setSelected(true);

        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(files.get(position).getAbsolutePath(), MediaStore.Images.Thumbnails.MINI_KIND);

        holder.imgThumbnail.setImageBitmap( thumb);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFileClicked(files.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return files.size();
    }
}
