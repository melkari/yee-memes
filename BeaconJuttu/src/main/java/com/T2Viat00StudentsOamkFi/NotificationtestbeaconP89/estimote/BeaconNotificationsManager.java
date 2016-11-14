package com.T2Viat00StudentsOamkFi.NotificationtestbeaconP89.estimote;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.T2Viat00StudentsOamkFi.NotificationtestbeaconP89.DataGet;
import com.T2Viat00StudentsOamkFi.NotificationtestbeaconP89.JsonUtils;
import com.T2Viat00StudentsOamkFi.NotificationtestbeaconP89.MainActivity;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeaconNotificationsManager implements DataGet.OnRequestDoneInterface{

    private static final String TAG = "BeaconNotifications";

    private BeaconManager beaconManager;

    private List<Region> regionsToMonitor = new ArrayList<>();
    private HashMap<String, String> enterMessages = new HashMap<>();
    private HashMap<String, String> exitMessages = new HashMap<>();
    ArrayList<ArrayList<String>> fullItemData = new ArrayList<ArrayList<String>>();


    private Context context;

    private int notificationID = 0;

    public interface BeaconDataAvailableInterface {
        public void beaconDataAvailable(Map<String,Object> itemInfo);
    }

    BeaconDataAvailableInterface appCallback;

    public BeaconNotificationsManager(Context context, BeaconDataAvailableInterface callbackInterface) {
        this.context = context;
        this.appCallback = callbackInterface;
        beaconManager = new BeaconManager(context);
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                Log.i(TAG, "onEnteredRegion: " + region.getIdentifier());
                String message = enterMessages.get(region.getIdentifier());
                if (message != null) {
                    //showNotification(message);
                }
                getBeaconData();
            }

            @Override
            public void onExitedRegion(Region region) {
                Log.d(TAG, "onExitedRegion: " + region.getIdentifier());
                String message = exitMessages.get(region.getIdentifier());
                if (message != null) {
                    //showNotification(message);
                }
            }
        });
    }

    public void getBeaconData() {
        String url = "http://www.students.oamk.fi/~t4toan00/php/getBeacons.php";
        DataGet getter = new DataGet(url, this);
        getter.start();
    }

    public void addNotification(BeaconID beaconID, String enterMessage, String exitMessage) {
        Region region = beaconID.toBeaconRegion();
        enterMessages.put(region.getIdentifier(), enterMessage);
        exitMessages.put(region.getIdentifier(), exitMessage);
        regionsToMonitor.add(region);
    }

    public void startMonitoring() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                for (Region region : regionsToMonitor) {
                    beaconManager.startMonitoring(region);
                }
            }
        });
    }

    private void showNotification(String message) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Beacon Notifications")
                .setContentText(message)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, builder.build());
    }
    @Override
    public void onRequestDone(String data)
    {
        try
        {
            List<Object> parsed = JsonUtils.toList(new JSONArray(data));
            Map<String,Object> itemInfo = (Map)parsed.get(0);
            /*for(int i= 0; i < parsed.size(); i++) {
                itemInfo = (Map)parsed.get(i);
                ArrayList<String> tempList = new ArrayList<String>();
                Log.i(TAG, itemInfo.get("name").toString() + itemInfo.get("author").toString() + itemInfo.get("year").toString());
            }*/

            appCallback.beaconDataAvailable(itemInfo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
