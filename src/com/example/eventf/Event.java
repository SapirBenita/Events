package com.example.eventf;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Event {
	
	public String date;
	public String data;
    public String day;
	public String month;
	public String place;
	public String hours;
	public String title;
	public String year;
	public String imageUrl;
	

public Event(String data,String date,String day,String month,String year,String place,String hours,String title,final String imageUrl){
	
	this.data=data;
	this.day=day;
	this.hours=hours;
	this.place=place;
	this.title=title;
	this.month=month;
	this.year=year;
	this.date=date;
	this.imageUrl=imageUrl;
	
	
}

public Bitmap getBitmap(String bitmapUrl) {
	try {
		URL url = new URL(bitmapUrl);
		return BitmapFactory.decodeStream(url.openConnection() .getInputStream()); 
	}
	catch(Exception ex) {return null;}
}





}
