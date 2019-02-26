package net.koreate.www.test_20190226_adapterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import net.koreate.www.test_20190226_adapterview.util.SQLiteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleAdapterActivity extends AppCompatActivity {

    @BindView(R.id.simple_adapter_listview)
    ListView listView;
    @BindArray(R.array.dog_list)
    String[] dog_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);
        ButterKnife.bind(this);

        ArrayList<Map<String,String>> simpleList = new ArrayList<>();

        for(String s : dog_list){
            Map<String,String> map = new HashMap<>();
            map.put("kind",s);
            map.put("name",s + "_해피");
            simpleList.add(map);
        }

        simpleList.clear();

        SQLiteHelper helper = new SQLiteHelper(this);
        simpleList = helper.getDogList();

        SimpleAdapter adapter
                = new SimpleAdapter(
                        this,
                simpleList,
                android.R.layout.simple_list_item_2,
                new String[]{"kind","name"},
                new int[]{android.R.id.text1,android.R.id.text2}
        );

        listView.setAdapter(adapter);


    }
}
