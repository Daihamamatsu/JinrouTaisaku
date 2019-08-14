package com.mycompany.myapp;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.graphics.*;
import android.widget.LinearLayout.*;
import java.util.*;
import android.view.*;
import android.widget.AdapterView.*;
import android.content.*;

public class Sub_05_OnceaDayEventsActivity  extends Activity
{
	private GrobalDatas gdatas;
	private final int TEXTSIZE = 30;
	//private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_03_output_events_doublescrol);
		
		//get Grobal datas class//////
		gdatas = (GrobalDatas)this.getApplication();
		TextView dayText = findViewById(R.id.sub_03_top_text);
		gdatas.setDayText(dayText);
		////////////
		
		//table layout/////
		TableLayout rootTable = findViewById(R.id.sub_03_root_view);
		List<TableRow> tr_list = new ArrayList<>();
		
		//make row/////
		for(int i=0; i<3; i++){
			TableRow tr = new TableRow(this);
			tr_list.add(tr);
			
			TextView tv = new TextView(this, null, 0, R.style.AppTheme);
			tv.setTextSize(TEXTSIZE);
			
			tv.setBackgroundColor(Color.WHITE);
			switch(i){
				case 0 :
					tv.setText(R.string.day);
					break;
				case 1 :
					tv.setText(R.string.bited);
					break;
				case 2 :
					tv.setText(R.string.hanged);
					break;
			}
			
			tr_list.get(i).addView(tv);
			
			//set layout params after addView()
			ViewGroup.LayoutParams lp = tv.getLayoutParams();
			ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
			mlp.setMargins(1,1,1,1);
			tv.setLayoutParams(mlp);
		}
		
		for(int i=0; i<tr_list.size(); i++){
			TableRow tr = tr_list.get(i);
			rootTable.addView(tr);
		}
		////////
		
