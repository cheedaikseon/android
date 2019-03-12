package net.koreate.www.test_20190228_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.koreate.www.test_20190228_intent.vo.MemberVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransmissionActivity extends AppCompatActivity {

    @BindView(R.id.userId)
    EditText editText;

    @BindView(R.id.userPw)
    EditText editText2;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.btnResult)
    Button result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmission);
        ButterKnife.bind(this);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString();
                String pw = editText2.getText().toString();
                MemberVO member = new MemberVO();
                member.setUserId(id);
                member.setUserPw(pw);

                Intent intent = new Intent(TransmissionActivity.this,ResultActivity.class);
                intent.putExtra("member",member);
                startActivityForResult(intent,1004);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = editText.getText().toString();
                    String pw = editText2.getText().toString();
                    String message = "id : " + id +" / pw : "+pw;
                    showToast(message);

                    Intent intent = new Intent(
                            TransmissionActivity.this,
                            ReceiveActivity.class);
                    intent.putExtra("userId",id);
                    intent.putExtra("userPw",pw);
                    intent.putExtra("uno",1);
                    intent.putExtra("isChecked",true);
                    String[] 정하기싫다 = {"잡채밥","짜장면","탕뽁","스테이크"};
                    intent.putExtra("strs",정하기싫다);
                    MemberVO member = new MemberVO();
                    member.setUno(1);
                    member.setUserId(id);
                    member.setUserPw(pw);

                    intent.putExtra("memberVO",member);

                    ArrayList<MemberVO> memberList = new ArrayList<>();
                    for(int j=0; j<10; j++){
                        MemberVO m = new MemberVO();
                        m.setUno(j+1);
                        m.setUserId(id+j);
                        m.setUserPw(pw+j);
                        memberList.add(m);
                    }

                    intent.putExtra("memberList",memberList);


                    startActivity(intent);
                }
            }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode"+requestCode);
        if(requestCode == 1004 && resultCode == RESULT_OK){
            boolean isLogin = data.getBooleanExtra("isLogin",false);
            showToast("isLogin : " + isLogin);
        }
    }

    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
