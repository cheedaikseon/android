package net.koreate.www.test_20190221_db.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.koreate.www.test_20190221_db.vo.MemoVO;

import java.util.ArrayList;

public class MemoDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_memo";
    public static final String TABLE_NAME = "tbl_memo";

    SQLiteDatabase db;

    public MemoDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSql = "CREATE TABLE "+TABLE_NAME+ "("
                + "id integer primary key autoincrement, "
                + "title text,"
                + "content text)";
        db.execSQL(memoSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // database 정보를 업그레이드 할때 필요
    }

    public void insertMemo(String title, String content){
        db = getWritableDatabase();
        Log.i("insertMemo","title : " + title+"  content : "+content);
        //String sql = "INSERT INTO "+TABLE_NAME+" (title,content) VALUES('"+title+"','"+content+"')";
        /*String sql="INSERT INTO "+TABLE_NAME+"(title,content) VALUES(?,?)";
        db.execSQL(sql,new String[]{title,content});
        */
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("content",content);
        //db.insert(TABLE_NAME,"id",cv);
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }

    public MemoVO readMemoOnlyOne(){
        db = getReadableDatabase();
        MemoVO memo = null;
        String sql = "SELECT id,title,content FROM "+TABLE_NAME+" ORDER BY id DESC limit 1";
        Cursor cur = db.rawQuery(sql,null);
        while(cur.moveToNext()){
            memo = new MemoVO();
            memo.setId(cur.getInt(0));
            memo.setTitle(cur.getString(1));
            memo.setContent(cur.getString(2));
        }
        db.close();
        return memo;
    }

    public ArrayList<String> selectTitles(){
        ArrayList<String> titles = new ArrayList<>();
        db = getReadableDatabase();
        String sql = "SELECT title FROM "+TABLE_NAME+" ORDER BY id DESC";
        Cursor cur = db.rawQuery(sql,null);
        while(cur.moveToNext()){
            titles.add(cur.getString(0));
        }
        db.close();
        return titles;
    }

    public ArrayList<MemoVO> readMemoList(){
        ArrayList<MemoVO> memoList = new ArrayList<>();
        db = getReadableDatabase();
        String sql ="SELECT id, title, content FROM "+TABLE_NAME+" ORDER BY id DESC";
        Cursor cur = db.rawQuery(sql,null);
        while(cur.moveToNext()){
            MemoVO m = new MemoVO();
            m.setId(cur.getInt(0));
            m.setTitle(cur.getString(1));
            m.setContent(cur.getString(2));
            memoList.add(m);
        }
        db.close();
        return memoList;
    }

    public void deleteMemo(int id){
        db = getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateMemo(MemoVO mv){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",mv.getTitle());
        cv.put("content",mv.getContent());
        db.update(TABLE_NAME,cv,"id=?",new String[]{String.valueOf(mv.getId())});
    }

}
