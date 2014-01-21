package com.pavanandroid.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Image implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7586156985327867389L;
	private String fullUrl;
	private String thumbUrl;

	public String getFullUrl(){
		return fullUrl;
	}
	
	public String getThumbUrl(){
		return thumbUrl;
	}
	
	@Override
	public String toString() {
		return thumbUrl;
	}

	public Image(JSONObject json){
		try {
			this.fullUrl = json.getString("url");
			this.thumbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}
	}// end of constructor

	public static ArrayList<Image> jsonArrayToArrayList(JSONArray imageJsonResults) {
		ArrayList<Image> imageResults = new ArrayList<Image> ();
		for(int i=0;i<imageJsonResults.length();i++){
			try {
				imageResults.add(new Image(imageJsonResults.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return imageResults;
	}
	
}
