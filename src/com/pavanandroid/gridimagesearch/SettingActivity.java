package com.pavanandroid.gridimagesearch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

public class SettingActivity extends Activity {

	
	Spinner spImageSize;
	Spinner spColorFilter;
	Spinner spImageType;
	EditText etSiteFilter;
	
	ImageFilter imageFilter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageFilter prevFilter = (ImageFilter) getIntent().getSerializableExtra("prevFilter");
		Log.d("RETURN","AFTER: "+prevFilter.getImageSize());
		imageFilter = prevFilter;
		setContentView(R.layout.activity_setting);
		setupViews();
		spImageSize.setSelection(getIndex(spImageSize, imageFilter.getImageSize()));
		spColorFilter.setSelection(getIndex(spColorFilter,imageFilter.getColorFilter()));
		spImageType.setSelection(getIndex(spImageType,imageFilter.getImageType()));
		etSiteFilter.setText(imageFilter.getSiteFilter());
		
	}
	
	private int getIndex(Spinner spinner, String myString){
		 int index = 0;
		 Log.d("RETURN","spinner count "+spinner.getCount());
		 for (int i=0;i<spinner.getCount();i++){
			 if (spinner.getItemAtPosition(i).equals(myString)){
				 index = i;
			 }
		 }
		 return index;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}
	
	private void setupViews() {
		spImageSize = (Spinner) findViewById(R.id.imageSizeId);
		spColorFilter = (Spinner) findViewById(R.id.colorFilterId);
		spImageType = (Spinner) findViewById(R.id.imageTypeId);
		etSiteFilter = (EditText) findViewById(R.id.siteFilterId);
		
	}
	
	public void onFilterImage(View v) {
		setupViews();
		imageFilter.setImageSize(String.valueOf(spImageSize.getSelectedItem()));
		imageFilter.setColorFilter(String.valueOf(spColorFilter.getSelectedItem()));
		imageFilter.setImageType(String.valueOf(spImageType.getSelectedItem()));
		imageFilter.setSiteFilter(String.valueOf(etSiteFilter.getText().toString()));
		Intent i = new Intent(getApplicationContext(),ImageSearchActivity.class);
		i.putExtra("filterData",imageFilter);
		startActivity(i);
	}

}