		//gdatas.dayDatas[0].bited_player = 2;
		//make column///
		for(int j=0; j<=gdatas.getDay(); j++){
			if(j==0 && !gdatas.getUse_Zero_Day())
				continue;
				
			//Row 1///
			TextView tv = new TextView(this);
			tv.setTextSize(TEXTSIZE);
			tv.setBackgroundColor(Color.WHITE);
			tv.setGravity(Gravity.CENTER);
			tv.setText(String.valueOf(j));
			tr_list.get(0).addView(tv);
			
			ViewGroup.LayoutParams lp = tv.getLayoutParams();
			ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
			mlp.setMargins(1,1,1,1);
			tv.setLayoutParams(mlp);
			
			//////////

			//Row 2 and 3 /////////////////////////
			for(int i=1; i<3; i++){				
				//spinner/////
				//Spinner spnr = findViewById(R.id.sub_03_spinner);
				Spinner spnr = new Spinner(this);
				spnr.setBackgroundColor(Color.WHITE);
				
				String spinnerItems[] = new String[gdatas.getP_num()+2];//add 2items, no selected(hyded) and no one killed
				for(int s=0; s<gdatas.getP_num(); s++){
					spinnerItems[s] = gdatas.playerDatas[s].name;
				}
				spinnerItems[gdatas.getP_num()] = (String)getText(R.string.noselected);
				spinnerItems[gdatas.getP_num()+1] = (String)getText(R.string.noone);
				
				//ArrayAdapter
				ArrayAdapter<String> adptr = new ArrayAdapter<String>(this, R.layout.spinner_item , spinnerItems){
					//hihyouji no selected item//
					@Override
					public View getDropDownView(int position, View convertView, ViewGroup parent){
						View v = null;
						
						if(position == gdatas.getP_num()){
							TextView textView = new TextView(getContext());
							textView.setHeight(0);
							textView.setVisibility(View.GONE);
							v = textView;
						}
						else{
							v = super.getDropDownView(position, null, parent);
						}
						parent.setVerticalScrollBarEnabled(false);
						return v;
					}
				};
				adptr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				//set adapter
				spnr.setAdapter(adptr);
				//spnr.setSelection(4);
				/////////////
				
				//spinner first/////////
				switch(i){
					case 1:
						if(gdatas.dayDatas[j].bited_player == gdatas.NO_SELECTED)
							spnr.setSelection(gdatas.getP_num());
						else if(gdatas.dayDatas[j].bited_player == gdatas.NOT_KILLED)
							spnr.setSelection(gdatas.getP_num()+1);
						else
							spnr.setSelection(gdatas.dayDatas[j].bited_player);
						break;
					case 2:
						if(gdatas.dayDatas[j].hanged_player == gdatas.NO_SELECTED)
							spnr.setSelection(gdatas.getP_num());
						else if(gdatas.dayDatas[j].hanged_player == gdatas.NOT_KILLED)
							spnr.setSelection(gdatas.getP_num()+1);
						else
							spnr.setSelection(gdatas.dayDatas[j].hanged_player);
						break;						
				}
				//////////////
				
				final int final_i = i;
				final int final_j = j;
				spnr.setOnItemSelectedListener(new OnItemSelectedListener(){
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
						Spinner spinner = (Spinner)parent;
						int killed_player = spinner.getSelectedItemPosition();
						if(killed_player == gdatas.getP_num())
							killed_player = gdatas.NO_SELECTED;
						else if (killed_player == gdatas.getP_num()+1)
							killed_player = gdatas.NOT_KILLED;
						
						switch(final_i){
							case 1:
								int current_bited_player = gdatas.dayDatas[final_j].bited_player;
								gdatas.dayDatas[final_j].bited_player = killed_player;
									
								if(current_bited_player != gdatas.dayDatas[final_j].bited_player){
									if(gdatas.dayDatas[final_j].bited_player != gdatas.NOT_KILLED)
										gdatas.addEventdatas(gdatas.playerDatas[gdatas.dayDatas[final_j].bited_player].name
															 + " " 
															 + (String)getText( R.string.log_bited_01));
									reload();
								}
									break;
							case 2:
								int current_hanged_player = gdatas.dayDatas[final_j].hanged_player;
								gdatas.dayDatas[final_j].hanged_player = killed_player;
								if(current_hanged_player != gdatas.dayDatas[final_j].hanged_player){
									if(gdatas.dayDatas[final_j].hanged_player != gdatas.NOT_KILLED)
										gdatas.addEventdatas(gdatas.playerDatas[gdatas.dayDatas[final_j].hanged_player].name
															 + " " 
															 + (String)getText( R.string.log_hanged_01));
									reload();
								}
								break;
						}
					}
					
					public void onNothingSelected(AdapterView<?> parent){
						
					}
				}
				);				
				tr_list.get(i).addView(spnr);
				
				ViewGroup.LayoutParams spnr_lp = spnr.getLayoutParams();
				ViewGroup.MarginLayoutParams spnr_mlp = (ViewGroup.MarginLayoutParams)spnr_lp;
				spnr_mlp.setMargins(1,1,1,1);
				spnr.setLayoutParams(spnr_mlp);
				
			}
			///////////////////////////////
		}
		////////////////
		///////////////////
		
/*		TextView tv = findViewById(R.id.sub_03_table_2_1);
		tv.setBackgroundColor(Color.WHITE);
		LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
		MarginLayoutParams mlp = (MarginLayoutParams)lp;
		mlp.setMargins(1,1,1,1);
		tv.setLayoutParams(mlp);
		tv.setTextSize(30);
		
		TableRow tr = findViewById(R.id.sub_03_tablerow_1);
		Button button = new Button(this);
		button.setText("ababa");
		tr.addView(button);*/
		
		//left buttton////////
		Button leftButton = findViewById(R.id.sub_03_left_button);

		leftButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Intent intent = new Intent(getApplication(), Sub_04_PlayerCorrelationActivity.class);
					startActivity(intent);
				}
			});

		//////////////////////
		//right buttton////////
		Button rightButton = findViewById(R.id.sub_03_right_button);

		rightButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Intent intent = new Intent(getApplication(), Sub_03_OutputEventsActivity.class);
					startActivity(intent);
				}
			});

		//////////////////////
		
	}
	
	//Activity Reload//////
	private void reload(){
		gdatas.calcToday();
		gdatas.calcLife();
		
		Intent intent = getIntent();
		overridePendingTransition(0,0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();

		overridePendingTransition(0,0);
		startActivity(intent);
	}

	///////////////////////
	
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
