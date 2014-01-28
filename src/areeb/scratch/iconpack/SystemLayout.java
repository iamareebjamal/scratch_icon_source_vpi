package areeb.scratch.iconpack;


import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class SystemLayout extends Fragment implements OnItemClickListener {
    
	private static final String ACTION_ADW_PICK_ICON="org.adw.launcher.icons.ACTION_PICK_ICON";
	private boolean mPickerMode=false;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.system_layout, null);
        
        return v;
        
    }
    
    @Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		int iconSize=getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);
        
        GridView g=(GridView) getActivity().findViewById(R.id.system_icon_grid);
        g.setNumColumns(GridView.AUTO_FIT);
        g.setColumnWidth(iconSize);
        g.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
        g.setVerticalSpacing(iconSize/3);
		g.setOnItemClickListener(this);
        IconsAdapter adapter=new IconsAdapter(getActivity(),iconSize);
        g.setAdapter(adapter);
        
        if(getActivity().getIntent().getAction().equals(ACTION_ADW_PICK_ICON)){
        	mPickerMode=true;
        }
		
		
    }




	private class IconsAdapter extends BaseAdapter{
		private Context mContext;
		private int mIconSize;
		public IconsAdapter(Context mContext, int iconsize) {
			super();
			this.mContext = mContext;
			this.mIconSize = iconsize;
			loadIcons();
		}

		@Override
		public int getCount() {
			return mThumbs.size();
		}

		@Override
		public Object getItem(int position) {
		    Options opts=new BitmapFactory.Options();
		    opts.inPreferredConfig=Bitmap.Config.ARGB_8888;
		    return BitmapFactory.decodeResource(mContext.getResources(), mThumbs.get(position), opts);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(mIconSize, mIconSize));
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(mThumbs.get(position));
            return imageView;
		}
		
		private ArrayList<Integer> mThumbs;

	    private void loadIcons() {
	        mThumbs = new ArrayList<Integer>();

	        final Resources resources = getResources();
	        final String packageName = getActivity().getApplication().getPackageName();

	        addIcons(resources, packageName, R.array.system_icons);
	    }
	    private void addIcons(Resources resources, String packageName, int list) {
	        final String[] extras = resources.getStringArray(list);
	        for (String extra : extras) {
	            int res = resources.getIdentifier(extra, "drawable", packageName);
	            if (res != 0) {
	                final int thumbRes = resources.getIdentifier(extra,"drawable", packageName);
	                if (thumbRes != 0) {
	                    mThumbs.add(thumbRes);
	                }
	            }
	        }
	    }
		
	}


	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		if(mPickerMode){
			Intent intent=new Intent();
			Bitmap bitmap=null;
			try{
				bitmap=(Bitmap) adapter.getAdapter().getItem(position);
			}catch (Exception e) {
			}
			if(bitmap!=null){
				intent.putExtra("icon",bitmap);
				getActivity().setResult(Activity.RESULT_OK, intent);
			}else{
				getActivity().setResult(Activity.RESULT_CANCELED, intent);
			}
			getActivity().finish();
		}
	}


	

	
}