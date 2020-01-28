package com.imakhilc.notificationreader.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.widget.ListView;
import com.imakhilc.notificationreader.R;
import com.imakhilc.notificationreader.adapters.NotificationListAdapter;
import com.imakhilc.notificationreader.models.NotificationModel;

import java.util.ArrayList;

/**
 * Created by mukesh on 19/5/15.
 */
public class MainActivity extends Activity {

    ListView list;
    NotificationListAdapter adapter;
    ArrayList<NotificationModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelList = new ArrayList<>();
        adapter = new NotificationListAdapter(getApplicationContext(), modelList);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(
                        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private BroadcastReceiver onNotice = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");
            //int id = intent.getIntExtra("icon",0);

            try {
                byte[] byteArray = intent.getByteArrayExtra("icon");
                Bitmap bmp = null;
                if (byteArray != null) {
                    bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                }
                NotificationModel model = new NotificationModel();
                model.setName(title + " " + text);
                model.setImage(bmp);

                if (modelList != null) {
                    modelList.add(model);
                    adapter.notifyDataSetChanged();
                } else {
                    modelList = new ArrayList<>();
                    modelList.add(model);
                    adapter = new NotificationListAdapter(getApplicationContext(), modelList);
                    list = findViewById(R.id.list);
                    list.setAdapter(adapter);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}