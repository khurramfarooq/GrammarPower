package com.android.grammarpower.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class Grammar_Power_MainActivity extends SherlockFragmentActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grammar__power__main);
		Button btnStart = (Button)this.findViewById(R.id.grammarAcitivty_strtBtn);
		btnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Grammar_Power_MainActivity.this, Grammar_Power_SelectActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.grammar__power__main, menu);
//		return true;
//	}

}
