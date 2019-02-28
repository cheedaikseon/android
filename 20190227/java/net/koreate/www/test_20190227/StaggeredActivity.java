package net.koreate.www.test_20190227;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StaggeredActivity extends AppCompatActivity {

    @BindArray(R.array.img_title) String[] titles;
    @BindArray(R.array.img_drawable) TypedArray imgs;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    StaggeredRecyclerViewAdapter adapter;
    StaggeredGridLayoutManager manager;
    ArrayList<RecyclerTestVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        for(int i = 0; i<imgs.length(); i++){
            RecyclerTestVO rtv = new RecyclerTestVO();
            rtv.setTitle(titles[i]);
            rtv.setImg(imgs.getResourceId(i,0));
            list.add(rtv);
        }

        manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new StaggeredRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
