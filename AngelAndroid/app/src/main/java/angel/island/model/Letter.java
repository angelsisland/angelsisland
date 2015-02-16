package angel.island.model;

/**
 * Created by choi on 2015. 2. 3..
 */
public class Letter {

    String title;
    String contents;

    public Letter(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
