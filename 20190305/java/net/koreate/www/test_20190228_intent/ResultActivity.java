package net.koreate.www.test_20190228_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.koreate.www.test_20190228_intent.vo.MemberVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    final static String USER_ID = "aaa";
    final static String USER_PW = "aaa";

    @BindView(R.id.result)
    TextView result;

    @BindView(R.id.callBack)
    Button callBack;

    boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Intent i = getIntent();
        MemberVO member = (MemberVO)i.getSerializableExtra("member");
        Log.i("Member " , member.toString());

        if(USER_ID.equals(member.getUserId()) && USER_PW.equals(member.getUserPw())){
            isLogin = true;
        }else{
            isLogin = false;
        }
        result.setText("isLogin : " + isLogin);


        callBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = ResultActivity.this.getIntent();
                i.putExtra("isLogin",isLogin);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}
