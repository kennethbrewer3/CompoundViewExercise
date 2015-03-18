package com.mobileappscompany.training.compoundviewexercise;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Android1 on 3/17/2015.
 */
public class SmilingProgressBar extends LinearLayout {
    private final TextView textViewTitle;
    private final ImageView smileView;
    private final ProgressBar progressBar;
    private final TextView textProgressBarValue;
    private final SeekBar seekBarProgressBarControl;
    private int progress = 0;
    private final Context thisContext;
    public SmilingProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        thisContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.smiling_progressbar, this);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        smileView = (ImageView) findViewById(R.id.smile);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textProgressBarValue = (TextView) findViewById(R.id.textProgressBarValue);
        seekBarProgressBarControl = (SeekBar) findViewById(R.id.seekBarProgressBarControl);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SmilingProgressBar,
                0, 0);

        String title = "Hello";
        try {
            title = a.getString(R.styleable.SmilingProgressBar_text);
        } finally {
            a.recycle();
        }
        textViewTitle.setText(title);

        update();

        seekBarProgressBarControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(thisContext, "seek bar progress:" + progressChanged, Toast.LENGTH_SHORT).show();
                setProgress(progressChanged);
            }
        });
    }
    public void setProgress(int progress) {
        this.progress = progress;
        update();
    }
    private void update() {
        progressBar.setProgress(progress);
        updateSmile();
        textProgressBarValue.setText(" " + progress);
    }
    private void updateSmile() {
        if (progress < 25) {
            smileView.setImageResource(R.drawable.smile_waiting_48);
        } else if (progress < 50) {
            smileView.setImageResource(R.drawable.smile_48);
        } else if (progress < 75) {
            smileView.setImageResource(R.drawable.smile_lol_48);
        } else {
            smileView.setImageResource(R.drawable.smile_in_love_48);
        }
    }
}
