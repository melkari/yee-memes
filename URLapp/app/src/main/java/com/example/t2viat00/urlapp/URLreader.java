package com.example.t2viat00.urlapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by t2viat00 on 20.9.2016.
 */
public class URLreader extends Thread {
    ArrayList<String> urlcontent = new ArrayList<String>();

    public interface CommInterface {
        public void urlContentAvailable();
    }

    private String url;
    CommInterface uiCallback;

    public URLreader(CommInterface callbackInterface) {
        this.uiCallback = callbackInterface;
    }
    @Override
    public void run() {
        try {

            URL myURL = new URL(url);
            URLConnection yc = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                urlcontent.add(inputLine);
                //System.out.println(inputLine);
            }
            in.close();
            uiCallback.urlContentAvailable();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getURLcontent() {
        return urlcontent;
    }

    public void getURLdata(String givenurl) {
        url = givenurl;
        start();
    }
}
