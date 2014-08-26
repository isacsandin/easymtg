package com.magicplayers.easymtg.ui.tabs;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.magicplayers.easymtg.R;
import com.magicplayers.easymtg.model.Card;
import com.magicplayers.easymtg.model.DatabaseHelper;
import com.magicplayers.easymtg.model.DatabaseManager;
import com.magicplayers.easymtg.ui.lists.AdapterListView;
import com.magicplayers.easymtg.ui.lists.ItemListView;

public class SearchFragment extends Fragment implements OnItemClickListener {
	public static final String ARG_SECTION_NUMBER = "section_number";

	// List view
	private ListView listView;

	private AdapterListView adapterListView;
	private ArrayList<ItemListView> itens;

	private Context thiscontext;

	// Listview Adapter
	ArrayAdapter<String> adapter;

	// Search EditText
	EditText inputSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.search, container, false);
		// Listview Data
		final String products[] = { "Dell Inspiron", "HTC One X",
				"HTC Wildfire S", "HTC Sense", "HTC Sensation XE", "iPhone 4S",
				"Samsung Galaxy Note 800", "Samsung Galaxy S3", "MacBook Air",
				"Mac Mini", "MacBook Pro" };
		thiscontext = container.getContext();
		listView = (ListView) rootView.findViewById(R.id.list_view_search);
		inputSearch = (EditText) rootView.findViewById(R.id.inputSearch);

		listView.setOnItemClickListener(this);

		createListView(products);

		/**
		 * Enabling Search Filter
		 * */
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				itens = new ArrayList<ItemListView>();

				if (cs.length() > 2) {
					DatabaseHelper helper = new DatabaseHelper(thiscontext);
					QueryBuilder<Card, Integer> qb = helper.getCardDao()
							.queryBuilder();
					try {
						qb.where().like("name", "%" + cs + "%");
						PreparedQuery<Card> pq = qb.prepare();
						List<Card> cards = helper.getCardDao().query(pq);
						for (Card c : cards) {
							itens.add(new ItemListView(c.getName(),
									R.drawable.ic_launcher));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// Cria o adapter
				adapterListView = new AdapterListView(thiscontext, itens);
				// Adding items to listview
				listView.setAdapter(adapterListView);
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

	private void createListView(String[] products) {
		itens = new ArrayList<ItemListView>();
		for (int i = 0; i < products.length; i++) {
			itens.add(new ItemListView(products[i], R.drawable.ic_launcher));
		}

		// Cria o adapter
		adapterListView = new AdapterListView(thiscontext, itens);
		// Adding items to listview
		listView.setAdapter(adapterListView);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		ItemListView item = adapterListView.getItem(arg2);
		// Demostração
		Toast.makeText(thiscontext, "Você Clicou em: " + item.getTexto(),
				Toast.LENGTH_LONG).show();
	}
}
