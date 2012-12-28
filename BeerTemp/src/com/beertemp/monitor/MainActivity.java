package com.beertemp.monitor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity {
	Temperature temp = new Temperature();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TabHost tabHost=(TabHost)findViewById(R.id.tabhost);
        tabHost.setup();
        
        
        TabSpec spec1=tabHost.newTabSpec("Current");
        spec1.setContent(R.id.currentTempTab);
        spec1.setIndicator("Current Temp");
                        

        TabSpec spec2=tabHost.newTabSpec("Temp Logs");
        spec2.setIndicator("Temp Logs");
        spec2.setContent(R.id.pastTempText);

        
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        
        TextView tempText = (TextView)findViewById(R.id.tempDisplay);
        tempText.setText("XX.XºF");
        
        getXMLValues(temp);
        
        tempText.setText(Float.toString(temp.getTemp()));
        
        
        
    }//end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    void getXMLValues(Temperature temp){
    	String URL = "http://nicktravis.com/beertemp/get.php?format=xml&num=1";
		// XML node keys
		String KEY_ITEM = "temperature"; // parent node
		String KEY_TIME = "timestamp";
		String KEY_TEMP = "reading";
		String KEY_SENS = "sensorID";
		 
		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element
		 
		NodeList nl = doc.getElementsByTagName(KEY_ITEM);
				 
		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			String time = parser.getValue(e, KEY_TIME); // name child value
		    String temperature = parser.getValue(e, KEY_TEMP); // cost child value
		    String sensor = parser.getValue(e, KEY_SENS); // description child value
		    
		    //
		    //TEMPORARILY FORCING JUST ONCE THROUGH
		    //
		    temp.setTemp(Float.parseFloat(temperature));
		    temp.setTime(Integer.parseInt(time));
		    temp.setSensorID(Short.parseShort(sensor));
		    
		    i = nl.getLength();//force just once through until 
		}
		
    }
}

