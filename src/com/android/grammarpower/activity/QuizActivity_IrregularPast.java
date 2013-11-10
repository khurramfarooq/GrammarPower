/**
 * 
 */
package com.android.grammarpower.activity;
import java.util.List;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.android.grammarpower.service.QuizData;
import com.android.grammarpower.service.TextParser;

/**
 * @author khurram Farooq
 *
 */
public class QuizActivity_IrregularPast extends SherlockFragmentActivity  {
	
	RadioButton option1 = null;//(RadioButton)findViewById(R.quizActivity.option1);
	RadioButton option2 = null;//(RadioButton)findViewById(R.quizActivity.option2);
	RadioButton option3 = null;//(RadioButton)findViewById(R.quizActivity.option3);
	String timeText = "";
	TextView questionText = null;
	TextView questionNumberText = null;
	List<String> fileText = null;
	QuizData quizData = null;
	Random random = new Random();
	TextView hintText = null;
	//ProgressDialog progressDialog = null;
	ImageView optionResultImage = null;
	int questionNumber = 1;
	boolean isFirstGame = true;
	int randomIndex = 0;
	int intialIndex = 0;
	int checkIndex = 0;
	
	int countHours = 0;
	
	private long startTime = 0L;
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	
	boolean isIncrementRandomIndex = false;

	private Handler customHandler = new Handler();
	
