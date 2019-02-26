package net.koreate.www.test_20190226_adapterview.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.koreate.www.test_20190226_adapterview.vo.DogVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    public SQLiteHelper(Context context){
        super(context,"testdb",null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbl_dog ("
                     + " _id integer primary key autoincrement, "
                     + " kind text ,"
                     + " name text )";
        db.execSQL(sql);

        db.execSQL("INSERT INTO tbl_dog(kind,name) VALUES('골든리트리버','순이')");
        db.execSQL("INSERT INTO tbl_dog(kind,name) VALUES('진돗개','돌이')");
        db.execSQL("INSERT INTO tbl_dog(kind,name) VALUES('푸들','푸순이')");
        db.execSQL("INSERT INTO tbl_dog(kind,name) VALUES('그레이 하운드','하운드')");
        db.execSQL("INSERT INTO tbl_dog(kind,name) VALUES('불독','못난이')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table tbl_dog");
            onCreate(db);
        }
    }

    public ArrayList<Map<String, String>> getDogList(){
        ArrayList<Map<String, String>> dogList = new ArrayList<>(5);
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT kind,name FROM tbl_dog",null);
        while(cursor.moveToNext()){
            Map<String , String> map = new HashMap<>();
            map.put("kind",cursor.getString(0));
            map.put("name",cursor.getString(1));
            dogList.add(map);
        }
        db.close();
        return dogList;
    }

    public ArrayList<DogVO> getDogVOList(){
        ArrayList<DogVO> dogList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id,kind,name FROM tbl_dog",null);
        while(cursor.moveToNext()){
            DogVO dog = new DogVO();
            dog.set_id(cursor.getInt(0));
            dog.setKind(cursor.getString(1));
            dog.setName(cursor.getString(2));
            dogList.add(dog);
        }
        db.close();
        return dogList;
    }

}
