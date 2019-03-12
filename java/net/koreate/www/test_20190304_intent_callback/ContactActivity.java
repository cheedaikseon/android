package net.koreate.www.test_20190304_intent_callback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.koreate.www.test_20190304_intent_callback.util.ListViewAdapter;
import net.koreate.www.test_20190304_intent_callback.vo.ContactVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;

    ListViewAdapter adapter;

    ArrayList<ContactVO> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        contactList = (ArrayList<ContactVO>)getIntent().getSerializableExtra("contactList");

        for(ContactVO cv : contactList){
            Log.i("ContactVO",cv.toString());
        }

        adapter= new ListViewAdapter(this,contactList,R.layout.item_layout);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = getIntent();
                ContactVO contactVO = contactList.get(position);
                System.out.println("ContactActivty : "+contactVO.toString());
                i.putExtra("contactVO",contactVO);
                setResult(RESULT_OK,i);
                finish();
            }
        });


    }
}
