package net.koreate.www.test_20190226_adapterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import net.koreate.www.test_20190226_adapterview.util.CustomListViewAdapter;
import net.koreate.www.test_20190226_adapterview.util.SQLiteHelper;
import net.koreate.www.test_20190226_adapterview.vo.DogVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomActivity extends AppCompatActivity {

    @BindView(R.id.custom_listview)
    ListView custom_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);

        SQLiteHelper helper = new SQLiteHelper(this);
        ArrayList<DogVO> dogList  = helper.getDogVOList();
        Log.i("dogList size ",dogList.size()+"");
        CustomListViewAdapter adapter
                = new CustomListViewAdapter(this,R.layout.custom_item_listview,dogList);

        custom_listview.setAdapter(adapter);

    }
}
