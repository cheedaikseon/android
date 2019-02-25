package net.koreate.www.test_20190225;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InternalActivity extends AppCompatActivity {
    @BindView(R.id.editText)
    EditText content;
    @BindView(R.id.btnFile)
    Button btnFile;
    @BindView(R.id.btnCache)
    Button btnCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        ButterKnife.bind(this);

        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos = null;
                String text = content.getText().toString();
                String fileName = "sample.txt";
                System.out.println(text);

                try {
                    // MODE_WORLD_READABLE;
                    // MODE_WORLD_WRITEABLE;
                    // MODE_MULTI_PROCESS;
                    fos = openFileOutput(fileName,MODE_PRIVATE|MODE_APPEND);
                    fos.write(text.getBytes());
                    fos.flush();
                    fos.close();

                    Intent intent = new Intent(InternalActivity.this,ReadInternalActivity.class);
                    intent.putExtra("data","normal");
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos = null;
                String text = content.getText().toString();
                String fileName= "cache";

                try {
                    File file = File.createTempFile(fileName, ".txt", getCacheDir());
                    System.out.println("file : " + file);
                    System.out.println("cache file : " + file.getName());
                    fos = new FileOutputStream(file);
                    fos.write(text.getBytes());
                    fos.flush();
                    fos.close();

                    Intent i = new Intent(InternalActivity.this,ReadInternalActivity.class);
                    i.putExtra("data","cache");
                    i.putExtra("cache",file.getName());
                    startActivity(i);
                }catch(IOException e){

                }


            }
        });
    }
}
