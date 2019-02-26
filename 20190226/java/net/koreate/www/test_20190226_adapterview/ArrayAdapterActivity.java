package net.koreate.www.test_20190226_adapterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArrayAdapterActivity extends AppCompatActivity {
    @BindView(R.id.array_adapter_listview)
    ListView array_adapter_listview;

    @BindArray(R.array.dog_list)
    String[] dog_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);
        ButterKnife.bind(this);
        for(String s : dog_list){
            Log.i("dog_list",s);
        }
        //String[] dog_list = getResources().getStringArray(R.array.dog_list);
        ArrayAdapter arrayAdapter
                // android.R.id.text1    , android.R.id.text2
                //= new ArrayAdapter(this,android.R.layout.simple_list_item_multi_choice,dog_list);
                = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,dog_list);
        array_adapter_listview.setAdapter(arrayAdapter);
        // 다중선택
        array_adapter_listview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        // 단일선택
        //array_adapter_listview.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        array_adapter_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        ArrayAdapterActivity.this,
                        "dog : "+dog_list[position],
                        Toast.LENGTH_SHORT
                ).show();

                SparseBooleanArray sp = array_adapter_listview.getCheckedItemPositions();
                for(int i=0; i<sp.size(); i++){
                    Log.i("sp ","key : "+sp.keyAt(i)+" / value : " + sp.valueAt(i));
                    if(sp.valueAt(i) == true){
                        String s = ((CheckedTextView)array_adapter_listview.getChildAt(i)).getText().toString();
                        Log.i("checked dog  ",s);
                    }
                }



            }
        });





    }
}
