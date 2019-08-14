package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.View;
import android.content.*;
import android.view.*;

public class MainActivity extends Activity 
{
	NumberPicker numPicker01;
	private TextView pickerTextView;
	
	private String playernum = "";
	
	private GrobalDatas gdatas;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//get Grobal datas class//////
		gdatas = (GrobalDatas)this.getApplication();
		if(!gdatas.getBoo_init()){
			gdatas.initialization();
			}
		////////////
		
		numPicker01 = findViewById(R.id.numPicker01);
		pickerTextView = findViewById(R.id.textView01);

//
		SerializeDatas seriDatas = new SerializeDatas();
		String testString = seriDatas.getDatas(gdatas);
		pickerTextView.setText(testString);
//
		
		numPicker01.setMaxValue(gdatas.maxplayer);
		numPicker01.setMinValue(1);
		numPicker01.setValue(gdatas.getP_num());
		numPicker01.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		
		Button pickerButton = findViewById(R.id.button_ok01);
		
		pickerButton.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//set data on Grobal datas class///////
				gdatas.setP_num(numPicker01.getValue());
				///////////
				
				playernum = String.valueOf(numPicker01.getValue());
				
				pickerTextView.setText(playernum);
				
				gdatas.initializeRelations();
				gdatas.initializeJjudgement();
				
				gdatas.saveText();
				gdatas.openText();
				
				//seni test
				//Intent intent = new Intent(getApplication(), SubActivity.class);
				//Intent intent = new Intent(getApplication(), Sub_03_OutputEventsActivity.class);
				Intent intent = new Intent(getApplication(), Sub_04_PlayerCorrelationActivity.class);
				startActivity(intent);
			}
		});
		
    }
}
