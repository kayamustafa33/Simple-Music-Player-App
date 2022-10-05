package com.mustafa.mymusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.mustafa.mymusic.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<StoreMusic> storeMusicArrayList;

    StoreMusic storeMusic,storeMusic2,storeMusic3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        storeMusic = new StoreMusic(R.raw.neaygeceden,"Sezer Sarıgöz","Ne Ay Geceden");
        storeMusic2 = new StoreMusic(R.raw.hayallerim,"Ata Alabaş","Hayallerim");
        storeMusic3 = new StoreMusic(R.raw.birnefessigara,"Serkan Nişancı","Bir Nefes Sigara");

        storeMusicArrayList = new ArrayList<>();
        storeMusicArrayList.add(storeMusic);
        storeMusicArrayList.add(storeMusic2);
        storeMusicArrayList.add(storeMusic3);

        binding.recyclerRow.setLayoutManager(new LinearLayoutManager(this));
        MusicAdapter musicAdapter = new MusicAdapter(storeMusicArrayList);
        binding.recyclerRow.setAdapter(musicAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_music:{
                Toast.makeText(this, "Henüz yapım aşamasında!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}