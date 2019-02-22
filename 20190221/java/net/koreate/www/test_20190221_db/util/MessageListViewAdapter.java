package net.koreate.www.test_20190221_db.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.koreate.www.test_20190221_db.R;
import net.koreate.www.test_20190221_db.vo.MessageVO;

import java.util.ArrayList;

public class MessageListViewAdapter extends BaseAdapter {

    ArrayList<MessageVO> messageList;
    Context context;
    int layout;
    LayoutInflater li;

    public MessageListViewAdapter(Context context,ArrayList<MessageVO> messageList){
        this.context=context;
        this.messageList = messageList;
        //this.layout = layout;
        this.layout = R.layout.realm_listview;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messageList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = li.inflate(layout,parent,false);
        }
        MessageVO messageVO = messageList.get(position);
        TextView messageID = convertView.findViewById(R.id.messageID);
        TextView messageWriter = convertView.findViewById(R.id.messageWriter);
        TextView message = convertView.findViewById(R.id.message);

        messageID.setText(String.valueOf(messageVO.getId()));
        messageWriter.setText(messageVO.getWriter());
        message.setText(messageVO.getMessage());


        return convertView;
    }
}
