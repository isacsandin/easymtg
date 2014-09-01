package com.magicplayers.easymtg.lazylist;

import java.util.ArrayList;

import com.magicplayers.easymtg.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<ItemListView> data;
    private LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity activity, ArrayList<ItemListView> data) {
        this.activity = activity;
        this.data=data;
        this.inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageLoader=new ImageLoader(this.activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public ItemListView getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item_list, null);

        TextView text=(TextView)vi.findViewById(R.id.text);
        ImageView image=(ImageView)vi.findViewById(R.id.imagemview);
        text.setText(data.get(position).getTexto());
        imageLoader.DisplayImage(data.get(position).getImageUrl(), image);
        return vi;
    }
}