package com.magicplayers.easymtg.ui.lists;

import java.util.ArrayList;
import java.util.List;

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

public class AdapterListView extends BaseAdapter implements Filterable {
	private LayoutInflater mInflater;
	private ArrayList<ItemListView> itens_backup;
	private ArrayList<ItemListView> itens;

	public AdapterListView(Context context, ArrayList<ItemListView> itens) {
		// Itens que preencheram o listview
		this.itens = itens;
		this.itens_backup=itens;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}

	/**
	 * Retorna a quantidade de itens
	 * 
	 * @return
	 */
	public int getCount() {
		return itens.size();
	}

	/**
	 * Retorna o item de acordo com a posicao dele na tela.
	 * 
	 * @param position
	 * @return
	 */
	public ItemListView getItem(int position) {
		return itens.get(position);
	}

	/**
	 * Sem 
	 * 
	 * @param position
	 * @return
	 */
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		// Pega o item de acordo com a 
		ItemListView item = itens.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.item_list_deck, null);

		// atravez do layout pego pelo LayoutInflater, pegamos cada id
		// relacionado
		// ao item e definimos as .
		((TextView) view.findViewById(R.id.deckName)).setText(item.getTexto());
		((ImageView) view.findViewById(R.id.deckIcon)).setImageResource(item
				.getIconeRid());

		return view;
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
		            results.values = itens_backup;
		            results.count = itens_backup.size();
		        }
		        else {
		            // We perform filtering operation
		        	List<ItemListView> nItens = new ArrayList<ItemListView>();
		             
		            for (ItemListView p : itens_backup) {
		                if (p.getTexto().toUpperCase().contains(constraint.toString().toUpperCase()))
		                    nItens.add(p);
		            }
		             
		            results.values = nItens;
		            results.count = nItens.size();
		     
		        }
		        return results;
		    }
		 
		    @Override
		    protected void publishResults(CharSequence constraint,FilterResults results) {
		    	itens = (ArrayList<ItemListView>)results.values;
		    	notifyDataSetChanged();
		    }
		};
		return filter;    
	}
}