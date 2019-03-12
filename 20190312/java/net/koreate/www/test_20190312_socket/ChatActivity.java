package net.koreate.www.test_20190312_socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import net.koreate.www.test_20190312_socket.util.ChatListViewAdapter;
import net.koreate.www.test_20190312_socket.util.UrlConfig;
import net.koreate.www.test_20190312_socket.vo.ChatMessage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.msgEdit)
    EditText msgEdit;
    @BindView(R.id.btnSend)
    ImageView btnSend;

    Handler writeHandler;

    ArrayList<ChatMessage> list;
    ArrayAdapter ap;
    // 서버와 연결을 위한 스레드 상태
    boolean flagConnection = true;
    // 소켓 연결 상태
    boolean isConnected = false;
    // 읽기 스레드
    boolean flagRead = true;

    Socket socket;

    SocketThread socketThread;

    ReadThread readThread;

    WriteThread writeThread;

    // 입출력
    BufferedInputStream bis;
    BufferedOutputStream bos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        btnSend.setOnClickListener(this);
        ap = new ChatListViewAdapter(this,R.layout.item_chat,list);
        listView.setAdapter(ap);

        btnSend.setEnabled(false);
        msgEdit.setEnabled(false);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("ChatActivity onStart");
        socketThread = new SocketThread();
        socketThread.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("ChatActivity stop");

        flagConnection = false;
        isConnected = false;
        try {
            if (socket != null) {
                bos.close();
                bis.close();
                socket.close();
            }
        }catch(IOException e){}
    }

    @Override
    public void onClick(View v) {
        if(!msgEdit.getText().toString().trim().equals("")){
            Message msg = new Message();
            msg.obj = msgEdit.getText().toString();
            writeHandler.sendMessage(msg);
            /*addMessage("me",msgEdit.getText().toString());*/
        }
/*        String msg = msgEdit.getText().toString();
        addMessage("me",msg);*/
    }

    public void addMessage(String who, String msg){
        ChatMessage vo = new ChatMessage();
        vo.setWho(who);
        vo.setMsg(msg);
        list.add(vo);
        ap.notifyDataSetChanged();
        listView.setSelection(list.size()-1);
        msgEdit.setText("");
        showToast(msg);
    }

    Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 10){
                // is Connection = true
                showToast("서버 연결 성공");
                btnSend.setEnabled(true);
                msgEdit.setEnabled(true);
            }else if(msg.what == 20){
                // isConnection = false
                showToast("서버 연결 안됨");
                btnSend.setEnabled(false);
                msgEdit.setEnabled(false);
            }else if(msg.what == 100){
                // read msg
                addMessage("you",(String)msg.obj);
            }else if(msg.what == 200){
                // send message
                addMessage("me", (String)msg.obj);
            }
        }
    };



    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    class SocketThread extends Thread{
        @Override
        public void run(){
            while(flagConnection){
                try {
                    if (!isConnected) {
                        socket = new Socket();
                        SocketAddress remoteAddress
                                = new InetSocketAddress(UrlConfig.SERVER_IP, UrlConfig.SERVER_PORT);
                        socket.connect(remoteAddress,10000);
                        bos = new BufferedOutputStream(socket.getOutputStream());
                        bis = new BufferedInputStream(socket.getInputStream());

                        // readThread  writeThread -> 초기화

                        if(writeThread != null){
                            writeHandler.getLooper().quit();
                        }


                        writeThread = new WriteThread();
                        writeThread.start();

                        readThread  = new ReadThread();
                        readThread.start();

                        isConnected = true;

                        Message msg = new Message();
                        msg.what = 10;
                        mainHandler.sendMessage(msg);
                    }else {
                        SystemClock.sleep(10000);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    SystemClock.sleep(10000);
                }
            }
        }
    }

    class ReadThread extends Thread{
        @Override
        public void run(){
            byte[] buffer = null;
            while(flagRead){
                buffer = new byte[1024];
                try{
                    String message  = null;
                    int size = bis.read(buffer);
                    if(size > 0){
                        message = new String(buffer,0, size,"utf-8");
                        if(message != null && !message.equals("")){
                            Message m = new Message();
                            m.what = 100;
                            m.obj = message;
                            mainHandler.sendMessage(m);
                        }
                    }else{
                        flagRead = false;
                        isConnected = false;
                    }
                }catch(Exception e){
                    System.out.println("소켓 연결 오류");
                    flagRead = false;
                    isConnected=false;
                }
            }
            Message msg = new Message();
            msg.what = 20;
            mainHandler.sendMessage(msg);
        }
    }

    class WriteThread extends Thread{
        @Override
        public void run(){
            Looper.prepare();
            writeHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    try{
                        bos.write(((String)msg.obj).getBytes());
                        bos.flush();

                        Message m = new Message();
                        m.what = 200;
                        m.obj = msg.obj;
                        mainHandler.sendMessage(m);
                    }catch(Exception e){
                        isConnected = false;
                        writeHandler.getLooper().quit();
                        flagRead  = false;
                    }
                }
            };

            Looper.loop();
        }
    }

}
