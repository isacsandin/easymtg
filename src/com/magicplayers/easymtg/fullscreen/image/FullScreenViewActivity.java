package com.magicplayers.easymtg.fullscreen.image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.magicplayers.easymtg.R;
import com.magicplayers.easymtg.lazylist.ImageLoader;

public class FullScreenViewActivity extends Activity {
	private ImageLoader imageLoader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fullscreen_image);
		this.imageLoader=new ImageLoader(this.getApplicationContext());
		ImageView image=(ImageView)this.findViewById(R.id.imagemview);
		Intent i = getIntent();
		String photoUrl = i.getExtras().getString("photoUrl");
		imageLoader.DisplayImage(photoUrl, image);
	}

}
