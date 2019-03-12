package net.koreate.www.test_20190304_intent_callback.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.koreate.www.test_20190304_intent_callback.R;
import net.koreate.www.test_20190304_intent_callback.vo.ContactVO;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<ContactVO> list;
    LayoutInflater li;

    public ListViewAdapter(Context context, ArrayList<ContactVO> list,int layout){
        this.context = context;
        this.list = list;
        this.layout = layout;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView == null){
            convertView = li.inflate(layout,parent,false);
        }
        TextView id = convertView.findViewById(R.id.id);
        TextView name = convertView.findViewById(R.id.name);
        TextView number = convertView.findViewById(R.id.number);

        ContactVO contactVO = list.get(pos);
        id.setText(String.valueOf(contactVO.getId()));
        name.setText(contactVO.getName());
        number.setText(contactVO.getPhoneNumber());
        return convertView;
    }
}
