package com.pavanandroid.gridimagesearch;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ImageDisplay extends Activity {

	String fullUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		Image imageResultDisplay = (Image) getIntent().getSerializableExtra("image");
		SmartImageView sivImageView = (SmartImageView) findViewById(R.id.sivImageFullId);
		sivImageView.setImageUrl(imageResultDisplay.getFullUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

}
