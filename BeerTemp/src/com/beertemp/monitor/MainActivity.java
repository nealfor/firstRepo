package com.beertemp.monitor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {

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

        TabSpec spec3=tabHost.newTabSpec("Chart");
        spec3.setIndicator("Chart");        
        spec3.setContent(R.id.pastTempChart);

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
