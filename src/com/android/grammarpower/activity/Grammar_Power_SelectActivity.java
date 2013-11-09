/**
 * 
 */
package com.android.grammarpower.activity;

/**
 * @author khurram Farooq
 *
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.grammarpower.service.ImageHelper;

public class Grammar_Power_SelectActivity extends SherlockFragmentActivity {
	
	
	Button verbsBtn = null;
	Button participleBtn = null;
	Button nounPlural = null;
	Button perpositionBtn = null;
	Button phrasalBtn = null;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_grammar_power_option);
		ImageView imageView = (ImageView)this.findViewById(R.id.grammar_image);
		Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
		imageView.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap, 20));
		
		verbsBtn = (Button)this.findViewById(R.id.grammar_irregularVerbs);
		
		
		
		
		verbsBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Grammar_Power_SelectActivity.this, Grammar_Power_QuizActivity.class);
				startActivity(intent);
				
			}
		});
		
		
	}

}
