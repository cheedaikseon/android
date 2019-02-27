package net.koreate.www.test_20190227;

public class RecyclerTestVO {

    private String title;
    private int img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "RecyclerTestVO{" +
                "title='" + title + '\'' +
                ", img=" + img +
                '}';
    }
}
