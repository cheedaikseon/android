package net.koreate.www.test_20190227;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindViews({R.id.basicVerticalBtn,R.id.horizontalBtn,R.id.gridBtn,R.id.staggeredBtn})
    Button[] btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        for(Button btn : btns){
            btn.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch(v.getId()){
            case R.id.basicVerticalBtn :
                i = new Intent(this, BasicVerticalActivity.class);
                startActivity(i);
                break;
            case R.id.horizontalBtn :
                i = new Intent(this, HorizontalActivity.class);
                startActivity(i);
                break;
            case R.id.gridBtn :
                i = new Intent(this, GridActivity.class);
                startActivity(i);
                break;
            case R.id.staggeredBtn :
                i = new Intent(this, StaggeredActivity.class);
                startActivity(i);
                break;
        }
    }
}
