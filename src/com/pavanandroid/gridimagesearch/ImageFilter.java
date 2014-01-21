package com.pavanandroid.gridimagesearch;

import java.io.Serializable;

public class ImageFilter implements Serializable{
	
	private static final long serialVersionUID = -6696189402355194173L;
	
	private String imageSize;
	private String imageType;
	private String colorFilter;
	private String siteFilter;
	
	public String getImageSize() {
		return imageSize;
	}
	
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	
	public String getImageType() {
		return imageType;
	}
	
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String getColorFilter() {
		return colorFilter;
	}
	
	public void setColorFilter(String colorFilter) {
		this.colorFilter = colorFilter;
	}
	
	public String getSiteFilter() {
		return siteFilter;
	}

	
	public void setSiteFilter(String siteFilter) {
		this.siteFilter = siteFilter;
	}
	
	public ImageFilter(){
		setImageSize("small");
		setColorFilter("white");
		setImageType("photo");
		setSiteFilter("");
	}
	
	

}
