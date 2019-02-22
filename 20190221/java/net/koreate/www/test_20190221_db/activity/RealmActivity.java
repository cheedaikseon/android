package net.koreate.www.test_20190221_db.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import net.koreate.www.test_20190221_db.R;
import net.koreate.www.test_20190221_db.util.MessageListViewAdapter;
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
    @BindView(R.id.addWrap)
    LinearLayout addWrap;
    @BindView(R.id.messageListView)
    ListView messageListView;
    @BindView(R.id.addMessageBtn)
    Button addMessageBtn;

    ArrayList<MessageVO> messageList;
    MessageListViewAdapter adapter;

    Realm realm;
    RealmHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        ButterKnife.bind(this);
        initRealm();
        initListView();
        addMessage();
        updateMessage();

    }

    private void updateMessage() {
        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageVO messageVO = messageList.get(position);

                Dialog dialog = new Dialog(RealmActivity.this);
                dialog.setTitle("UPDATE TO REALM");
                dialog.setContentView(R.layout.realm_dialog);
                EditText modifyWriter = dialog.findViewById(R.id.modifyWriter);
                EditText modifyMessage = dialog.findViewById(R.id.modifyMessage);
                modifyWriter.setText(messageVO.getWriter());
                modifyMessage.setText(messageVO.getMessage());
                Button modifyBtn = dialog.findViewById(R.id.modifyBtn);
                Button deleteBtn = dialog.findViewById(R.id.deleteBtn);

                modifyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String writer = modifyWriter.getText().toString();
                        String message = modifyMessage.getText().toString();
                        Log.i("modify Btn ","CLICK"+writer+"  :  "+ message);
                        helper.updateMessage(messageVO,writer,message);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("delete Btn ","CLICK");
                        messageList.remove(messageVO);
                        helper.deleteMessage(messageVO);
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                });

                Toast.makeText(RealmActivity.this, messageVO.toString(), Toast.LENGTH_SHORT).show();
                dialog.show();

            }
        });

    }

    private void addMessage() {
        addMessageBtn.setOnClickListener(v -> addWrap.setVisibility(View.VISIBLE));

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writer = editWriter.getText().toString();
                String message = editMessage.getText().toString();
                MessageVO mv = new MessageVO();
                mv.setWriter(writer);
                mv.setMessage(message);
                Log.i("Message : ", mv.toString());
                helper.saveToMessage(mv);
                initListView();
                addWrap.setVisibility(View.GONE);
            }
        });
    }

    // listview??
    private void initListView() {
        messageList = helper.retrieve();
        adapter = new MessageListViewAdapter(this,messageList);
        messageListView.setAdapter(adapter);
        for(MessageVO m : messageList){
            Log.i("message : ",m.toString());
        }
    }

    private void initRealm() {
        Realm.init(RealmActivity.this);
        realm = Realm.getDefaultInstance();
        helper = new RealmHelper(realm);
    }
}
