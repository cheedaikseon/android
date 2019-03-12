package net.koreate.www.test_20190312_socket.vo;

public class ChatMessage {

    // 작성자
    private String who;
    // message
    private String msg;


    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "who='" + who + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
