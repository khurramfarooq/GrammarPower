/**
 * 
 */
package com.android.grammarpower.activity;

/**
 * @author khurram Farooq
 *
 */

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.grammarpower.service.ImageHelper;

public class Grammar_Power_SelectActivity extends SherlockFragmentActivity {
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_grammar_power_option);
		ImageView imageView = (ImageView)this.findViewById(R.id.grammar_image);
		Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
		imageView.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap, 20));
		
		
	}

}
