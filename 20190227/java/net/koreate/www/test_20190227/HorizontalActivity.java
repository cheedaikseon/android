package net.koreate.www.test_20190227;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindArray(R.array.img_title)
    String[] titles;
    @BindArray(R.array.img_drawable)
    TypedArray imgs;

    HorizontalRecyclerViewAdapter adapter;
    ArrayList<RecyclerTestVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        ButterKnife.bind(this);

        //TypedArray imgs = getResources().obtainTypedArray(R.array.img_drawable);
        list = new ArrayList<>();
        for(int j =0; j<imgs.length(); j++){
            int i = imgs.getResourceId(j,0);
            RecyclerTestVO rtv = new RecyclerTestVO();
            rtv.setImg(i);
            rtv.setTitle(titles[j]);
            list.add(rtv);
            Log.i("imgs drawble id " ,i+"");
        }

        RecyclerView.LayoutManager manager =
                new LinearLayoutManager
                        (this,
                        LinearLayoutManager.HORIZONTAL,
                        false);
        recyclerView.setLayoutManager(manager);
        adapter = new HorizontalRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
