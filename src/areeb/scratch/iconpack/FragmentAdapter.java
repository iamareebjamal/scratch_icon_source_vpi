package areeb.scratch.iconpack;

import com.viewpagerindicator.IconPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter{

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

      @Override
    public int getIconResId(int index) {
        // TODO Auto-generated method stub
        return 0;
    }

      @Override
    public Fragment getItem(int position) 
    {
        // TODO Auto-generated method stub
        Fragment fragment = new MiscLayout();
        switch(position){
        case 0:
            fragment = new MiscLayout();
            break;
        case 1:
            fragment = new SystemLayout();
            break;
        case 2:
            fragment = new GamesLayout();
            break;
        case 3:
            fragment = new AppsLayout();
            break;
        }
        return fragment;
    }

      @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 4;
    }
    
      @Override
    public CharSequence getPageTitle(int position){
        String title = "";
        switch(position){
        case 0:
            title = "Misc.";
            break;
        case 1:
            title = "System";
            break;
        case 2:
            title = "Games";
            break;
        case 3:
            title = "Apps";
            break;
        }
        return title;
    }

}