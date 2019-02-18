package net.koreate.www.a20190218firstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class Test3 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText editText;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);

        CompoundButton.OnCheckedChangeListener listener
                = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    System.out.println("CHECK");
                }else{
                    System.out.println("CHECK 해제");
                }
            }
        };
        checkBox.setOnCheckedChangeListener(listener);
        //checkBox.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            System.out.println("체크");
            String funny = getString(R.string.funny);
            editText.setText(funny);
        }else{
            System.out.println("체크해제!");
            String boring = getString(R.string.boring);
            editText.setText(boring);
        }
    }
}