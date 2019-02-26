package net.koreate.www.test_20190226_adapterview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import net.koreate.www.test_20190226_adapterview.util.SQLiteHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursorActivity extends AppCompatActivity {

    @BindView(R.id.cursor_adapter_listview)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor);
        ButterKnife.bind(this);

        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase db1 = helper.getWritableDatabase();
        helper.onUpgrade(db1,1,2);


        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tbl_dog",null);

        CursorAdapter adapter
                = new SimpleCursorAdapter(
                        this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"kind","name"},
                new int[]{android.R.id.text1,android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);


    }
}
