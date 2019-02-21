package net.koreate.www.test_20190221_db.vo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MessageVO extends RealmObject {
    @PrimaryKey
    int id;

    String writer;
    String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
