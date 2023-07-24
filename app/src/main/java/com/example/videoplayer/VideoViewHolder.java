package com.example.videoplayer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgThumbnail;
    public TextView txtView;
    public CardView cardView;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
        txtView = itemView.findViewById(R.id.txtName);
        cardView= itemView.findViewById(R.id.main_container);
    }
}
