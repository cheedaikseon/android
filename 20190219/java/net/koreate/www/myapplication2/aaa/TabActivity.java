package net.koreate.www.myapplication2.aaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;
import android.widget.Toast;

import net.koreate.www.myapplication2.R;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        TabHost host = findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator(null,getResources().getDrawable(R.drawable.tab_icon1));
        spec.setContent(R.id.tabText1);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator(null,getResources().getDrawable(R.drawable.tab_icon2));
        spec.setContent(R.id.tabText2);
        host.addTab(spec);

        spec = host.newTabSpec("tab3");
        spec.setIndicator(null,getResources().getDrawable(R.drawable.tab_icon3));
        spec.setContent(R.id.tabText3);
        host.addTab(spec);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(TabActivity.this,"변경 : " + tabId,Toast.LENGTH_SHORT).show();
            }
        });


    }
}
