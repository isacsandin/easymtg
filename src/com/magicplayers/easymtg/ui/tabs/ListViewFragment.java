package com.magicplayers.easymtg.ui.tabs;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.magicplayers.easymtg.R;
import com.magicplayers.easymtg.ui.lists.AdapterListView;
import com.magicplayers.easymtg.ui.lists.ItemListView;

/**
 * A dummy fragment representing a section of the app, but that simply displays
 * dummy text.
 */
public class ListViewFragment extends Fragment implements OnItemClickListener {

	public static final String ARG_SECTION_NUMBER = "section_number";
	private ListView listView;
	private AdapterListView adapterListView;
	private ArrayList<ItemListView> itens;
	private Context thiscontext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_view, container, false);
		// Bundle args = getArguments();
		// ((TextView) rootView.findViewById(android.R.id.text1)).setText(
		// getString(R.string.dummy_section_text,
		// args.getInt(ARG_SECTION_NUMBER)));

		// Pega a referencia do ListView
		thiscontext = container.getContext();
		listView = (ListView) rootView.findViewById(R.id.list);
		// Define o Listener quando alguem clicar no item.
		listView.setOnItemClickListener(this);

		createListView();

		return rootView;
	}

	private void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itens = new ArrayList<ItemListView>();
		ItemListView item1 = new ItemListView("Deck 1",
				R.drawable.icon);
		ItemListView item2 = new ItemListView("Deck 2",
				R.drawable.icon);
		ItemListView item3 = new ItemListView("Deck 3",
				R.drawable.icon);
		ItemListView item4 = new ItemListView("Deck 4",
				R.drawable.icon);

		itens.add(item1);
		itens.add(item2);
		itens.add(item3);
		itens.add(item4);

		// Cria o adapter
		adapterListView = new AdapterListView(thiscontext, itens);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		ItemListView item = adapterListView.getItem(arg2);
		Toast.makeText(thiscontext, "Você Clicou em: " + item.getTexto(),
				Toast.LENGTH_LONG).show();
	}
}
