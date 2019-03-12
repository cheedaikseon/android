package net.koreate.www.test_20190228_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import net.koreate.www.test_20190228_intent.vo.MemberVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveActivity extends AppCompatActivity {

    @BindView(R.id.resultView)
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        ButterKnife.bind(this);

        Intent i = this.getIntent();
        String userId = i.getStringExtra("userId");
        String userPw = i.getStringExtra("userPw");
        result.setText(userId+" / "+userPw);

        int uno = i.getIntExtra("uno",0);
        Log.i("uno ",uno+"");
        boolean isChecked = i.getBooleanExtra("isChecked",false);
        Log.i("isChecked ",isChecked+"");
        String[] 점심 = i.getStringArrayExtra("strs");
        for(String s : 점심){
            Log.i("메뉴 ",s);
        }

        MemberVO member = (MemberVO)i.getSerializableExtra("memberVO");
        Log.i("Member ",member.toString());

        ArrayList<MemberVO> memberList =
                (ArrayList<MemberVO>)i.getSerializableExtra("memberList");
        for(MemberVO m : memberList){
            Log.i("Member list ",m.toString());
        }
    }
}
