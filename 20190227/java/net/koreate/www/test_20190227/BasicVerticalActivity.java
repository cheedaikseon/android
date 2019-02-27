package net.koreate.www.test_20190227;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicVerticalActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindArray(R.array.img_title)
    String[] titles;

    ArrayList<String> titleList;

    BasicVerticalRecyclerViewAdapter adapter;

    @BindView(R.id.group) RadioGroup group;
    boolean isCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_vertical);
        ButterKnife.bind(this);

        titleList = new ArrayList<>();
        for(String title : titles){
            titleList.add(title);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BasicVerticalRecyclerViewAdapter(titleList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new CustomItemDecoration(this));
        adapter.setOnItemClickListener(new BasicVerticalRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("position " , position+"");
                if(isCheck){
                    // 아이템 추가
                    adapter.addItem(titleList.get(position),position);
                }else{
                    // 아이템 삭제
                    adapter.deleteItem(position);
                }
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.addBtn){
                    isCheck = true;
                }else{
                    isCheck = false;
                }
            }
        });
    }
}
