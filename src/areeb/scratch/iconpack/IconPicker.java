package areeb.scratch.iconpack;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

public class IconPicker extends FragmentActivity {
    
    FragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    int Number = 0;
    
      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        	setContentView(R.layout.icon_picker_layout);
        
        	mAdapter = new FragmentAdapter(getSupportFragmentManager());

        	mPager = (ViewPager)findViewById(R.id.pager);
        	mPager.setAdapter(mAdapter);

        	mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
        	mIndicator.setViewPager(mPager);
    }
      
  
    public void info(View view){
    	
    	
    	
    	Toast.makeText(getApplicationContext(), "Icons made by " + getString(R.string.developer_name), Toast.LENGTH_LONG).show();
    	
    }

  
     
    
    
}