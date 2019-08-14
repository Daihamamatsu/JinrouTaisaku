package com.mycompany.myapp;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.view.View.*;
import android.content.*;

public class Sub_06_01_Setting_PlayerNum extends Activity
{
	private TextView topTextview;
	private GrobalDatas gdatas;
	private NumberPicker npicker;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_05_setting_layout);
		
		//get Grobal datas class//////
		gdatas = (GrobalDatas)this.getApplication();
		
		//top texView
		topTextview = findViewById(R.id.sub_05_top_text);
		topTextview.setText(R.string.sub_06_01_title);
		
		//numberpicker////
		npicker = findViewById(R.id.sub_05_numberPicker);

		npicker.setMaxValue(gdatas.maxplayer);
		npicker.setMinValue(1);
		npicker.setValue(gdatas.getP_num());
		npicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		
		//button
		Button ok_button = findViewById(R.id.sub_05_okbutton);
		Button return_button = findViewById(R.id.sub_05_returnbutton);
		Button cancel_button = findViewById(R.id.sub_05_cancelbutton);
		
		ok_button.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					int current_pnum = gdatas.getP_num();
					
					//set data on Grobal datas class///////
					gdatas.setP_num(npicker.getValue());
					///////////

					if(current_pnum != gdatas.getP_num()){
						gdatas.restartInitialization();
						gdatas.initializeRelations();
						gdatas.initializeJjudgement();
						gdatas.initializeLife();
					}
						
					//seni
					Intent intent = new Intent(getApplication(), Sub_06_02_Setting_PlayerName.class);
					startActivity(intent);
				}
			});
			
		return_button.setVisibility(View.INVISIBLE);
		
		cancel_button.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					gdatas.openText();
					
					//seni
					Intent intent = new Intent(getApplication(), Sub_04_PlayerCorrelationActivity.class);
					startActivity(intent);
				}
			});

		/////////////////
	}
}
