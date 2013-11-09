/**
 * 
 */
package com.android.grammarpower.activity;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

/**
 * @author khurram Farooq
 *
 */
public class Grammar_Power_QuizActivity extends SherlockFragmentActivity  {
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_grammar_power_quiz);
	}
	
	public void ToggleRadioButton(View view)
	{
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	 // First Menu Button
    	menu.add("MENÜ")
        .setOnMenuItemClickListener(this.MenuButtonHandler)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        
        
        return super.onCreateOptionsMenu(menu);
    }
	
	OnMenuItemClickListener MenuButtonHandler = new OnMenuItemClickListener() {

		public boolean onMenuItemClick(MenuItem item) 
		{
			finish();
			// Do something else
			return false;
		}
	};
}
