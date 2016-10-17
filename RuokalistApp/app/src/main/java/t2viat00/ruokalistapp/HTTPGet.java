package t2viat00.ruokalistapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by melkari on 17.10.2016.
 */
public class HTTPGet extends Thread {
    private String menurl;
    private String httpdata = "";

    public interface OnRequestDoneInterface
    {
        public void onRequestDone(String data);
    }

    OnRequestDoneInterface callback;

    public HTTPGet(String url, OnRequestDoneInterface cbInterface)
    {
        menurl = url;
        this.callback = cbInterface;
    }

    @Override
    public void run() {
        try {
            URL myURL = new URL(menurl);
            URLConnection yc = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                httpdata = httpdata + inputLine;
            }
            in.close();
            callback.onRequestDone(httpdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
