package net.koreate.www.test_20190228_intent.vo;

import java.io.Serializable;

public class MemberVO implements Serializable {

    int uno;
    String userId;
    String userPw;

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "uno=" + uno +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                '}';
    }
}
