package ata.it.solutions.nooks;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class RoundedImage extends Drawable{

	private final Bitmap mBitmap;
	private final Paint mPaint;
	private final RectF mRectF;
	private final int mBitmapWidth;
	private final int mBitmapHeight;
	
	
	public RoundedImage(Bitmap bitmap) {
		mBitmap = bitmap;
		mRectF = new RectF();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
		mPaint.setShader(shader);

		// NOTE: we assume bitmap is properly scaled to current density
		mBitmapWidth = mBitmap.getWidth();
		mBitmapHeight = mBitmap.getHeight();
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawOval(mRectF, mPaint);
	}
	
	@Override
	protected void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);

		mRectF.set(bounds);
	}


	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		
		return PixelFormat.TRANSLUCENT;
		
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		if (mPaint.getAlpha() != alpha) {
			mPaint.setAlpha(alpha);
			invalidateSelf();
		}
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub
		mPaint.setColorFilter(cf);
	}
	
	
	@Override
	public int getIntrinsicWidth() {
		return mBitmapWidth;
	}

	@Override
	public int getIntrinsicHeight() {
		return mBitmapHeight;
	}

	public void setAntiAlias(boolean aa) {
		mPaint.setAntiAlias(aa);
		invalidateSelf();
	}

	@Override
	public void setFilterBitmap(boolean filter) {
		mPaint.setFilterBitmap(filter);
		invalidateSelf();
	}

	@Override
	public void setDither(boolean dither) {
		mPaint.setDither(dither);
		invalidateSelf();
	}

	public Bitmap getBitmap() {
		return mBitmap;
	}
}
