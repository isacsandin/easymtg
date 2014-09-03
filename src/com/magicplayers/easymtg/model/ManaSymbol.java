package com.magicplayers.easymtg.model;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.magicplayers.easymtg.R;

public class ManaSymbol {

	private static HashMap<String, Integer> manaSymbols;
	
	static{
		manaSymbols = new HashMap<String, Integer>();
		manaSymbols.put("{G}", R.drawable.mana_symbol_g);
		manaSymbols.put("{W}", R.drawable.mana_symbol_w);
		manaSymbols.put("{B}", R.drawable.mana_symbol_b);
		manaSymbols.put("{U}", R.drawable.mana_symbol_u);
		manaSymbols.put("{R}", R.drawable.mana_symbol_r);
		manaSymbols.put("{S}", R.drawable.mana_symbol_s);
		manaSymbols.put("{1}", R.drawable.mana_symbol_1);
		manaSymbols.put("{2}", R.drawable.mana_symbol_2);
		manaSymbols.put("{3}", R.drawable.mana_symbol_3);
		manaSymbols.put("{4}", R.drawable.mana_symbol_4);
		manaSymbols.put("{5}", R.drawable.mana_symbol_5);
		manaSymbols.put("{6}", R.drawable.mana_symbol_6);
		manaSymbols.put("{7}", R.drawable.mana_symbol_7);
		manaSymbols.put("{8}", R.drawable.mana_symbol_8);
		manaSymbols.put("{9}", R.drawable.mana_symbol_9);
		manaSymbols.put("{10}", R.drawable.mana_symbol_10);
		manaSymbols.put("{11}", R.drawable.mana_symbol_11);
		manaSymbols.put("{12}", R.drawable.mana_symbol_12);
		manaSymbols.put("{13}", R.drawable.mana_symbol_13);
		manaSymbols.put("{14}", R.drawable.mana_symbol_14);
		manaSymbols.put("{15}", R.drawable.mana_symbol_15);
		manaSymbols.put("{16}", R.drawable.mana_symbol_16);
		manaSymbols.put("{17}", R.drawable.mana_symbol_17);
		manaSymbols.put("{18}", R.drawable.mana_symbol_18);
		manaSymbols.put("{19}", R.drawable.mana_symbol_19);
		manaSymbols.put("{20}", R.drawable.mana_symbol_20);
		manaSymbols.put("{100}", R.drawable.mana_symbol_100);
		manaSymbols.put("{1000000}", R.drawable.mana_symbol_1000000);
		manaSymbols.put("{X}", R.drawable.mana_symbol_x);
		manaSymbols.put("{Z}", R.drawable.mana_symbol_z);
		manaSymbols.put("{Y}", R.drawable.mana_symbol_y);
		manaSymbols.put("{2/G}", R.drawable.mana_symbol_2_g);
		manaSymbols.put("{2/W}", R.drawable.mana_symbol_2_w);
		manaSymbols.put("{2/B}", R.drawable.mana_symbol_2_b);
		manaSymbols.put("{2/U}", R.drawable.mana_symbol_2_u);
		manaSymbols.put("{2/R}", R.drawable.mana_symbol_2_r);
		manaSymbols.put("{G/U}", R.drawable.mana_symbol_g_u);
		manaSymbols.put("{G/W}", R.drawable.mana_symbol_g_w);
		manaSymbols.put("{R/G}", R.drawable.mana_symbol_r_g);
		manaSymbols.put("{R/W}", R.drawable.mana_symbol_r_w);
		manaSymbols.put("{U/B}", R.drawable.mana_symbol_u_b);
		manaSymbols.put("{U/R}", R.drawable.mana_symbol_u_r);
		manaSymbols.put("{W/B}", R.drawable.mana_symbol_w_b);
		manaSymbols.put("{W/U}", R.drawable.mana_symbol_w_u);
		manaSymbols.put("{G/P}", R.drawable.mana_symbol_g_p);
		manaSymbols.put("{W/P}", R.drawable.mana_symbol_w_p);
		manaSymbols.put("{B/P}", R.drawable.mana_symbol_b_p);
		manaSymbols.put("{U/P}", R.drawable.mana_symbol_u_p);
		manaSymbols.put("{R/P}", R.drawable.mana_symbol_r_p);
		manaSymbols.put("{P}", R.drawable.mana_symbol_p);
		manaSymbols.put("{hg}", R.drawable.mana_symbol_hg);
		manaSymbols.put("{hw}", R.drawable.mana_symbol_hw);
		manaSymbols.put("{hb}", R.drawable.mana_symbol_hb);
		manaSymbols.put("{hu}", R.drawable.mana_symbol_hu);
		manaSymbols.put("{hr}", R.drawable.mana_symbol_hr);
	}
	
	public static Drawable getManaSymbol(Context ctx, String manaCode){

		Bitmap square1 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.mana_symbol_w);
		Bitmap square2 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.mana_symbol_g);
		Bitmap square3 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.mana_symbol_b);
		Bitmap square4 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.mana_symbol_u);

		Bitmap big = Bitmap.createBitmap(square1.getWidth() * 4, square1.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(big);
		canvas.drawBitmap(square1, 0, 0, null);
		canvas.drawBitmap(square2, square1.getWidth(), 0, null);
		canvas.drawBitmap(square3, square1.getWidth()*2, 0, null);
		canvas.drawBitmap(square4, square1.getWidth()*3, 0, null);
		BitmapDrawable drawable = new BitmapDrawable(ctx.getResources(), big);
		return drawable;
	}
	
}
