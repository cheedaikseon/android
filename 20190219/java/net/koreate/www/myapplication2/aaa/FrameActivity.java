package net.koreate.www.myapplication2.aaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.koreate.www.myapplication2.R;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1,btn2,btn3;
    TextView content1,content2,content3;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame2);
        initView();
    }

    public void initView(){
        btn1 = findViewById(R.id.btn1);
        btn2 =findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        content1 = findViewById(R.id.content1);
        content2 = findViewById(R.id.content2);
        content3 = findViewById(R.id.content3);
    }

    @Override
    public void onClick(View v) {
        content1.setVisibility(View.GONE);
        content2.setVisibility(View.GONE);
        content3.setVisibility(View.GONE);
        if(v == btn1){
            Toast.makeText(getApplicationContext(),"1번 컨텐츠",Toast.LENGTH_SHORT).show();
            content1.setVisibility(View.VISIBLE);
            /*content2.setVisibility(View.GONE);
            content3.setVisibility(View.GONE);*/
        }else if(v == btn2){
            Toast.makeText(getBaseContext(),"2번 컨텐츠",Toast.LENGTH_SHORT).show();
            /*content1.setVisibility(View.GONE);
            content3.setVisibility(View.GONE);*/
            content2.setVisibility(View.VISIBLE);

        }else{
            Toast.makeText(this,"3번 컨텐츠",Toast.LENGTH_SHORT).show();
            /*content1.setVisibility(View.GONE);
            content2.setVisibility(View.GONE);*/
            content3.setVisibility(View.VISIBLE);
        }

    }
}
