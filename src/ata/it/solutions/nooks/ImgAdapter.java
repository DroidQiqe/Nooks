package ata.it.solutions.nooks;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImgAdapter extends BaseAdapter{
	int mGalleryItemBackground;
	private Context mContext;
	
	private Integer  ImagesIds[]={
			R.drawable.orgd,
			R.drawable.orggdd,
			R.drawable.orggdt,
			R.drawable.jj,
			R.drawable.jjj,
			R.drawable.jjjj
			
			
			
			
	};
	
	
	public ImgAdapter(Context c) {
        mContext = c;
        TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
        mGalleryItemBackground = attr.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 0);
        attr.recycle();
    }
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return ImagesIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(mContext);

        imageView.setImageResource(ImagesIds[position]);
        imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(mGalleryItemBackground);

        return imageView;
	}

}
