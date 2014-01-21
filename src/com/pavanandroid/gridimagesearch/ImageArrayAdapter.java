package com.pavanandroid.gridimagesearch;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ImageArrayAdapter extends ArrayAdapter<Image> {

	public ImageArrayAdapter(Context context, List<Image> images) {
		super(context, R.layout.item_image_view, images);
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Image imageInfo = this.getItem(position);
		SmartImageView sivImage;
		
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			sivImage = (SmartImageView) inflater.inflate(R.layout.item_image_view, parent, false);
		} 
		else {
			sivImage = (SmartImageView) convertView;
			sivImage.setImageResource(android.R.color.transparent);
		}
		sivImage.setImageUrl(imageInfo.getThumbUrl());
		return sivImage;
	}

}
