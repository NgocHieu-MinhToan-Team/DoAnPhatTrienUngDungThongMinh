package com.example.pepperluchapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pepperluchapplication.Model.ItemProfileMenu;
import com.example.pepperluchapplication.R;

import java.util.ArrayList;

public class ProfileMenuAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ItemProfileMenu> data;

    public ProfileMenuAdapter(Context context, ArrayList<ItemProfileMenu> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_profile_menu, null);

        ItemProfileMenu itemProfileMenu = data.get(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.imgIconProfileMenu);
        icon.setImageResource(itemProfileMenu.getIcon());
        TextView itemTitle = (TextView) convertView.findViewById(R.id.txtItemProfileMenu);
        itemTitle.setText(itemProfileMenu.getTitleItem());

        return convertView;
    }
}
