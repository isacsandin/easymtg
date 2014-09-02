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

	private static HashMap<String, Drawable> manaSymbols = new HashMap<String, Drawable>();
	
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
