package net.koreate.www.test_20190311_network_volley.vo;

public class TestVO {

    private String id;
    private String pw;

    public TestVO(){}

    public TestVO(String id, String pw) {
        this.id=id;
        this.pw=pw;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "TestVO [id=" + id + ", pw=" + pw + "]";
    }
}
