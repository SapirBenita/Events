package com.example.eventf;


import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.Menu;



	public class TitleActivity extends Activity{
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			setContentView(R.layout.fragmenta);
			//background
			getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		

		}
		
		
	}

