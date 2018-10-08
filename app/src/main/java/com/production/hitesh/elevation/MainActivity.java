package com.production.hitesh.elevation;

import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import carbon.widget.LinearLayout;
import carbon.widget.RelativeLayout;
import carbon.widget.SeekBar;
import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton colorPicker;
    private LinearLayout l1,l2,l3;
    private int defaultColor;
    private android.widget.SeekBar setEle;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        init();


        int minSeek = (int) (2*getResources().getDisplayMetrics().density);
        int maxSeek = (int)(20*getResources().getDisplayMetrics().density);

        setEle.setMin(minSeek);
        setEle.setMax(maxSeek);
        setEle.setProgress((int)l1.getElevation());
        setEle.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                setElevation(progress,l1,l2,l3);


            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {


            }
        });

        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });


    }


    private void init(){
        setEle=findViewById(R.id.setEle);
        colorPicker=findViewById(R.id.colorPicker);

        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);

        defaultColor=ContextCompat.getColor(MainActivity.this,R.color.carbon_blue_900);

    }


    private void openColorPicker(){
        AmbilWarnaDialog colorDialogue = new AmbilWarnaDialog(this,defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                l1.setElevationShadowColor(color);
                l2.setElevationShadowColor(color);
                l3.setElevationShadowColor(color);

                refreshViews(l1,l2,l3);

            }
        });
        colorDialogue.show();
    }


    private void refreshViews(LinearLayout... l){
        for (int i=0;i<l.length;i++){
            l[i].setVisibility(View.GONE);
            l[i].setVisibility(View.VISIBLE);
        }


    }

    private  void setElevation(int el, LinearLayout...l){
        for(int i=0;i<l.length;i++){
            l[i].setElevation(el);
        }

        refreshViews(l1,l2,l3);
    }
}
