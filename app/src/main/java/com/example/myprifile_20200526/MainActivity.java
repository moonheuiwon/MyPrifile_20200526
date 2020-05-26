package com.example.myprifile_20200526;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.myprifile_20200526.databinding.ActivityMainBinding;
import com.github.chrisbanes.photoview.PhotoView;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        binding.profilImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

        Glide.with(mContext).load("https://img2.sbs.co.kr/img/sbs_cms/CH/2017/04/28/CH52785630_w300_h300.jpg").into(binding.profilImg);

    }
}
