package net.koreate.www.test_20190225;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadInternalActivity extends AppCompatActivity {

    @BindView(R.id.result)
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_internal);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String data = i.getStringExtra("data");
        Log.i("data ", data);

        if(data.equals("normal")){
            getNormalFile();
        }else{
            String fileName = i.getStringExtra("cache");
            getCacheFile(fileName);
        }
    }

    public void getNormalFile(){
        File file = getFilesDir();
        Log.i("내부 저장소 경로  ",file.getPath());
        Log.i("파일 절대 경로 "," : "+getFileStreamPath("sample.txt"));

        File file1 = getFileStreamPath("sample.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            int buffer = 0;
            while((buffer = reader.read()) != -1){
                Log.i("file data : ",String.valueOf((char)buffer));
                result.append(String.valueOf((char)buffer));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getCacheFile(String fileName){
        File cacheFile = new File(getCacheDir().getAbsolutePath(),fileName);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
            int buffer = 0;

            while((buffer = reader.read()) != -1){
                String data = String.valueOf((char)buffer);
                System.out.println("cache data :"+data);
                result.append(data);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
