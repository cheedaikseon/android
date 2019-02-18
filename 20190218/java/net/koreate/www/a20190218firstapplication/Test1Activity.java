package net.koreate.www.a20190218firstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Test1Activity extends AppCompatActivity implements View.OnClickListener {

    Button visibleBtn;
    Button invisibleBtn;
    Button goneBtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2_layout);
        visibleBtn = findViewById(R.id.visibleBtn);
        invisibleBtn = findViewById(R.id.invisibleBtn);
        goneBtn = findViewById(R.id.goneBtn);
        textView = findViewById(R.id.textView);

        visibleBtn.setOnClickListener(this);
        invisibleBtn.setOnClickListener(this);
        goneBtn.setOnClickListener(this);

        // test1_layout
/*
        setContentView(R.layout.test1_layout);
        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sout + tab
                System.out.println();
                System.out.println("btn1 Click");
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);
*/



    }
    // alt + insert
    @Override
    public void onClick(View v) {
        Button btn = (Button)v;
        System.out.println(btn.getText());
        if(v == visibleBtn){
            System.out.println(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }else if(v == invisibleBtn){
            System.out.println(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
        }else{
            System.out.println(View.GONE);
            textView.setVisibility(View.GONE);
        }
    }

    public void btn3Click(View view){
        System.out.println("btn3 CLick");
    }

}
