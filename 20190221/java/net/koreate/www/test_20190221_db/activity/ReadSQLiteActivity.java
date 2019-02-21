package net.koreate.www.test_20190221_db.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import net.koreate.www.test_20190221_db.R;
import net.koreate.www.test_20190221_db.util.MemoDBHelper;
import net.koreate.www.test_20190221_db.vo.MemoVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadSQLiteActivity extends AppCompatActivity {

    @BindView(R.id.add_title)
    TextView addTitle;
    @BindView(R.id.add_content)
    TextView addContent;
    @BindView(R.id.list_btn)
    Button list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sqlite);
        ButterKnife.bind(this);

        MemoDBHelper helper = new MemoDBHelper(this);
        MemoVO memo = helper.readMemoOnlyOne();

        addTitle.setText(memo.getTitle());
        addContent.setText(memo.getContent());

        list_btn.setOnClickListener(
                v->startActivity(
                        new Intent(ReadSQLiteActivity.this,ReadMemoListActivity.class)
                )
        );
        /*
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReadSQLiteActivity.this,ReadMemoListActivity.class);
                startActivity(i);
            }
        });
        */
    }
}
