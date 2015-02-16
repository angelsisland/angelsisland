package angel.island.net;

import angel.island.model.Category;
import angel.island.model.Comment;
import angel.island.model.FacebookId;
import angel.island.model.Id;
import angel.island.model.Letter;
import angel.island.model.Writing;

/**
 * Created by choi on 2015. 1. 26..
 */
public class Proxy extends RawProxy {

    private static String serverAddress = "http://54.65.132.14:8080/";

    private static String need = "api/need";
    private static String mypost = "api/mypost";
    private static String letter = "api/letter";
    private static String view = "api/view";

    private static String write = "api/write";
    private static String comment = "api/comment";
    private static String id = "api/login";

    public void sendId(String uid, String accessKey) {
        connect(id, new FacebookId(uid, accessKey), null);
    }

    public Writing[] getPraiseNeed() {
        return connect(need, new Category("praise"), Writing[].class);
    }

    public Writing[] getSolaceNeed() {
        return connect(need, new Category("solace"), Writing[].class);
    }

    public Writing[] getMyPostList(String category) {
        return connect(mypost, new Category(category), Writing[].class);
    }

    public Comment[] getComments(int id) {
        return connect(view, new Id(id), Comment[].class);
    }

    public Letter[] getLetters() {
        return connect(letter, null, Letter[].class);
    }

    public void sendWriting(Writing writing) {
        connect(write, writing, null);
    }

    public void sendComment(Comment commetData) {
        connect(comment, commetData, null);
    }

    /*
    public void uploadImg(String servletName, Bitmap bitmap) {
        try {
            HttpURLConnection conn = getConnection(servletName, "POST");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bytes = stream.toByteArray();
            conn.getOutputStream().write(bytes);
            conn.connect();
            conn.getResponseCode();
        } catch (Exception e) {

        }
    }

    public byte[] convertBitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    public Bitmap byteArrayToBitmap(byte[] $byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
        return bitmap;
    }

    public void downloadImg(String servletName) {
        //Bitmap bitmap = BitmapFactory.decodeByteArray( $byteArray, 0, $byteArray.length ) ;
        //return bitmap;
    }
    */
}