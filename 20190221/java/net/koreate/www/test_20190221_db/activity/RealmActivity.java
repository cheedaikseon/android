package net.koreate.www.test_20190221_db.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.koreate.www.test_20190221_db.R;
import net.koreate.www.test_20190221_db.util.RealmHelper;
import net.koreate.www.test_20190221_db.vo.MessageVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class RealmActivity extends AppCompatActivity {

    @BindView(R.id.editWriter)
    EditText editWriter;
    @BindView(R.id.editMessage)
    EditText editMessage;
    @BindView(R.id.btnMessage)
    Button btnMessage;

    ArrayList<MessageVO> messageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        ButterKnife.bind(this);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writer = editWriter.getText().toString();
                String message = editMessage.getText().toString();
                MessageVO mv = new MessageVO();
                mv.setWriter(writer);
                mv.setMessage(message);
                Log.i("Message : ", mv.toString());
                Realm.init(RealmActivity.this);
                Realm realm = Realm.getDefaultInstance();
                RealmHelper helper = new RealmHelper(realm);
                helper.saveToMessage(mv);

                messageList = helper.retrieve();

                for(MessageVO m : messageList){
                    Log.i("message : ",m.toString());
                }

            }
        });

    }
}
