package net.koreate.www.test_20190225;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Map;

public class SharedPreferencesActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        pref = getSharedPreferences("pref",MODE_PRIVATE);

        System.out.println(pref.getBoolean("isLogin",false));
        System.out.println(pref.getString("hi","defaultValue"));
        System.out.println(pref.getString("bye","defaultValue"));
        savePref();
        Map<String,?> entry = pref.getAll();
        for(Map.Entry<String, ?> map : entry.entrySet()){
            System.out.println("key : " + map.getKey()+" / value : "+ map.getValue());
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.remove("hi");
        editor.commit();

        System.out.println("=====remove=====");
        Map<String,?> entry1 = pref.getAll();
        for(Map.Entry<String, ?> map : entry1.entrySet()){
            System.out.println("key : " + map.getKey()+" / value : "+ map.getValue());
        }

        editor.clear();
        editor.commit();

        Map<String,?> entry2 = pref.getAll();
        for(Map.Entry<String, ?> map : entry2.entrySet()){
            System.out.println("key : " + map.getKey()+" / value : "+ map.getValue());
        }

    }

    public void savePref(){
        SharedPreferences.Editor  editor = pref.edit();
        editor.putString("hi","안녕");
        editor.putString("bye","잘가");
        editor.putBoolean("isLogin",true);
        editor.commit();
    }





}
