package com.example.eventf;



import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.androidquery.AQuery;



import android.R.layout;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class FragmentData extends Fragment{
	 private AQuery aq;
	TextView TextData;
	TextView TextHours;
	TextView TextPlace;
	TextView TextTitle;
	TextView TextDate;
	ImageView imageView;
  
//	Context context;
	
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		// TODO Auto-generated method 
		
		//aq = new AQuery(this);
		Intent launchingIntent = getActivity().getIntent();
		
		String data = launchingIntent.getStringExtra("Data");
		String hours = launchingIntent.getStringExtra("Hours");
		String place = launchingIntent.getStringExtra("Place");
		String title= launchingIntent.getStringExtra("Title");
		String date= launchingIntent.getStringExtra("Date");
		String image =launchingIntent.getStringExtra("Image");
		

		   Bitmap bitmap;
		View viewer = (View) inflater.inflate(R.layout.viewer, container, false);
		
		TextHours = (TextView) viewer.findViewById(R.id.hours);
		TextHours.setText(hours);
		
		
		TextDate = (TextView) viewer.findViewById(R.id.date);
		TextDate.setText(date);
		
		
		TextTitle = (TextView) viewer.findViewById(R.id.title);
		TextTitle.setText(title);
		
		TextPlace = (TextView) viewer.findViewById(R.id.place);
		TextPlace.setText(place);
		
		if(!(data.equals("null"))){
		
		TextData= (TextView) viewer.findViewById(R.id.data);
		TextData.setHint(data+"\n");
		
		
	   
		aq = new AQuery(getActivity(), viewer);
		
		bitmap = aq.getCachedImage(image);
		
		if(bitmap ==null)
			aq.id(R.id.background).image(image);
		
		else 
			aq.id(R.id.background).image(bitmap);
		
		
		
	
		}
		
		

		
		return viewer;
	}
	
	

}