	TextView yellowQuestionText = null;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_grammar_power_quiz);
		option1 = (RadioButton)findViewById(R.id.quizActivity_option1);
		option2 = (RadioButton)findViewById(R.id.quizActivity_option2);
		option3 = (RadioButton)findViewById(R.id.quizActivity_option3);
		questionText = (TextView)findViewById(R.id.quizActivity_questionTextInWhite);
		optionResultImage = (ImageView)findViewById(R.id.quizActivity_optionResultImage);
		hintText = (TextView)this.findViewById(R.id.quizActivity_hintText);
		TextView yellowQuestionText = null;
		yellowQuestionText = (TextView)findViewById(R.id.quizActivity_questionTextInYellow);
		yellowQuestionText.setText(R.string.past_question);
		Button stopBtn = (Button)findViewById(R.id.quizActivity_stopButton);
		
		hintText.setText(R.string.hint_break);
		final Runnable updateTimerThread = new Runnable() {

			public void run() {

				timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
				updatedTime = timeSwapBuff + timeInMilliseconds;
				int secs = (int) (updatedTime / 1000);
				int mins = secs / 60;
				secs = secs % 60;
				//int milliseconds = (int) (updatedTime % 1000);
				if(mins == 60){
					++countHours;
					timeText = ""
							+ String.format("%02d", countHours) + ":"
							+ String.format("%02d", mins) + ":"
							+ String.format("%02d", secs);
				}
				timeText = "" + String.format("%02d", mins) + ":"
						+ String.format("%02d", secs);
				customHandler.postDelayed(this, 0);
				
			}

		};
		startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);   
		stopBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub	
				String time = timeText;
				//time.replace("Time", "");
				String []timeArr = time.split(":");
				String hours = "0";
				String minutes = "00";
				String seconds = "00";
				if(timeArr.length > 3)
				{
					hours = timeArr[0].trim();
					minutes = timeArr[1].trim();
					seconds = timeArr[2].trim();
					
					// this is the text we'll be operating on  
				    SpannableString text = new SpannableString("You did within " + hours + " hours, "+ minutes +" minutes, "+ seconds +" seconds: "+ (questionNumber - 2));  
				  
				    // make "Lorem" (characters 0 to 5) red  
				    int index = text.toString().indexOf(": ");
				    index = index + 2;
				    text.setSpan(new ForegroundColorSpan(Color.RED), index, text.toString().length() - 1, 0);
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity_IrregularPast.this);
					// set dialog message
					alertDialogBuilder
							.setMessage(text)
							.setCancelable(false)
							.setPositiveButton("continue",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int id) {
											// if this button is clicked, close
											// current activity
											finish();
										}
									});
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
					// show it
					alertDialog.show();
				}
				else
				{
					minutes = timeArr[0].trim();
					seconds = timeArr[1].trim();
					SpannableString text = new SpannableString("You did within "+ minutes +" minutes, "+ seconds +" seconds: "+ (questionNumber - 2));
					int index = text.toString().indexOf(": ");
				    index = index + 2;
				    text.setSpan(new ForegroundColorSpan(Color.RED), index, text.toString().length(), 0);
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity_IrregularPast.this);
					// set dialog message
					alertDialogBuilder
							.setMessage(text)
							.setCancelable(false)
							.setPositiveButton("continue",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int id) {
											// if this button is clicked, close
											// current activity
											finish();
										}
									});
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
					// show it
					alertDialog.show();
				}
				
				customHandler.removeCallbacks(updateTimerThread);
			}
		});		
	  
		loadFileText();
		setFirstQuizQuestion();		
	}
	

	
	public void loadFileText() 
	{	
		fileText = TextParser.readFile(getAssets(),"DLT1_fin.txt");		
	}
	
	public void refreshFileTextArray()
	{
		try
		{			
			loadFileText();			
			setFirstQuizQuestion();	
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public void setFirstQuizQuestion()
	{
		if(fileText.size() > 0)
		{
			checkIndex = fileText.size() - 1;
			if(fileText.size() == 0)
			{
				randomIndex = 0;
			}
			else
			{
				randomIndex = random.nextInt(fileText.size() - 1) - 0 + 0;
			}
			intialIndex = randomIndex;
			quizData = TextParser.splitData(fileText.get(randomIndex));
			//questionNumberText.setText("Q." + Integer.toString(questionNumber++));
			questionText.setText(quizData.name);
			option1.setText(quizData.option1);
			option2.setText(quizData.option2);
			option3.setText(quizData.option3);
			fileText.remove(randomIndex);	
			isIncrementRandomIndex = false;
			questionNumber++;
		}		
	}
	
	public void setNextQuizQuestion() 
	{
		if(fileText.size() > 0)
		{			
			if(fileText.size() == 1)
			{
				randomIndex = 0;
				quizData = TextParser.splitData(fileText.get(randomIndex));
				fileText.remove(randomIndex);
				//questionNumberText.setText("Q." + Integer.toString(questionNumber++));
				questionText.setText(quizData.name);
				option1.setText(quizData.option1);
				option2.setText(quizData.option2);
				option3.setText(quizData.option3);
				questionNumber++;
				return;
			}
			
			else if(checkIndex == intialIndex)
			{
				randomIndex = 0;		
				isIncrementRandomIndex = true;
			}
			else
			{
				intialIndex++;
			}
			quizData = TextParser.splitData(fileText.get(randomIndex));			
			fileText.remove(randomIndex);
			//questionNumberText.setText("Q." + Integer.toString(questionNumber++));
			questionText.setText(quizData.name);
			option1.setText(quizData.option1);
			option2.setText(quizData.option2);
			option3.setText(quizData.option3);		
			
			if(isIncrementRandomIndex)
			{
				randomIndex += 1;
			}
			questionNumber++;
		}
		else
		{
			refreshFileTextArray();
		}
	}
	
	public void ToggleRadioButton(View view) 
	{	
		boolean isNextQuestion = false;
		RadioButton radioButton = null;		
		String selectedOption = "";		
		switch (view.getId()) {
			case R.id.quizActivity_option1:
				checkRadioButton(option1);
				uncheckRadioButton(option2);
				uncheckRadioButton(option3);
			radioButton = (RadioButton) findViewById(view.getId());
			selectedOption = radioButton.getText().toString();
			if (selectedOption.equals(quizData.correctOption)) {
				optionResultImage.setImageResource(R.drawable.option_checkmark);
				//showDialogBox();
				disableOption();
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() 
				{	
					@Override
					public void run() 
					{
						clearOption();
						setNextQuizQuestion();
					}
				}, 1000);
				
					
			}			
			break;
			case R.id.quizActivity_option2:
				checkRadioButton(option2);
				uncheckRadioButton(option1);
				uncheckRadioButton(option3);
			radioButton = (RadioButton) findViewById(view.getId());
			selectedOption = radioButton.getText().toString();
			if (selectedOption.equals(quizData.correctOption)) {
				optionResultImage.setImageResource(R.drawable.option_checkmark);
				//showDialogBox();
				disableOption();
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						clearOption();
						setNextQuizQuestion();
					}
				}, 1000);
				
			}			
			break;
			case R.id.quizActivity_option3:
				checkRadioButton(option3);
				uncheckRadioButton(option2);
				uncheckRadioButton(option1);
			radioButton = (RadioButton) findViewById(view.getId());
			selectedOption = radioButton.getText().toString();
			if (selectedOption.equals(quizData.correctOption)) {
				optionResultImage.setImageResource(R.drawable.option_checkmark);
				//showDialogBox();
				disableOption();
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						clearOption();
						setNextQuizQuestion();
					}
				}, 1000);
				
			}		
			break;
		}		

		if(isNextQuestion){
			clearOption();
		}		
	
	}
	
	public void clearOption() {
		uncheckRadioButton(option1);
		uncheckRadioButton(option2);
		uncheckRadioButton(option3);
		option1.setEnabled(true);
		option2.setEnabled(true);
		option3.setEnabled(true);
		optionResultImage.setImageResource(0);
		//progressDialog.dismiss();			
	}
	
	public void disableOption(){
		
		option1.setEnabled(false);
		option2.setEnabled(false);
		option3.setEnabled(false);
	}
	
	public void uncheckRadioButton(RadioButton btn)
	{
		btn.setChecked(false);
		btn.setTextColor(Color.BLACK);
		btn.setTypeface(null, Typeface.NORMAL);
	}
	public void checkRadioButton(RadioButton btn)
	{
		btn.setTextColor(Color.BLUE);
		btn.setTypeface(null, Typeface.BOLD);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	 // First Menu Button
    	menu.add("MENU")
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
