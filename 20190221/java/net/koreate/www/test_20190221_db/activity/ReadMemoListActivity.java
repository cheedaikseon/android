package net.koreate.www.test_20190221_db.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.koreate.www.test_20190221_db.R;
import net.koreate.www.test_20190221_db.util.MemoDBHelper;
import net.koreate.www.test_20190221_db.util.MemoListViewAdapter;
import net.koreate.www.test_20190221_db.vo.MemoVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadMemoListActivity extends AppCompatActivity {

    @BindView(R.id.listView) ListView listView;
    @BindView(R.id.addBtn) Button addBtn;
    @BindView(R.id.modifyWindow) LinearLayout modifyWindow;
    @BindView(R.id.modifyID) TextView modifyID;
    @BindView(R.id.modifyTitle) EditText modifyTitle;
    @BindView(R.id.modifyContent) EditText modifyContent;
    @BindView(R.id.modifyBtn) Button modifyBtn;

    MemoListViewAdapter adapter;

    ArrayList<MemoVO> memoList;

    MemoVO memoVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_memo_list);
        ButterKnife.bind(this);

        MemoDBHelper helper = new MemoDBHelper(this);
        ArrayList<String> titles = helper.selectTitles();
        for(String title : titles){
            Log.i("read list : title", title);
        }
        memoList = helper.readMemoList();
        //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        adapter = new MemoListViewAdapter(this,memoList,R.layout.custom_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                memoVO = memoList.get(position);
                Toast.makeText(ReadMemoListActivity.this, memoVO.toString(), Toast.LENGTH_SHORT).show();
                modifyWindow.setVisibility(View.VISIBLE);
                modifyID.setText(String.valueOf(memoVO.getId()));
                modifyTitle.setText(memoVO.getTitle());
                modifyContent.setText(memoVO.getContent());
            }
        });

        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = modifyTitle.getText().toString();
                String content = modifyContent.getText().toString();
                memoVO.setTitle(title);
                memoVO.setContent(content);
                Log.i("modify btn : ",memoVO.toString());
                helper.updateMemo(memoVO);
                modifyWindow.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReadMemoListActivity.this,SQLLiteActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
