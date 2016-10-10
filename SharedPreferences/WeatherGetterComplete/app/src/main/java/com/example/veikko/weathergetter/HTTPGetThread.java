package com.example.veikko.weathergetter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by melkari on 24.9.2016.
 */
public class HTTPGetThread extends Thread{
    private String url;
    private String httpdata = "";

    public interface OnRequestDoneInterface {
        public void onRequestDone(String data);
    }

    OnRequestDoneInterface callback;

    public HTTPGetThread(String givenurl, OnRequestDoneInterface cbInterface) {
        url = givenurl;
        this.callback = cbInterface;
    }

    @Override
    public void run() {
        try {
            URL myURL = new URL(url);
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
