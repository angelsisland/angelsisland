package angel.island.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by choi on 2015. 2. 8..
 */
public class RawProxy {

    private static String server = "http://54.65.132.14:8080/";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Object receiving;
    protected HttpURLConnection conn;


    public <T> T connect(final String address, final Object jsonObject, final Class<T> receivedType) {
        Thread networkThread = new Thread() {
            @Override
            public void run() {
                try {
                    receiving = proceedConnection(address, jsonObject, receivedType);
                } catch (IOException e) {
                    Log.e("HTTP", e.getMessage());
                    receiving = null;
                }
            }
        };
        networkThread.start();
        try {
            networkThread.join();
        } catch (InterruptedException e) {
            receiving = null;
            Log.e("Thread", e.getMessage());
        }

        return (T) receiving;
    }


    private <T> T proceedConnection(String address, Object jsonObject, Class<T> receivedType) throws IOException {
        HttpURLConnection conn = getConnection(address);
        conn.connect();
        Log.e("HTTP", conn.getResponseCode() + "");

        if (jsonObject != null)
            writeJsonOnConnection(conn, jsonObject);

        if (receivedType != null) {
            String receivedJson = readJsonFromConnection(conn);
            return gson.fromJson(receivedJson, receivedType);
        }

        return null;
    }

    private HttpURLConnection getConnection(String address) throws IOException {
        String servletURL = server + address;
        URL url = new URL(servletURL);
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(10 * 1000);
        conn.setReadTimeout(10 * 1000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("charset", "euc-kr");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        return conn;
    }

    private String readJsonFromConnection(HttpURLConnection conn) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null)
            stringBuilder.append(line);

        bufferedReader.close();
        String receivedJson = stringBuilder.toString();

        return receivedJson;
    }

    private void writeJsonOnConnection(HttpURLConnection conn, Object jsonObject) throws IOException {
        String bodyContent = gson.toJson(jsonObject);
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        bufferedWriter.write(bodyContent);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
