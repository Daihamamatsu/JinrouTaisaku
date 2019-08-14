package com.mycompany.myapp;
import android.app.*;
import android.widget.*;
import android.view.*;
import android.text.*;
import android.view.View.*;
import android.content.*;
import android.os.*;

public class Sub_02_JobNameActivity extends Activity
{
	private GrobalDatas gdatas;
	private String jnum_str;

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

		jnum_str = "Job number = " + String.valueOf(gdatas.getJ_num());

		textView.setText(jnum_str);

		//dynamic view//////
		root_layout = findViewById(R.id.root_view);
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
					for(int i=0; i<gdatas.getJ_num(); i++){
						EditText editText = view[i].findViewById(R.id.child_view_01_edittext);
						gdatas.jobDatas[i].name = String.valueOf(editText.getText());
					}
					///////////

					//senni
					Intent intent = new Intent(getApplication(), SubActivity.class);
					startActivity(intent);
				}
			}
		);
	    ////////////
	}
	
}
