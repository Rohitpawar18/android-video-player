package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class video extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    List<File> fileList;

    File path=new File(System.getenv("EXTERNAL_STORAGE"));
    customAdapter customadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        displayFiles();
        askPermission();
    }
    private void askPermission(){
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                displayFiles();
            }
            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(video.this, "Storage Permission is Required!!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    private void displayFiles() {

        recyclerView=findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        fileList =new ArrayList<>();
        fileList .addAll(findVideos(path));
        customadapter =new customAdapter(this,fileList,this);
        customadapter.setHasStableIds(true);
        recyclerView.setAdapter(customadapter);

    }
    private ArrayList<File> findVideos(File file)
    {
        ArrayList<File> myVideo =new ArrayList<>();
        File[] allFiles =file.listFiles();

        for(File singleFile:allFiles){
            if(singleFile.isDirectory()&&singleFile.isHidden()){
                myVideo.addAll(findVideos(singleFile));

            } else if (singleFile.getName().toLowerCase().endsWith(".mp4") || singleFile.getName().toLowerCase().endsWith("mkv") || singleFile.getName().toLowerCase().endsWith("mov"))
            {

                myVideo.add(singleFile);
            }
        }
        return myVideo;
    }

    @Override
    public void onFileClicked(File file) {
        startActivity(new Intent(video.this, PlayerActivity.class)
                .putExtra("Video",file.getAbsolutePath()));

    }
}