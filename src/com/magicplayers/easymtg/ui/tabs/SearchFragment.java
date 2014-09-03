package com.magicplayers.easymtg.ui.tabs;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.magicplayers.easymtg.R;
import com.magicplayers.easymtg.lazylist.ImageLoader;
import com.magicplayers.easymtg.lazylist.LazyListItem;
import com.magicplayers.easymtg.lazylist.LazyListAdapter;
import com.magicplayers.easymtg.model.DatabaseHelper;

public class SearchFragment extends Fragment implements OnItemClickListener {
	public static final String ARG_SECTION_NUMBER = "section_number";

	// List view
	private ListView listView;
	private LazyListAdapter lazyListAdapter;

	private ArrayList<LazyListItem> itens;

	private Context thiscontext;
	private String DATABASE_PATH;
	
	Boolean num = false;

	// Search EditText
	EditText inputSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.search, container, false);
		// Listview Data
		thiscontext = container.getContext();
		DATABASE_PATH = thiscontext.getExternalFilesDir(null).getAbsolutePath()+"/databases/easymtg.sqlite";
		listView = (ListView) rootView.findViewById(R.id.list_view_search);
		inputSearch = (EditText) rootView.findViewById(R.id.inputSearch);
		listView.setOnItemClickListener(this);
		createListView();

		/**
		 * Enabling Search Filter
		 * */
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				lazyListAdapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		return rootView;
	}

	private void createListView() {
		Log.e("CREATELISTVIEW","Begin...");
		itens = new ArrayList<LazyListItem>();
		DatabaseHelper helper = new DatabaseHelper(thiscontext,DATABASE_PATH);
		try {
			List<String[]> cards = helper.getCardNames();
			for (String[] row : cards) {
				Log.e("CREATELISTVIEW",row[0]+" "+row[1]);
				//http://mtgimage.com/set/ARB/Sen%20Triplets.jpg
				String url = String.format("http://mtgimage.com/set/%s/%s.jpg",row[2],row[1]);
				itens.add(new LazyListItem(row[0],url, row[3], row[4]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		lazyListAdapter=new LazyListAdapter(this.getActivity(), itens);
	    listView.setAdapter(lazyListAdapter);
		Log.e("CREATELISTVIEW","End...");
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		LazyListItem item = lazyListAdapter.getItem(arg2);
		Toast.makeText(thiscontext, "Voc� Clicou em: " + item.getTexto(),
				Toast.LENGTH_LONG).show();	
	}
	
}
