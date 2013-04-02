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
	


public void  setTime (String hours,String day, String month,String year,String date ){
	
	this.hours=hours;
	this.day=day;
	this.month=month;
	this.year=year;
	this.date=date;
	
}

public void setData(String data){
	
this.data=data;	
}

public void setPlace(String place){

this.place=place;	
}

public void setTitle(String title){
	
	this.title=title;
	
}

public void setImageUrl(String imageUrl){

this.imageUrl=imageUrl;


}

}
