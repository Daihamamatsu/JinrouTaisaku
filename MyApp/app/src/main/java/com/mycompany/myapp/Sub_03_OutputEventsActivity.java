package com.mycompany.myapp;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.text.*;
import android.view.View.*;
import android.graphics.*;
import android.content.*;
import java.util.*;

public class Sub_03_OutputEventsActivity extends Activity
{
	private GrobalDatas gdatas;
	
	//dynamic view////////
	private LinearLayout root_layout;
	private TextView child_view_TexitView_01;
	private TextView child_view_TexitView_02;
	//////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_03_output_events);

		//get Grobal datas class//////
		gdatas = (GrobalDatas)this.getApplication();
		TextView dayText = findViewById(R.id.sub_03_top_text);
		gdatas.setDayText(dayText);
		
		////////////

		//dynamic view//////
		root_layout = findViewById(R.id.sub_03_root_view);
		
		/*for(int i=0; i<gdatas.getP_num(); i++){
			View view;
			view = getLayoutInflater().inflate(R.layout.child_view_02_eventdatas,null);
			root_layout.addView(view);
			
			if(i%2 == 1)
				view.setBackgroundColor(Color.GRAY);
			else
				view.setBackgroundColor(Color.WHITE);
			
			child_view_TexitView_01 = view.findViewById(R.id.child_view_02_text_01);
			child_view_TexitView_01.setText(gdatas.getNowDate());
			
			child_view_TexitView_02 = view.findViewById(R.id.child_view_02_text_02);
			String s = "";
			for(int j=0; j<i; j++){
				s = s + String.valueOf(j);
			}
			child_view_TexitView_02.setText(s);
		}*/
		for(int i=0; i<gdatas.e_datas.size(); i++){
			View view;
			view = getLayoutInflater().inflate(R.layout.child_view_02_eventdatas,null);
			root_layout.addView(view);
			
			if(i%2 == 1)
				view.setBackgroundColor(Color.GRAY);
			else
				view.setBackgroundColor(Color.WHITE);
			
			child_view_TexitView_01 = view.findViewById(R.id.child_view_02_text_01);
			child_view_TexitView_01.setText(gdatas.e_datas.get(i).eventDate);

			child_view_TexitView_02 = view.findViewById(R.id.child_view_02_text_02);
			child_view_TexitView_02.setText(gdatas.e_datas.get(i).eventLog);
			
		}
		////////////
		
		//left buttton////////
		Button leftButton = findViewById(R.id.sub_03_left_button);

		leftButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Intent intent = new Intent(getApplication(), Sub_05_OnceaDayEventsActivity.class);
					startActivity(intent);
				}
			});

		//////////////////////
		//right buttton////////
		Button rightButton = findViewById(R.id.sub_03_right_button);

		rightButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Intent intent = new Intent(getApplication(), Sub_04_PlayerCorrelationActivity.class);
					startActivity(intent);
				}
			});

		//////////////////////

		
	}
	
	//action bar ////
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_02_topmenu,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent;

		// TODO: Implement this method
		switch(item.getItemId()){
			case R.id.menu_02_item01:
				gdatas.restartInitialization();
				gdatas.initializeRelations();
				gdatas.initializeJjudgement();
				gdatas.initializeLife();
				intent = new Intent(getApplication(), Sub_04_PlayerCorrelationActivity.class);
				startActivity(intent);
				break;
			case R.id.menu_02_item02:
				intent = new Intent(getApplication(), Sub_06_01_Setting_PlayerNum.class);
				startActivity(intent);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	/////////////////
}
