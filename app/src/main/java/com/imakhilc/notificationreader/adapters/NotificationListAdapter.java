package com.imakhilc.notificationreader.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imakhilc.notificationreader.R;
import com.imakhilc.notificationreader.models.NotificationModel;

import java.util.ArrayList;

/**
 * Created by mukesh on 18/5/15.
 */
public class NotificationListAdapter extends BaseAdapter {

    Context context;
    ArrayList<NotificationModel> modelList;

    public NotificationListAdapter(Context context, ArrayList<NotificationModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.list_item, null,true);

        TextView txtTitle = rowView.findViewById(R.id.item_name);
        ImageView imageView = rowView.findViewById(R.id.icon);

        NotificationModel m = modelList.get(position);
        txtTitle.setText(m.getName());
        if(m != null && m.getImage() !=null)
            imageView.setImageBitmap(m.getImage());

        return rowView;

    };
}