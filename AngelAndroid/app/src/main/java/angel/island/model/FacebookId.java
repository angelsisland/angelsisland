package angel.island.model;

/**
 * Created by choi on 2015. 2. 4..
 */
public class FacebookId {
    String id;
    String accesskey;

    public FacebookId(String id, String accesskey) {
        this.id = id;
        this.accesskey = accesskey;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public FacebookId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
