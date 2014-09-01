package com.magicplayers.easymtg.lazylist;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.magicplayers.easymtg.R;


@SuppressLint("InflateParams")
public class LazyListAdapter extends BaseAdapter implements Filterable{
    
    private Activity activity;
    private ArrayList<LazyListItem> data;
	private ArrayList<LazyListItem> data_backup;
    private LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyListAdapter(Activity activity, ArrayList<LazyListItem> data) {
        this.activity = activity;
        this.data=data;
        this.data_backup = data;
        this.inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageLoader=new ImageLoader(this.activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public LazyListItem getItem(int position) {
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
    
    @Override
	public Filter getFilter(){
		Filter filter = new Filter() {
		    @Override
		    protected FilterResults performFiltering(CharSequence constraint) {
		        FilterResults results = new FilterResults();
		        // We implement here the filter logic
		        if (constraint == null || constraint.length() == 0) {
		            // No filter implemented we return all the list
		            results.values = data_backup;
		            results.count = data_backup.size();
		        }
		        else {
		            // We perform filtering operation
		        	List<LazyListItem> nItens = new ArrayList<LazyListItem>();
		             
		            for (LazyListItem p : data_backup) {
		                if (p.getTexto().toUpperCase().contains(constraint.toString().toUpperCase()))
		                    nItens.add(p);
		            }
		             
		            results.values = nItens;
		            results.count = nItens.size();
		     
		        }
		        return results;
		    }
		 
		    @SuppressWarnings("unchecked")
			@Override
		    protected void publishResults(CharSequence constraint,FilterResults results) {
		    	data = (ArrayList<LazyListItem>)results.values;
		    	notifyDataSetChanged();
		    }
		};
		return filter;    
	}
}