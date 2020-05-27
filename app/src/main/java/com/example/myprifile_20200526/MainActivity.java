package com.example.myprifile_20200526;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myprifile_20200526.databinding.ActivityMainBinding;
import com.github.chrisbanes.photoview.PhotoView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

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

        binding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener pl = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
//                        허가가 된 상황 => 실제 전화걸기
//                        실제 전화 걸기 => 권한 허가가 안남, 앱이 강제 종료됨 => TedPermission 권한 획득 후 사용
                        String phoneNum = binding.phoneNumTxt.getText().toString();
                        Uri myUri = Uri.parse(String.format("tel:%s", phoneNum));
                        Intent myIntent = new Intent(Intent.ACTION_CALL, myUri);
                        startActivity(myIntent);
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
//                        최종적으로 거부되었을때 처리할 행동
                        Toast.makeText(mContext, "권한이 거부되어 통화가 불가능합니다.", Toast.LENGTH_SHORT).show();
                    }
                };

//                만약에 권한 설정이 안되어있으면 => 얼럿으로 허용할지 ?
//                권한 이전 허용 => 곧바로 Granted 실행.
                TedPermission
                        .with(mContext)
                        .setPermissionListener(pl)
                        .setDeniedMessage("거부하면 통화가 불가능함. \n 설정에서 권한을 켜주세요.")
                        .setPermissions(Manifest.permission.CALL_PHONE)
                        .check();

            }
        });

        binding.profilImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                intent.putExtra("imgUrl","https://img2.sbs.co.kr/img/sbs_cms/CH/2017/04/28/CH52785630_w300_h300.jpg");
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

        Glide.with(mContext).load("https://img2.sbs.co.kr/img/sbs_cms/CH/2017/04/28/CH52785630_w300_h300.jpg").into(binding.profilImg);

    }
}
