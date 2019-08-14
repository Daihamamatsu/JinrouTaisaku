package com.mycompany.myapp;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.view.View.*;
import android.content.*;
import android.text.*;

public class Sub_06_04_Setting_JobName extends Activity
{
	private TextView topTextview;
	private GrobalDatas gdatas;
	private NumberPicker npicker;

	//dynamic view////////
	private LinearLayout root_layout;
	private TextView child_view_TexitView;
	private EditText[] child_view_EditText;
	private View[] view;
	private ImageButton[] cancelButton;
	//////////////

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_05_setting_layout);

		//get Grobal datas class//////
		gdatas = (GrobalDatas)this.getApplication();

		//top texView
		topTextview = findViewById(R.id.sub_05_top_text);
		topTextview.setText(R.string.sub_06_04_title);

		//numberpicker////
		npicker = findViewById(R.id.sub_05_numberPicker);
		//npicker.setVisibility(View.INVISIBLE);
		ViewGroup vg = (ViewGroup) npicker.getParent();
		vg.removeView(npicker);
		
		//dynamic view//////
		root_layout = findViewById(R.id.sub_05_root_view);
		view = new View[gdatas.getJ_num()];
		child_view_EditText = new EditText[gdatas.getJ_num()];
		cancelButton = new ImageButton[gdatas.getJ_num()];

		for(int i=0; i<gdatas.getJ_num(); i++){
			view[i] = getLayoutInflater().inflate(R.layout.child_view_01,null);
			root_layout.addView(view[i]);

			child_view_TexitView = view[i].findViewById(R.id.child_view_01_text);
			child_view_TexitView.setText("job"+String.valueOf(i+1));

			child_view_EditText[i] = view[i].findViewById(R.id.child_view_01_edittext);
			child_view_EditText[i].setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
			child_view_EditText[i].setText(gdatas.jobDatas[i].name);

			cancelButton[i] = view[i].findViewById(R.id.child_view_01_cancelbutton);
			final EditText cancelText = child_view_EditText[i];
			cancelButton[i].setOnClickListener(new OnClickListener(){
					public void onClick(View v){
						//EditText cancelText = view[i].findViewById(R.id.child_view_01_edittext);
						cancelText.setText("");
					}
				});

		}
		////////////

		//button
		Button ok_button = findViewById(R.id.sub_05_okbutton);
		Button return_button = findViewById(R.id.sub_05_returnbutton);
		Button cancel_button = findViewById(R.id.sub_05_cancelbutton);

		ok_button.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					//set name
					for(int i=0; i<gdatas.getJ_num(); i++){
						EditText editText = view[i].findViewById(R.id.child_view_01_edittext);
						gdatas.jobDatas[i].name = String.valueOf(editText.getText());
					}
					///////////
					//SerializeDatas serDatas = new SerializeDatas();
					//serDatas.saveData(gdatas);
					
					gdatas.restartInitialization();
					gdatas.initializeRelations();
					gdatas.initializeJjudgement();
					gdatas.initializeLife();

					//seni
					Intent intent = new Intent(getApplication(), Sub_04_PlayerCorrelationActivity.class);
					//Intent intent = new Intent(getApplication(), MainActivity.class);
					startActivity(intent);
				}
			});

		return_button.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					//seni
					Intent intent = new Intent(getApplication(), Sub_06_03_Setting_JobNum.class);
					startActivity(intent);
				}
			});

		cancel_button.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					gdatas.openText();

					//seni
					Intent intent = new Intent(getApplication(), Sub_04_PlayerCorrelationActivity.class);
					startActivity(intent);
				}
			});


	}
}
