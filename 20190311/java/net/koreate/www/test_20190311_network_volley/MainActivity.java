package net.koreate.www.test_20190311_network_volley;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn1){
            startActivity(new Intent(this, net.koreate.www.test_20190311_network_volley.VolleyListTestActivity.class));
        }else if(v == btn2){
            startActivity(new Intent(this, net.koreate.www.test_20190311_network_volley.VolleyMapTestActivity.class));
        }
    }
}
