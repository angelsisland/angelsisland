package angel.island.model;

/**
 * Created by choi on 2015. 1. 26..
 */
public class Comment {

    public int getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public String getWriter() {
        return writer;
    }

    int id;
    String contents;
    String writer;

    public Comment(int id, String contents, String writer) {
        this.id = id;
        this.contents = contents;
        this.writer = writer;
    }

    public Comment(int id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}
