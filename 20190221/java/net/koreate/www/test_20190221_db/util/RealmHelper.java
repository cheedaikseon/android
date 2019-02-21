package net.koreate.www.test_20190221_db.util;

import android.util.Log;

import net.koreate.www.test_20190221_db.vo.MessageVO;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    public void saveToMessage(MessageVO messageVO){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number maxId = realm.where(MessageVO.class).max("id");
                int nextId = (maxId == null? 1 : maxId.intValue() +1);
                messageVO.setId(nextId);
                MessageVO vo = realm.copyToRealm(messageVO);
                Log.i("Message : ", vo.toString());
            }
        });
    }


    //READ
    public ArrayList<MessageVO> retireve(){
        ArrayList<MessageVO> mList = new ArrayList<>();
        RealmResults<MessageVO> messages = realm.where(MessageVO.class).findAll();
        for(MessageVO m : messages){
            mList.add(m);
        }
        return mList;
    }




}
