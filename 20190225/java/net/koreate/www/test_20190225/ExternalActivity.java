package net.koreate.www.test_20190225;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExternalActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.addBtn)
    Button addBtn;

    boolean writeExternalPermission = false;
    boolean readExternalPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        ButterKnife.bind(this);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int currentApiVersion = Build.VERSION.SDK_INT;
        System.out.println("현재 사용 OS VERSION : " + currentApiVersion);
        //if(currentApiVersion >= 23){
        if(currentApiVersion >= Build.VERSION_CODES.M){
            checkPermissionNow();
        }else{
            writeExternalPermission = true;
            readExternalPermission = true;
        }
        System.out.println(writeExternalPermission +"  / " + readExternalPermission);
        if(writeExternalPermission && readExternalPermission){
            try {
                String text = content.getText().toString();

                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/sample";

                File file = new File(dirPath);
                if(!file.exists()){
                    file.mkdirs();
                }
                System.out.println("file path : "+file.getPath());

                File file1 = new File(dirPath+"/sample.txt");
                if(!file1.exists()){
                    file1.createNewFile();
                }

                FileWriter writer = new FileWriter(file1,true);
                writer.write(text);
                writer.flush();
                writer.close();

                Intent i = new Intent(
                        ExternalActivity.this,
                        ExternalReadActivity.class);
                startActivity(i);
                System.out.println("작성 종료");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("권한 없음");
        }
    }

    public void checkPermissionNow(){
        System.out.println("check permission  ");

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
           == PackageManager.PERMISSION_GRANTED){
            writeExternalPermission = true;
        }

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
            writeExternalPermission = true;
        }

        if(!writeExternalPermission || !readExternalPermission){
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0 && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                writeExternalPermission = true;
            }
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED){
                readExternalPermission = true;
            }
        }
    }
}
