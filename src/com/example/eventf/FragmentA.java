package com.example.eventf;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class FragmentA extends ListFragment {
	private GetTweetsAsync mTask;
	ArrayList<Event> events;
	
@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	
		new GetTweetsAsync().execute();
	}


	

	private class GetTweetsAsync extends AsyncTask<String, Integer, ArrayList<Event>> {

		@Override
		protected ArrayList<Event> doInBackground(String... strings) {
			events = getEvents();
			return events;		
		}

	
		@Override
		protected void onPostExecute(ArrayList<Event> events) {
			
			UserItemAdapter adapter = new UserItemAdapter(getActivity(),R.layout.listitem, events);
			getListView().setDivider(null);
			setListAdapter(adapter);
			
		}
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent launchingIntent = new Intent(getActivity(), DetailActivity.class);
		launchingIntent.putExtra("Data", events.get(position).data);
		launchingIntent.putExtra("Hours", events.get(position).hours);
		launchingIntent.putExtra("Place", events.get(position).place);
		launchingIntent.putExtra("Title", events.get(position).title);
		launchingIntent.putExtra("Date", events.get(position).date);
		launchingIntent.putExtra("Image", events.get(position).imageUrl);
		
		startActivity(launchingIntent);
	}

	// @Override

	public class UserItemAdapter extends ArrayAdapter<Event> {
		private ArrayList<Event> events;
		private Context context;

		public UserItemAdapter(Context context, int textViewResourceId,
				ArrayList<Event> events) {
			super(context, textViewResourceId, events);
			this.events = events; // get the array list 
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			EventItemViewHolder viewHolder = null;
			if (convertView == null) {
				// if null we need to create
				LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.listitem, null);
				viewHolder = new EventItemViewHolder();
				viewHolder.day = (TextView) convertView.findViewById(R.id.day);
				viewHolder.place = (TextView) convertView.findViewById(R.id.place);
				viewHolder.hours = (TextView) convertView.findViewById(R.id.hours);
				viewHolder.title = (TextView) convertView.findViewById(R.id.title);
				viewHolder.month = (TextView) convertView.findViewById(R.id.month);
				viewHolder.year = (TextView) convertView.findViewById(R.id.year);
			
				convertView.setTag(viewHolder);
			}
			else {
				viewHolder = (EventItemViewHolder) convertView.getTag();
			}

			Event event = events.get(position); 
			if (event != null) {
				viewHolder.year.setHint(event.year);
				viewHolder.title.setText(event.title);
				String upper = event.month.toUpperCase();
				
			
				viewHolder.month.setText(upper);

				viewHolder.day.setText(event.day);
				
				/*FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				if(event.day.length() ==2){
					lp.setMargins(32, 59,0, 0);          
					viewHolder.day.setLayoutParams(lp);
				}
				
				else{
					lp.setMargins(39, 59,0, 0);          
					viewHolder.day.setLayoutParams(lp);
				}*/
				
				viewHolder.place.setHint(event.place); 
				viewHolder.hours.setHint(event.hours); 
			}
			return convertView;
		}

	}
	
	private static class EventItemViewHolder {
		private TextView day;
		private TextView place;
		private TextView hours;
		private TextView title;
		private TextView month;
		private TextView year;
		
	}

	
	
	public ArrayList<Event> getEvents() {

		String searchUrl = "http://cms.mobile.conduit-services.com/calendar/2/?id=8592gjltnhrujne0m08i4jgp04%40group.calendar.google.com&max-results=25&start-index=0&since=%24date&until=%24date%2B6m&params=%7B%22order%22%3A%22asc%22%7D";
		ArrayList<Event> events = new ArrayList<Event>();

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(searchUrl);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = null;
		try {// get data
			responseBody = client.execute(get, responseHandler);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		JSONObject jsonObject = null;
		
		try {
			jsonObject = new JSONObject(responseBody);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray jsonArray = null;
		JSONArray jsonArrayTimes = null;
		
		try {
			jsonObject = jsonObject.getJSONObject("result");
		
			jsonArray = jsonObject.getJSONArray("items");
			
	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String data;
		String date;
		String dayString;
		String day;
		String hours;
		String month;
		String place;
		String title;
		String year;
		String imageUrl;
		long startTime;
		long endTime;
		long gmt;
		int i;
		
		for (i= 0; i < jsonArray.length(); i++)
			try {
				
				data = (jsonArray.getJSONObject(i)).get("description").toString();
				place = (jsonArray.getJSONObject(i)).get("location").toString();
				title = (jsonArray.getJSONObject(i)).get("title").toString();
				imageUrl =  (jsonArray.getJSONObject(i)).get("imageUrl").toString();
				
				jsonArrayTimes = (jsonArray.getJSONObject(i)).getJSONArray("times");
				endTime=jsonArrayTimes.getJSONObject(0).getJSONObject("end").getLong("localTime");
			    startTime=jsonArrayTimes.getJSONObject(0).getJSONObject("start").getLong("localTime");
			    gmt=jsonArrayTimes.getJSONObject(0).getJSONObject("start").optLong("timeZoneOffset");
				
				
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				
				
				TimeZone tz = cal.getTimeZone();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MMMMM,d,h:mmaaa,EEEEEEEE",Locale.ENGLISH);
				sdf.setTimeZone(tz);
		
				String localTimeStart = sdf.format(new Date(startTime * 1000));
				String localTimeEnd = sdf.format(new Date(endTime * 1000));
				String[] partsEnd =localTimeEnd.split(",");
				String[] partsStart= localTimeStart.split(",");
				
				year=partsStart[0];
				month=partsStart[1];
				day=partsStart[2];
				dayString =partsStart[4];
			    hours=partsStart[3]+" - "+partsEnd[3]+" (GMT";
			    if (gmt!=0)
			    hours= hours+" "+gmt+")";
			    else
			    hours= hours+")";
				date =dayString+",  "+ month+" "+day+", "+year;
				
			    Event event = new Event(data,date,day,month,year,place, hours, title,imageUrl);
			    events.add(event);

			}

			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return events;

	}

}
