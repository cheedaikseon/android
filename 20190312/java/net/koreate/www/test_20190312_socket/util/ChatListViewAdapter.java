package net.koreate.www.test_20190312_socket.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.koreate.www.test_20190312_socket.R;
import net.koreate.www.test_20190312_socket.vo.ChatMessage;

import java.util.ArrayList;

public class ChatListViewAdapter extends ArrayAdapter<ChatMessage> {

    ArrayList<ChatMessage> list;
    Context context;
    int layout;

    public ChatListViewAdapter(Context context, int layout, ArrayList<ChatMessage> list) {
        super(context,layout,list);
        this.list=list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater
                    = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
        }
        TextView list_msg = convertView.findViewById(R.id.list_msg);
        RelativeLayout.LayoutParams params = ( RelativeLayout.LayoutParams)list_msg.getLayoutParams();



        ChatMessage msg = list.get(position);
        String sendMsg = "";
        if(msg.getWho().equals("me")){
            sendMsg = "나 : " + msg.getMsg();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
            list_msg.setTextColor(Color.WHITE);
            list_msg.setBackgroundResource(R.drawable.chat_right);
        }else{
            sendMsg = "상대방 : " + msg.getMsg();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
            list_msg.setTextColor(Color.BLACK);
            list_msg.setBackgroundResource(R.drawable.chat_left);
        }
        list_msg.setText(sendMsg);
        return convertView;
    }
}
