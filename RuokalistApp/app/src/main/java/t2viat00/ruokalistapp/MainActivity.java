package t2viat00.ruokalistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EActivity
public class MainActivity extends AppCompatActivity implements FoodEngine.MenuDataAvailableInterface {

    ArrayList<String> menuList = new ArrayList<String>();

    FoodEngine engine = new FoodEngine(this);
    String items = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGettingMenu();
    }

    public void startGettingMenu()
    {
        Date date = new Date();
        engine.getMenu(date);
    }


    protected void updateUI()
    {
        try {
            TextView textViewName = (TextView) findViewById(R.id.textViewName);
            TextView textViewTime = (TextView) findViewById(R.id.textViewTime);
            final ListView listViewMenu = (ListView)findViewById(R.id.listViewMenu);
            final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
            listViewMenu.setAdapter(aa);
            textViewName.setText(engine.getName());
            textViewTime.setText(engine.getLunchTime());
            for (ArrayList<String> array : engine.getFullMenu()) {
                for (String line : array) {
                    items = items + "\n" + line;
                    listViewMenu.setAdapter(aa);
                }
                menuList.add(items);
                items = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void menuDataAvailable()
    {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }
}
