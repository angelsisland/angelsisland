package angel.island.model;

/**
 * Created by choi on 2015. 1. 26..
 */
public class Writing {
    private String category;
    private String title;
    private String contents;
    private String writer;
    private int commentnum;
    private int id;

    public Writing(String title, String contents, String category) {
        this.title = title;
        this.contents = contents;
        this.category = category;
    }

    public Writing(String title, String contents, String writer, int id) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.id = id;
    }

    public Writing(String title, String contents, String writer, int id, int commentnum) {
        this(title,contents,writer,id);
        this.commentnum = commentnum;
    }

    public Writing() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
