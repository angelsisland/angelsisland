package amacrazy.com.angel.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by choi on 2015. 1. 26..
 */
public class HttpHelper {

    private static String serverAddress = "http://10.73.42.200:8080";

    private static HttpURLConnection getConnection(String servletName, String method) {
        String servletURL = serverAddress + servletName;

        try {
            URL url = new URL(servletURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10 * 1000);
            conn.setReadTimeout(10 * 1000);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("charset", "euc-kr");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("getConnection : ", "Error!");
            return null;
        }
    }

    public <T> T connect(String servletName, String method, Object attachment, Class<T> receivedType) {
        try {
            HttpURLConnection conn = getConnection(servletName, method);
            DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if (attachment != null) {
                String bodyContent = gson.toJson(attachment);
                writer.writeUTF(bodyContent);
                writer.close();
            }
            conn.connect(); //connect 위치 조절이 필요한가
            conn.getResponseCode();
            if(receivedType != null) {
                DataInputStream reader = new DataInputStream(conn.getInputStream());
                String receivedJson = reader.readUTF();
                reader.close();
                return gson.fromJson(receivedJson, receivedType);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void uploadImg(String servletName, Bitmap bitmap) {
        try {
            HttpURLConnection conn = getConnection(servletName, "POST");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bytes = stream.toByteArray();
            conn.getOutputStream().write(bytes);
            conn.connect();
            conn.getResponseCode();
        } catch(Exception e) {

        }
    }

    public byte[] convertBitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    public Bitmap byteArrayToBitmap( byte[] $byteArray ) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length) ;
        return bitmap ;
    }

    public void downloadImg(String servletName) {
         //Bitmap bitmap = BitmapFactory.decodeByteArray( $byteArray, 0, $byteArray.length ) ;
         //return bitmap;
    }
}