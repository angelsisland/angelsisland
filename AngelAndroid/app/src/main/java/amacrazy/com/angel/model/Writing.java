package amacrazy.com.angel.model;

/**
 * Created by choi on 2015. 1. 26..
 */
public class Writing {
    private String category;
    private String title;
    private String contents;
    private byte[] photo;

    public Writing(String category, String title, String contents, byte[] photo) {
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

    public byte[] getPhoto() {
        return photo;
    }
}
