package com.beertemp.monitor;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	Temperature temp = new Temperature();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listplaceholder);
        
        
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        
        if (android.os.Build.VERSION.SDK_INT >= 9) { 
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().
        					permitAll().build(); 
        	StrictMode.setThreadPolicy(policy); }
        	        	
        
        
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        
      //Get the data (see above)
    	JSONObject json = 
    	    JSONParser.getJSONfromURL("http://nicktravis.com/beertemp/get.php?format=json&num=6");
    	 
    	    try{
    	    //Get the element that holds the earthquakes ( JSONArray )
    	    JSONArray temperatures = json.getJSONArray("posts");
    	 
    	            //Loop the Array
    	        for(int i=0;i < temperatures.length();i++){                      
    	 
    	            HashMap<String, String> map = new HashMap<String, String>();
    	            JSONObject e = temperatures.getJSONObject(i);
    	 
    	            map.put("lineListNumber",  String.valueOf(i+1));//bulleted points, basically
    	            map.put("reading", "Temp/Time = " + e.getString("reading")+"ºF at "+epochConvert(e.getString("timestamp")));
    	            //map.put("timestamp", "Epoch Time: " +  e.getString("timestamp"));
    	            mylist.add(map);
    	    }
    	    }catch(JSONException e)        {
    	         Log.e("log_tag", "Error parsing data "+e.toString());
    	    }
    	    
        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.main,
        		                        new String[] { "lineListNumber", "reading" },
        		                        new int[] { R.id.item_title, R.id.item_subtitle });
        		 
        setListAdapter(adapter);
        		 
        final ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           @SuppressWarnings("unchecked")
            HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);
        	Toast.makeText(MainActivity.this, "ID '" + o.get("lineListNumber") + "' was clicked.", Toast.LENGTH_SHORT).show();        		 
        	}
        	});

        
        
        
    }//end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public String epochConvert(String epochTime){
    	
    	long date = Long.parseLong(epochTime);
    	date *= 1000;
    	String stringTime = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(date));
    	return(stringTime);
    }
    
}

