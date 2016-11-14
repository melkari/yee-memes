package com.example.veikko.weathergetter;

import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EBean;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * Created by veikko on 14.9.15.
 */
public class WeatherEngine
{
    private String httpdata = "";

    // This interface is used to report data back to UI
    public interface WeatherDataAvailableInterface
    {
        // This method is called back in background thread.
        public void weatherDataAvailable();
    }

    double KELVIN_CONVERT = 273.15;

    protected String temperature;
    protected String iconId;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    WeatherDataAvailableInterface uiCallback;
    public WeatherEngine(WeatherDataAvailableInterface callbackInterface)
    {
        this.uiCallback = callbackInterface;
    }

    public void httpGet(String city)
    {
        try {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=65dbec3aae5e5bf9000c7a956c8b76f6";
            URL myURL = new URL(url);
            URLConnection yc = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                httpdata = httpdata + inputLine;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        onRequestDone(httpdata);
    }



    public void onRequestDone(String data)
    {
        try
        {
            Map<String, Object> parsed = JsonUtils.jsonToMap(new JSONObject(data));
            Map<String, Object> mainElement = (Map) parsed.get("main");
            double temp = (double)mainElement.get("temp");
            double tempInC = temp - KELVIN_CONVERT;
            this.temperature = String.format("%.1f", tempInC);

            ArrayList<Map<String, Object>> array = (ArrayList<Map<String, Object>>)parsed.get("weather");
            Map<String, Object> weatherElement = array.get(0);
            iconId = (String)weatherElement.get("icon");

            uiCallback.weatherDataAvailable();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
