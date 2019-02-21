package net.koreate.www.test_20190221_db.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import net.koreate.www.test_20190221_db.R;
import net.koreate.www.test_20190221_db.util.MemoDBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SQLLiteActivity extends AppCompatActivity {

    @BindView(R.id.add_btn) Button add_btn;
    @BindView(R.id.add_title) EditText add_title;
    @BindView(R.id.add_content) EditText add_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqllite);
        ButterKnife.bind(this);

        /*add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        add_btn.setOnClickListener(v ->{
            String title = add_title.getText().toString();
            String content = add_content.getText().toString();

            Log.i("SQLiteActivity : ","title : " + title +" content : " + content);

            MemoDBHelper helper = new MemoDBHelper(this);
            helper.insertMemo(title,content);

            Log.i("result : " , helper.readMemoOnlyOne().toString());

            Intent intent = new Intent(SQLLiteActivity.this,ReadSQLiteActivity.class);
            startActivity(intent);
            finish();

        });

    }
}
