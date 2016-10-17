package t2viat00.ruokalistapp;

import android.util.Log;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by melkari on 17.10.2016.
 */
public class FoodEngine implements HTTPGet.OnRequestDoneInterface{
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String name;
    String lunchTime;
    ArrayList<ArrayList<String>> fullMenu = new ArrayList<ArrayList<String>>();

    public interface MenuDataAvailableInterface
    {
        public void menuDataAvailable();
    }

    MenuDataAvailableInterface uiCallback;

    public FoodEngine(MenuDataAvailableInterface callbackInterface)
    {
        this.uiCallback = callbackInterface;
    }
    public ArrayList<ArrayList<String>> getFullMenu()
    {
        return fullMenu;
    }
    public String getName()
    {
        return name;
    }
    public String getLunchTime()
    {
        return lunchTime;
    }

    public void getMenu(Date date)
    {
        String date_string = dateFormat.format(date);
        String url = "http://www.amica.fi/modules/json/json/Index?costNumber=0235&firstDay="+ date_string +"&language=fi";
        HTTPGet getter = new HTTPGet(url, this);
        getter.start();
    }

    @Override
    public void onRequestDone(String data)
    {
       try
       {
           Map<String, Object> parsed = JsonUtils.jsonToMap(new JSONObject(data));
           name = (String) parsed.get("RestaurantName");
           ArrayList<Map<String, Object>> menusForDays = (ArrayList<Map<String, Object>>) parsed.get("MenusForDays");
           lunchTime = (String) menusForDays.get(0).get("LunchTime");
           ArrayList<Map<String, Object>> setMenus = (ArrayList<Map<String, Object>>) menusForDays.get(0).get("SetMenus");

           for(int i= 0; i < setMenus.size(); i++) {
               ArrayList<String> menuComponents = (ArrayList<String>) setMenus.get(i).get("Components");
               fullMenu.add(menuComponents);
           }
           uiCallback.menuDataAvailable();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }
}
