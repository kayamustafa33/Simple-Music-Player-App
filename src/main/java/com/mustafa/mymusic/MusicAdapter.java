package com.mustafa.mymusic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mustafa.mymusic.databinding.RecylerRowLayoutBinding;
import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> {

    ArrayList<StoreMusic> musicArrayList;

    public MusicAdapter(ArrayList<StoreMusic> musicArrayList) {
        this.musicArrayList = musicArrayList;
    }

    @NonNull
    @Override
    public MusicAdapter.MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecylerRowLayoutBinding recylerRowLayoutBinding = RecylerRowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MusicHolder(recylerRowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.MusicHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.musicName.setText(musicArrayList.get(position).musicName);
        holder.binding.musicName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.binding.getRoot().getContext(),MusicPlayerActivity.class);
                intent.putExtra("musicName",musicArrayList.get(position).musicName);
                intent.putExtra("music",musicArrayList.get(position).music);
                holder.binding.getRoot().getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return musicArrayList.size();
    }

    public class MusicHolder extends RecyclerView.ViewHolder {

        private RecylerRowLayoutBinding binding;

        public MusicHolder(@NonNull RecylerRowLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
