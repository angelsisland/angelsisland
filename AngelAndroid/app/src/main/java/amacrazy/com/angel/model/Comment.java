package amacrazy.com.angel.model;

/**
 * Created by choi on 2015. 1. 26..
 */
public class Comment {

    public int getWid() {
        return wid;
    }

    public String getContents() {
        return contents;
    }

    public String getWriter() {
        return writer;
    }

    int wid;
    String contents;
    String writer;

    public Comment(int wid, String contents, String writer) {
        this.wid = wid;
        this.contents = contents;
        this.writer = writer;
    }
}
