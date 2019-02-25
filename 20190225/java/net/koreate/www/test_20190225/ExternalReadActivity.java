package net.koreate.www.test_20190225;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExternalReadActivity extends AppCompatActivity {

    @BindView(R.id.result)
    TextView result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_internal);
        ButterKnife.bind(this);

        try {
            File file = new File(
                    Environment
                            .getExternalStorageDirectory()
                            .getAbsolutePath()+"/sample/sample.txt"
            );
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int buffer = 0;

            while((buffer = reader.read()) != -1){
                String data = String.valueOf((char)buffer);
                System.out.print(data);
                result.append(data);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
