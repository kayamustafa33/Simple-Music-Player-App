package com.mustafa.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.mustafa.mymusic.databinding.ActivityMusicPlayerBinding;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    private ActivityMusicPlayerBinding binding;
    AudioManager audioManager;

    String musicName;
    private Handler myHandler = new Handler();
    int music = 0;
    MediaPlayer mediaPlayer;
    TextView musicNameText,startTextDk,finishTextDk;
    ImageView stopImage;
    SeekBar seekBar;
    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicPlayerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        musicNameText = findViewById(R.id.musicNameText);
        stopImage = findViewById(R.id.stop);
        seekBar = findViewById(R.id.seekBar);
        startTextDk = findViewById(R.id.startTextDk);
        finishTextDk = findViewById(R.id.finishTextDk);

        seekBar.setClickable(false);
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        Intent intent = getIntent();
        if(intent != null){
            musicName = intent.getStringExtra("musicName");
            music = intent.getIntExtra("music",0);
        }

        if(musicName != null){
            musicNameText.setText(musicName);
        }

        if(music != 0){
            mediaPlayer = MediaPlayer.create(this, music);
            if(mediaPlayer.isPlaying()){
                stopImage.setImageResource(R.drawable.stop);
            }
        }
    }

    public void stopMusic(View view){

        if(!mediaPlayer.isPlaying()){

            stopImage.setImageResource(R.drawable.stop);
            mediaPlayer = MediaPlayer.create(this,music);
            mediaPlayer.start();

            seekBar.setClickable(false);
            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();

            if (oneTimeOnly == 0) {
                seekBar.setMax((int) finalTime);
                oneTimeOnly = 1;
            }

            startTextDk.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    startTime))));

            finishTextDk.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    finalTime)))
            );

            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(UpdateSongTime,1000);

        }else if(mediaPlayer.isPlaying()){
            startTime = 0;
            stopImage.setImageResource(R.drawable.startmusic);
            seekBar.setProgress((int)startTime);
            seekBar.setClickable(false);
            mediaPlayer.pause();
        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            startTextDk.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 1000);
        }
    };
}