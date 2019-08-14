package com.mycompany.myapp;
import android.app.*;
import android.widget.*;
import android.os.*;
import android.view.*;
import android.graphics.*;
import android.view.View.*;
import java.util.zip.*;
import android.content.*;

public class Sub_04_PlayerCorrelationActivity extends Activity
{
	private GrobalDatas gdatas;

	//dynamic view////////
	private FrameLayout root_layout;
	private TextView[] child_view_TexitView_01;
	//private TextView[] child_view_TexitView_02;
	//private View[] view;
	//////////////

	//byouga you//////////////
	//public float a;
	//public float b;
	//////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_03_output_events_noscrol);

		//get Grobal datas class//////
		gdatas = (GrobalDatas)this.getApplication();
		TextView dayText = findViewById(R.id.sub_03_top_text);
		gdatas.setDayText(dayText);
		////////////

		child_view_TexitView_01 = new TextView[gdatas.getP_num()];
		//child_view_TexitView_02 = new TextView[gdatas.getP_num()];
		//view = new View[gdatas.getP_num()];

		//dp tanni///
		float scale = getResources().getDisplayMetrics().density;

		//dynamic view//////
		root_layout = findViewById(R.id.sub_03_root_view);

		for(int i=0; i<gdatas.getP_num(); i++){
			final int final_i = i;
			/*
			 view[i] = getLayoutInflater().inflate(R.layout.child_view_03_playermenu,null);
			 root_layout.addView(view[i]);

			 if(i%2 == 1)
			 view[i].setBackgroundColor(Color.GRAY);
			 else
			 view[i].setBackgroundColor(Color.WHITE);
			 */
			//child_view_TexitView_01[i] = view[i].findViewById(R.id.child_view_03_text_01);
			child_view_TexitView_01[i] = new TextView(this);
			child_view_TexitView_01[i].setText(gdatas.playerDatas[i].name);
			if(gdatas.playerDatas[i].job != 0){
				child_view_TexitView_01[i].setBackgroundColor(Color.YELLOW);
			}else
				child_view_TexitView_01[i].setBackgroundColor(Color.WHITE);
			
			if(gdatas.playerDatas[i].life == gdatas.LIFE_DEAD)
				child_view_TexitView_01[i].setTextColor(gdatas.COLOR_DEAD);

			child_view_TexitView_01[i].setClickable(true);
			child_view_TexitView_01[i].setTextSize(20);
			/*
			 if(i%2 == 1)
			 child_view_TexitView_01[i].setBackgroundColor(Color.GRAY);
			 else
			 child_view_TexitView_01[i].setBackgroundColor(Color.WHITE);
			 */
			//LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams((int)(200 * scale), LinearLayout.LayoutParams.WRAP_CONTENT);
			FrameLayout.LayoutParams textViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
			child_view_TexitView_01[i].setLayoutParams(textViewParams);

			root_layout.addView(child_view_TexitView_01[i]);


			child_view_TexitView_01[i].setOnClickListener(new OnClickListener(){
					public void onClick(View v){
						///////
						//int[] locaion = new int[2];
						//child_view_TexitView_01[final_i].getLocationOnScreen(locaion);
						//child_view_TexitView_01[final_i].setText(String.valueOf(locaion[0]) + " " + String.valueOf(locaion[1]));
						///////////

						//popupmenu/////
						PopupMenu p_menu = new PopupMenu(Sub_04_PlayerCorrelationActivity.this, v);
						p_menu.getMenuInflater().inflate(R.menu.menu_01_popupmenu, p_menu.getMenu());
						if(gdatas.playerDatas[final_i].job == 0)
							p_menu.getMenu().getItem(3).setVisible(false);
							
						p_menu.getMenu().getItem(1).setVisible(false);
						
						///////set sub menu//
						for(int ii=0; ii<gdatas.getP_num(); ii++){
							if(final_i != ii){
								p_menu.getMenu().getItem(0).getSubMenu().getItem(0).getSubMenu().add(gdatas.GROUPID_RELATION_FRIENDLY,ii,ii,gdatas.playerDatas[ii].name);
								p_menu.getMenu().getItem(0).getSubMenu().getItem(1).getSubMenu().add(gdatas.GROUPID_RELATION_HATE,ii,ii,gdatas.playerDatas[ii].name);
								p_menu.getMenu().getItem(0).getSubMenu().getItem(2).getSubMenu().add(gdatas.GROUPID_RELATION_NONE,ii,ii,gdatas.playerDatas[ii].name);
								//p_menu.getMenu().getItem(1).getSubMenu().add(gdatas.GROUPID_RELATION_HATE,ii,ii,gdatas.playerDatas[ii].name);

								p_menu.getMenu().getItem(3).getSubMenu().getItem(0).getSubMenu().add(gdatas.GROUPID_JJUDGEMENT_WHITE,ii,ii,gdatas.playerDatas[ii].name);
								p_menu.getMenu().getItem(3).getSubMenu().getItem(1).getSubMenu().add(gdatas.GROUPID_JJUDGEMENT_BLACK,ii,ii,gdatas.playerDatas[ii].name);
								p_menu.getMenu().getItem(3).getSubMenu().getItem(2).getSubMenu().add(gdatas.GROUPID_JJUDGEMENT_NONE,ii,ii,gdatas.playerDatas[ii].name);
							}
							//p_menu.getMenu().getItem(2).getSubMenu().add(ii,ii,ii,gdatas.playerDatas[ii].name);
						}

						for(int ii=0; ii<gdatas.getJ_num(); ii++){
							p_menu.getMenu().getItem(2).getSubMenu().add(gdatas.GROUPID_JOB_CO,ii,ii,gdatas.jobDatas[ii].name);
							if(ii == (gdatas.getJ_num()-1)){
								p_menu.getMenu().getItem(2).getSubMenu().add(gdatas.GROUPID_ONLY_CO,ii+1,ii+1,R.string.onlyco);	
							}
						}

						//p_menu.getMenu().getItem(3).getSubMenu().add(gdatas.GROUPID_JJUDGEMENT_WHITE, gdatas.JJUDGE_WHITE, gdatas.JJUDGE_WHITE, R.string.jjdudge_white);
						//p_menu.getMenu().getItem(3).getSubMenu().add(gdatas.GROUPID_JJUDGEMENT_BLAKCK, gdatas.JJUDGE_BLACK, gdatas.JJUDGE_BLACK, R.string.jjdudge_black);						
						//p_menu.getMenu().getItem(3).getSubMenu().add(gdatas.GROUPID_JJUDGEMENT_NONE, gdatas.JJUDGE_NONE, gdatas.JJUDGE_NONE, R.string.jjdudge_none);
						/////////////////////
						p_menu.show();

						p_menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
								public boolean onMenuItemClick(MenuItem item){
											switch(item.getGroupId()){
												case gdatas.GROUPID_RELATION_FRIENDLY:
													gdatas.setPlayerRelations(final_i, item.getItemId(), gdatas.RELATION_FRIENDLY);
													gdatas.addEventdatas(gdatas.playerDatas[final_i].name + " " + (String)getText(R.string.log_friendly_01) + " " + gdatas.playerDatas[item.getItemId()].name + (String)getText(R.string.log_friendly_02));
													reload();
													break;
												case gdatas.GROUPID_RELATION_HATE:
													gdatas.setPlayerRelations(final_i, item.getItemId(), gdatas.RELATION_HATE);
													gdatas.addEventdatas(gdatas.playerDatas[final_i].name + " " + (String)getText(R.string.log_hate_01) + " " + gdatas.playerDatas[item.getItemId()].name + (String)getText(R.string.log_hate_02));
													reload();
													break;
												case gdatas.GROUPID_RELATION_NONE:
													gdatas.setPlayerRelations(final_i, item.getItemId(), gdatas.RELATION_NONE);
													gdatas.addEventdatas(gdatas.playerDatas[final_i].name + " " + (String)getText(R.string.log_none_01) + " " + gdatas.playerDatas[item.getItemId()].name + (String)getText(R.string.log_none_02));
													reload();
													break;
												case gdatas.GROUPID_JOB_CO:
													gdatas.playerDatas[final_i].job = item.getItemId();
													gdatas.addEventdatas(gdatas.playerDatas[final_i].name + " " + (String)getText(R.string.log_CO_01) + " " + gdatas.jobDatas[item.getItemId()].name + " " + (String)getText(R.string.log_CO_02));
													reload();
													break;
												case gdatas.GROUPID_ONLY_CO:
													gdatas.addEventdatas(gdatas.playerDatas[final_i].name + " " + (String)getText(R.string.log_CO_only));
													break;
												case gdatas.GROUPID_JJUDGEMENT_WHITE:
													gdatas.setJobJudgement(final_i, item.getItemId(), gdatas.JJUDGE_WHITE);
													gdatas.addEventdatas(gdatas.jobDatas[gdatas.playerDatas[final_i].job].name + " "
													+ gdatas.playerDatas[final_i].name + " "
													+ (String)getText(R.string.log_jjudge_white_01) + " "
																		 + gdatas.playerDatas[item.getItemId()].name + " "
																		 + (String)getText(R.string.log_jjudge_white_02));
													reload();
													break;
												case gdatas.GROUPID_JJUDGEMENT_BLACK:
													gdatas.setJobJudgement(final_i, item.getItemId(), gdatas.JJUDGE_BLACK);
													gdatas.addEventdatas(gdatas.jobDatas[gdatas.playerDatas[final_i].job].name + " "
																		 + gdatas.playerDatas[final_i].name + " "
																		 + (String)getText(R.string.log_jjudge_black_01) + " "
																		 + gdatas.playerDatas[item.getItemId()].name + " "
																		 + (String)getText(R.string.log_jjudge_black_02));
													reload();
													break;
												case gdatas.GROUPID_JJUDGEMENT_NONE:
													gdatas.setJobJudgement(final_i, item.getItemId(), gdatas.JJUDGE_NONE);
													gdatas.addEventdatas(gdatas.jobDatas[gdatas.playerDatas[final_i].job].name + " "
																		 + gdatas.playerDatas[final_i].name + " "
																		 + (String)getText(R.string.log_jjudge_none_01) + " "
																		 + gdatas.playerDatas[item.getItemId()].name + " "
																		 + (String)getText(R.string.log_jjudge_none_02));
													reload();
													break;
													
											}
									return true;
								}
							}
						);
						////////////////

					}
				}
			);


			/*
			 child_view_TexitView_02[i] = view[i].findViewById(R.id.child_view_03_text_02);
			 String s = "";
			 for(int j=0; j<i; j++){
			 s = s + String.valueOf(j);
			 }
			 child_view_TexitView_02[i].setText(s);

			 child_view_TexitView_02[i].setOnClickListener(new OnClickListener(){
			 public void onClick(View v){
			 ///////
			 int[] locaion = new int[2];
			 child_view_TexitView_02[final_i].getLocationOnScreen(locaion);
			 child_view_TexitView_02[final_i].setText(String.valueOf(locaion[0]) + " " + String.valueOf(locaion[1]));
			 ///////////
			 }
			 }
			 );*/
		}
		////////////

		//left buttton////////
		Button leftButton = findViewById(R.id.sub_03_left_button);

		leftButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Intent intent = new Intent(getApplication(), Sub_03_OutputEventsActivity.class);
					startActivity(intent);
				}
			});

		//////////////////////
		//right buttton////////
		Button rightButton = findViewById(R.id.sub_03_right_button);

		rightButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Intent intent = new Intent(getApplication(), Sub_05_OnceaDayEventsActivity.class);
					startActivity(intent);
				}
			});

		//////////////////////

	}

	//get location test////
	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		// TODO: Implement this method
		super.onWindowFocusChanged(hasFocus);

		//int[] locaion = new int[2];
		//child_view_TexitView_02[0].getLocationOnScreen(locaion);
		//child_view_TexitView_02[0].setText(String.valueOf(locaion[0]) + " " + String.valueOf(locaion[1]));		


		int left = root_layout.getLeft();
		int right = root_layout.getRight();
		int top = root_layout.getTop();
		int bottom = root_layout.getBottom();
		int center_x = (right-left)/2;
		int center_y = (bottom-top)/2;
		int field_width = right-left;
		int field_height = bottom-top;

		//Toast.makeText(Sub_04_PlayerCorrelationActivity.this, "Zahyou : " + String.valueOf(left) + " " + String.valueOf(right) + " " + String.valueOf(top) + " " + String.valueOf(bottom), Toast.LENGTH_LONG).show();

		//child_view_TexitView_01[0].setX(center_x);
		//child_view_TexitView_01[0].setY(center_y);
		//child_view_TexitView_02[0].setX((left + right) / 2);

		//en//////
		float r;
		float kakudai_x;
		float kakudai_y;

		if(field_width>field_height){
			r = field_height/2;
			kakudai_x = (float)field_width/field_height;
			kakudai_y = 1;
		}
		else{
			r = field_width/2;
			kakudai_y = (float)field_height/field_width;
			kakudai_x = 1;
		}

		float hosei = (float) 0.9;
		r=r*hosei;

		for(int i=0; i<gdatas.getP_num(); i++){
			float kakudo = 360/gdatas.getP_num() * i - 90;
			float radian = (float)Math.toRadians(kakudo);
			float cos = (float)Math.cos(radian);
			float sin = (float)Math.sin(radian);

			child_view_TexitView_01[i].setX(center_x + r * cos * kakudai_x - child_view_TexitView_01[i].getWidth()/2);
			child_view_TexitView_01[i].setY(center_y + r * sin * kakudai_y - child_view_TexitView_01[i].getHeight()/2);
		}
		////////////////

		DrawView d_view = new DrawView(this);
		//d_view.setAlpha((float)1.0);
		root_layout.addView(d_view,0);


	}
	//////////////////////

	public class DrawView extends View{
		public DrawView(Context context){
			super(context);
		}


		@Override
		protected void onDraw(Canvas canvas){
			Paint paint = new Paint();
			//paint.setStrokeWidth((float)9.8);
			//paint.setColor(Color.BLUE);
			final int PAINT_WIDTH_COUNT = 100;
			final int ARROW_START = 55;
			final int ARROW_END = 65;
			final float DEFAULT_WIDTH = (float)3.0;

			int[][] arrow_count;
			arrow_count = new int[gdatas.getP_num()][];
			for(int i=0; i<gdatas.getP_num(); i++){
				arrow_count[i] = new int[gdatas.getP_num()];
				for(int j=0; j<gdatas.getP_num(); j++){
					arrow_count[i][j] = 0;
				}
			}

			//canvas.drawLine(child_view_TexitView_01[0].getX(),child_view_TexitView_01[0].getY(),100,100,paint);
			float x1,y1,x2,y2;
			final int MAXDRAW=2;
			boolean[] drawFlag = new boolean[MAXDRAW];
			drawFlag[0]=false;
			drawFlag[1]=false;
			int[] drawColor = new int[MAXDRAW];
			drawColor[0] = 0;
			drawColor[1] = 0;
			
			for(int i=0; i<gdatas.getP_num(); i++){
				for(int j=0; j<gdatas.getP_num(); j++){
					drawFlag[0]=false;
					switch(gdatas.playerDatas[i].player_relations[j]){
						case gdatas.RELATION_FRIENDLY:
							drawColor[0]=gdatas.COLOR_FRIENDRY;
							drawFlag[0]=true;
							break;
						case gdatas.RELATION_HATE:
							drawColor[0]=gdatas.COLOR_HATE;
							drawFlag[0]=true;
							break;
					}
					drawFlag[1]=false;
					switch(gdatas.playerDatas[i].job_judgement[j]){
						case gdatas.JJUDGE_WHITE:
							drawColor[1]=gdatas.COLOR_JJ_WHITE;
							drawFlag[1]=true;
							break;
						case gdatas.JJUDGE_BLACK:
							drawColor[1]=gdatas.COLOR_JJ_BLACK;
							drawFlag[1]=true;
							break;
					}
					
					for(int flagCnt=0; flagCnt<MAXDRAW; flagCnt++){
						if(drawFlag[flagCnt]){
							paint.setColor(drawColor[flagCnt]);
							paint.setStrokeWidth(DEFAULT_WIDTH);
							x1 = child_view_TexitView_01[i].getX() + child_view_TexitView_01[i].getWidth()/2;
							y1 = child_view_TexitView_01[i].getY() + child_view_TexitView_01[i].getHeight()/2;
							x2 = child_view_TexitView_01[j].getX() + child_view_TexitView_01[j].getWidth()/2;
							y2 = child_view_TexitView_01[j].getY() + child_view_TexitView_01[j].getHeight()/2;
							if(Math.abs(y1-y2)<child_view_TexitView_01[i].getHeight()*3){
								switch(arrow_count[i][j]){
									case 0:
										break;
									case 1:
										y1 = y1 + child_view_TexitView_01[i].getHeight()*(float)0.25;
										y2 = y2 + child_view_TexitView_01[j].getHeight()*(float)0.25;
										break;
									case 2:
										y1 = y1 - child_view_TexitView_01[i].getHeight()*(float)0.25;
										y2 = y2 - child_view_TexitView_01[j].getHeight()*(float)0.25;
										break;
									case 3:
										y1 = y1 + child_view_TexitView_01[i].getHeight()*(float)0.5;
										y2 = y2 + child_view_TexitView_01[j].getHeight()*(float)0.5;
										break;
									case 4:
										y1 = y1 - child_view_TexitView_01[i].getHeight()*(float)0.5;
										y2 = y2 - child_view_TexitView_01[j].getHeight()*(float)0.5;
										break;
								}
								
							}else{
								switch(arrow_count[i][j]){
									case 0:
										break;
									case 1:
										x1 = x1 + child_view_TexitView_01[i].getWidth()*(float)0.2;
										x2 = x2 + child_view_TexitView_01[j].getWidth()*(float)0.2;
										break;
									case 2:
										x1 = x1 - child_view_TexitView_01[i].getWidth()*(float)0.2;
										x2 = x2 - child_view_TexitView_01[j].getWidth()*(float)0.2;
										break;
									case 3:
										x1 = x1 + child_view_TexitView_01[i].getWidth()*(float)0.4;
										x2 = x2 + child_view_TexitView_01[j].getWidth()*(float)0.4;
										break;
									case 4:
										x1 = x1 - child_view_TexitView_01[i].getWidth()*(float)0.4;
										x2 = x2 - child_view_TexitView_01[j].getWidth()*(float)0.4;
										break;
								}
							}
							arrow_count = arrow_add(i,j,arrow_count);

							for(int n=0; n<PAINT_WIDTH_COUNT; n++){
								if(n>ARROW_START && n<=ARROW_END){
									paint.setStrokeWidth(DEFAULT_WIDTH + (ARROW_END - n) * (float)2.0);
								}
								canvas.drawLine(x1+(x2-x1)/PAINT_WIDTH_COUNT*n,y1+(y2-y1)/PAINT_WIDTH_COUNT*n,x1+(x2-x1)/PAINT_WIDTH_COUNT*(n+1),y1+(y2-y1)/PAINT_WIDTH_COUNT*(n+1),paint);
							}
						}
					}
				}
			}
		}
	}

	//arrow count method////
	private int[][] arrow_add(int i, int j, int[][] arrow_count){
		arrow_count[i][j]++;
		arrow_count[j][i] = arrow_count[i][j];
		return arrow_count;
	}
	////////////////////////

	//Activity Reload//////
	private void reload(){
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
