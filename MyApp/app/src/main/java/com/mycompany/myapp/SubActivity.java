package com.mycompany.myapp;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.text.InputType;


public class SubActivity extends Activity
{

	private GrobalDatas gdatas;
	private String pnum_str;
	
	private TextView textView;
	
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
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_01_input_name);
	
		//get Grobal datas class//////
		gdatas = (GrobalDatas)this.getApplication();
		////////////
		
		textView = findViewById(R.id.pnum);
		
		pnum_str = "Player number = " + String.valueOf(gdatas.getP_num());
		
		textView.setText(pnum_str);
		
		//dynamic view//////
		root_layout = findViewById(R.id.root_view);
		view = new View[gdatas.getP_num()];
		child_view_EditText = new EditText[gdatas.getP_num()];
		cancelButton = new ImageButton[gdatas.getP_num()];
		
		for(int i=0; i<gdatas.getP_num(); i++){
			view[i] = getLayoutInflater().inflate(R.layout.child_view_01,null);
			root_layout.addView(view[i]);
			
			child_view_TexitView = view[i].findViewById(R.id.child_view_01_text);
			child_view_TexitView.setText("player"+String.valueOf(i+1));
			
			child_view_EditText[i] = view[i].findViewById(R.id.child_view_01_edittext);
			child_view_EditText[i].setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
			child_view_EditText[i].setText(gdatas.playerDatas[i].name);
			
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
		
		//returnbutton///////
		Button returnButton = findViewById(R.id.sub_01_inputname_return_Button1);

		returnButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					//set name
					for(int i=0; i<gdatas.getP_num(); i++){
						EditText editText = view[i].findViewById(R.id.child_view_01_edittext);
						gdatas.playerDatas[i].name = String.valueOf(editText.getText());
					}
					///////////
					
					//senni
					Intent intent = new Intent(getApplication(), MainActivity.class);
					startActivity(intent);
				}
			}
		);
	    ////////////
		
		//ok button//////////
		Button okButton = findViewById(R.id.sub_01_inputname_ok_Button2);
		
	    okButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					//set name
					for(int i=0; i<gdatas.getP_num(); i++){
						EditText editText = view[i].findViewById(R.id.child_view_01_edittext);
						gdatas.playerDatas[i].name = String.valueOf(editText.getText());
					}
					///////////

					//senni
					Intent intent = new Intent(getApplication(), Sub_02_JobNameActivity.class);
					startActivity(intent);
				}
			}
		);
		/////////////////////
	}
}
