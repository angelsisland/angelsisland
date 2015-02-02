package amacrazy.com.angel.model;

/**
 * Created by choi on 2015. 1. 26..
 */
public class Writing {
    private String category;
    private String title;
    private String contents;
    private String photo;
    private int commentNum;
    private int wid;

    public int getUid() {
        return uid;
    }

    private int uid;

    public int getWid() {
        return wid;
    }


    public Writing(String category, String title, String contents, String photo) {
        this.category = category;
        this.title = title;
        this.contents = contents;
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getPhoto() {
        return photo;
    }
}
