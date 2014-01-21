package com.pavanandroid.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ImageSearchActivity extends Activity {

	EditText etQuery;
	GridView gvResults;
	Button butSearch;
	Button butLoadMore;
	ArrayList <Image> imageResults = new ArrayList<Image> ();
	ImageArrayAdapter imageAdapter;
	ImageFilter imageFilter;
	ProgressDialog progressDialog;
	int imageTracker =0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search);
		setupViews();
		imageAdapter = new ImageArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long rowId) {
				Intent i = new Intent(getApplicationContext(), ImageDisplay.class);
				Image imageResultDisplay = imageResults.get(position);
				i.putExtra("image", imageResultDisplay);
				startActivity(i);
			}
		});
		
		ImageFilter custFilter = (ImageFilter) getIntent().getSerializableExtra("filterData");
		if(custFilter == null){
			imageFilter = new ImageFilter();
		}
		else{
			Log.d("RETURN",custFilter.getImageSize());
			imageFilter = custFilter;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_search, menu);
		return true;
	}
	
	private void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQueryId);
		gvResults = (GridView) findViewById(R.id.gvResultsId);
		butSearch = (Button) findViewById(R.id.butSearchId);
		butLoadMore = (Button) findViewById(R.id.loadMoreId);
	}
	
	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Looking for "+query, Toast.LENGTH_SHORT).show();
		displayImage(imageTracker, query);
		butLoadMore.setVisibility(View.VISIBLE);
        
        butLoadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                        // Starting a new async task
                        new loadMoreListView().execute();
                }
        });
	}
	
	public void displayImage(int imageTracker, String query){
		AsyncHttpClient httpClient = new AsyncHttpClient();
		Log.d("RETURN", imageFilter.getImageSize());
		httpClient.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&start=" + imageTracker+
				"&v=1.0&q="+ Uri.encode(query)+"&imgsz="+Uri.encode(imageFilter.getImageSize())+"&imgcolor="+
				Uri.encode(imageFilter.getColorFilter())+"&imgtype="+imageFilter.getImageType()+"&as_sitesearch="+imageFilter.getSiteFilter(), 
				new JsonHttpResponseHandler(){
					@Override
					public void onSuccess(JSONObject response){
						JSONArray imageJsonResults = null;
						try{
							imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
							imageResults.clear();
							imageAdapter.addAll(Image.jsonArrayToArrayList(imageJsonResults));
							
							Log.d("DEBUG", imageResults.toString());
							
						} catch(JSONException e){
							e.printStackTrace();
						}
					}
				}); 
	}
	
	private class loadMoreListView extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
        	progressDialog = new ProgressDialog(ImageSearchActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        protected Void doInBackground(Void... unused) {
        	runOnUiThread(new Runnable() {
        		public void run() {
        			imageTracker += 8;
                    String query = etQuery.getText().toString();
                    displayImage(imageTracker, query);
                }
        	});
        	return (null);
        }
        
        protected void onPostExecute(Void unused) {
                // closing progress dialog
                progressDialog.dismiss();
        }
}
	
	public void onImageSettings(MenuItem mi) {
		
		Intent i = new Intent(getApplicationContext(),SettingActivity.class);
		i.putExtra("prevFilter",imageFilter);
		Log.d("RETURN",imageFilter.getImageSize());
		startActivity(i);
	}

}
