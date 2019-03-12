package net.koreate.www.test_20190304_intent_callback.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import net.koreate.www.test_20190304_intent_callback.vo.ContactVO;

import java.util.ArrayList;

public class ContactUtil {
    Context context;

    public ContactUtil(Context context){
        this.context= context;
    }

    public ArrayList<ContactVO> getContectList(){
        ArrayList<ContactVO> contactList = new ArrayList<>();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] proj = new String[]{
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        };

        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                +" COLLATE LOCALIZED ASC";

        Cursor contactCursor
            = context.getContentResolver().query(uri,proj,null,null,sortOrder);

        if(contactCursor.moveToFirst()){
            do{
                ContactVO contact = new ContactVO();
                contact.setId(contactCursor.getLong(0));
                contact.setPhoneNumber(contactCursor.getString(1));
                contact.setName(contactCursor.getString(2));
                contactList.add(contact);

            }while(contactCursor.moveToNext());
        }
        return contactList;
    }


}
