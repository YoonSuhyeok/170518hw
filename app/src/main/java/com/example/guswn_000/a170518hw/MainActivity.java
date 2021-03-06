package com.example.guswn_000.a170518hw;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Mypainter mypainter;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Graphics");

        checkpermission();
        init();
    }

    public void init()
    {
        mypainter = (Mypainter)findViewById(R.id.painter);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                mypainter.checkboxcheck(isChecked);
            }
        });
        //디렉토리 생성
        String path = getExternalPath();
        File file = new File(path + "hwimg");
        file.mkdir();

//        String mkdirerrormsg = "디렉토리 생성";
//        if(file.isDirectory() == false)
//        {
//            mkdirerrormsg = "디렉토리 생성 오류";
//        }
//        Toast.makeText(this,mkdirerrormsg,Toast.LENGTH_SHORT).show();

    }




    //메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menublur://블러 체크메뉴
                if(item.isChecked())
                {
                    item.setChecked(false);
                    mypainter.blur(false);
                }
                else
                {
                    item.setChecked(true);
                    mypainter.blur(true);
                }
                break;

            case R.id.menucolor://컬러 필터 체크메뉴
                if (item.isChecked())
                {
                    item.setChecked(false);
                    mypainter.color(false);
                }
                else
                {
                    item.setChecked(true);
                    mypainter.color(true);
                }
                break;
            case R.id.menupenwb: //펜 굵기 체크메뉴
                if (item.isChecked())
                {
                    item.setChecked(false);
                    mypainter.penwidth(false);
                }
                else
                {
                    item.setChecked(true);
                    mypainter.penwidth(true);
                }


                break;
            case R.id.menured:
                mypainter.penRed();
                break;
            case R.id.menublue:
                mypainter.penBlue();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //버튼
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnerase://지우긔
                mypainter.Eraser();
                break;
            case R.id.btnopen:
                mypainter.Eraser();
                mypainter.Open(getExternalPath() + "hwimg/img.jpg");
                break;
            case R.id.btnsave:
                mypainter.Save(getExternalPath() + "hwimg/img.jpg");
                break;
            case R.id.btnrotate:
                checkBox.setChecked(true);
                mypainter.rotatestamp();
                break;
            case R.id.btnmove://사랑은 뭅뭅
                checkBox.setChecked(true);
                mypainter.move();
                break;
            case R.id.btnscale:
                checkBox.setChecked(true);
                mypainter.scale();
                break;
            case R.id.btnskew:
                checkBox.setChecked(true);
                mypainter.skew();
                break;
        }

    }



    public void checkpermission()
    {

        int permissioninfo = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissioninfo == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this,
//                    "SDCard 쓰기 권한 있음",Toast.LENGTH_SHORT).show();
        }
        else {

            //사실 이프문 안써도되는데
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this,
                        "어플리케이션 설정에서 저장소 사용 권한을 허용해주세요",Toast.LENGTH_SHORT).show();

                //이밑에꺼 해야 권한허용 대화상자가 다시뜸
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);
            }
            else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);  // 이 100은 리퀘스트코드다
            }
        }
    }


    public String getExternalPath()
    {
        String sdPath = "";
        String ext = Environment.getExternalStorageState();
        if(ext.equals(Environment.MEDIA_MOUNTED))
        {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
            //sdPath = "mnt/sdcard/";
        }
        else
        {
            sdPath = getFilesDir() + "";
        }
//        Toast.makeText(getApplicationContext(),sdPath,Toast.LENGTH_SHORT).show();
        return sdPath;
    }



}
