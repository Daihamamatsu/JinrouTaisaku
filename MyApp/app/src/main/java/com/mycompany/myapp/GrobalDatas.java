package com.mycompany.myapp;

import android.app.Application;
import java.lang.annotation.*;
import android.icu.text.*;
import java.util.*;
import android.graphics.*;
import android.widget.*;
import java.io.*;
import android.util.*;

public class GrobalDatas extends Application
{
	//global datas
	private int p_num;
	private int j_num;
	private boolean boo_init = false;
	private int current_player = 0;
	private String current_mode = "";
	private int day;
	private boolean use_zero_day = false;
	
	//define number
	public static final int maxplayer  = 20;
	public static final int maxjobs  = 10;
	public static final int RELATION_NONE = 0;
	public static final int RELATION_FRIENDLY = 1;
	public static final int RELATION_HATE = 2;
	public static final int JJUDGE_NONE = 0;
	public static final int JJUDGE_WHITE = 1;
	public static final int JJUDGE_BLACK = 2;
	public static final int GROUPID_RELATION_FRIENDLY = 91;
	public static final int GROUPID_RELATION_HATE = 92;
	public static final int GROUPID_RELATION_NONE = 93;
	public static final int GROUPID_SET_RELATIONSHIP = 91;
	public static final int GROUPID_JOB_CO = 99;
	public static final int GROUPID_ONLY_CO = 100;
	public static final int GROUPID_JJUDGEMENT_NONE = 111;
	public static final int GROUPID_JJUDGEMENT_WHITE = 112;
	public static final int GROUPID_JJUDGEMENT_BLACK = 113;
	public static final int COLOR_FRIENDRY = Color.CYAN;
	public static final int COLOR_HATE = Color.RED;
	public static final int COLOR_JJ_WHITE = Color.YELLOW;
	public static final int COLOR_JJ_BLACK = Color.BLACK;
	public static final int COLOR_DEAD = Color.RED;
	public static final int FIRST_DAY = 1;
	public static final int NOT_KILLED = -1;
	public static final int NO_SELECTED = -99;
	public static final int LIFE_DEAD = 1;
	public static final int LIFE_LIVING = 0;
	public static final String LOCAL_FILE = "datas.bin";
	//public static final String FILE_START_PLAYER = "start_playerdatas num=";
	//public static final String FILE_END_PLAYER = "end_playerdatas";
	//public static final String FILE_START_JOB = "start_jobdatas num=";
	//public static final String FILE_END_JOB = "end_jobdatas";
	
	//kouzoutai//////////////
	//player data
	private class PlayerDatas{
		public String name;
		public int[] player_relations;
		public int[] job_judgement;
		public int job;
		public int life;
	}
	PlayerDatas[] playerDatas = new PlayerDatas[maxplayer];
	
	//job data
	private class JobDatas{
		public String name;
	}
	JobDatas[] jobDatas = new JobDatas[maxjobs];
	
	//event data
	private class EventDatas{
		String eventDate;
		String eventLog;
	}
	ArrayList<EventDatas> e_datas = new ArrayList<EventDatas>();
	
	//day data
	private class DayDatas{
		public int bited_player;
		public int hanged_player;
	}
	DayDatas[] dayDatas = new DayDatas[maxplayer];//dayDatas's index is Game Day///
	////////////////////////
	
	//setter and getter
	public int getP_num(){
		return this.p_num;
	}
	public void setP_num(int inputdata){
		this.p_num = inputdata;
	}
	
	public int getJ_num(){
		return this.j_num;
	}
	public void setJ_num(int inputdata){
		this.j_num = inputdata;
	}
	
	public boolean getBoo_init(){
		return this.boo_init;
	}
	
	public int getCurrent_player(){
		return this.current_player;
	}
	public void setCurrent_player(int inputdata){
		this.current_player = inputdata;
	}
	
	public String getCurrent_mode(){
		return this.current_mode;
	}
	public void setCurrent_mode_Friendly(){
		this.current_mode = (String)getText(R.string.menu_01_item1);
	}
	public void setCurrent_mode_Hate(){
		this.current_mode = (String)getText(R.string.menu_01_item2);
	}
	public void setCurrent_mode_CO(){
		this.current_mode = (String)getText(R.string.menu_01_item3);
	}
	
	public int getDay(){
		return this.day;
	}
	public void setDay(int inputdata){
		this.day = inputdata;
	}
	
	public boolean getUse_Zero_Day(){
		return this.use_zero_day;
	}

	//////////////////
	
	//initialization method
	public void restartInitialization(){
		for(int i=0; i<maxplayer; i++){
			playerDatas[i].job = 0;
			playerDatas[i].life = LIFE_LIVING;
		}
		
		this.e_datas = new ArrayList<EventDatas>();
		
		for(int i=0; i<maxplayer; i++){
			dayDatas[i] = new DayDatas();
			dayDatas[i].bited_player = NO_SELECTED;
			dayDatas[i].hanged_player = NO_SELECTED;
		}
		
		this.current_player = 0;

		this.current_mode = (String)getText(R.string.menu_01_item1);

		this.day = FIRST_DAY;
	}
	
	public void initialization(){
		
		this.p_num=1;
		this.j_num=10;
		
		for(int i=0; i<maxplayer; i++){
			playerDatas[i] = new PlayerDatas();
			playerDatas[i].name = "murabito"+String.valueOf(i+1);
			playerDatas[i].job = 0;
			playerDatas[i].life = LIFE_LIVING;
		}
		
		for(int i=0; i<maxjobs; i++){
			jobDatas[i] = new JobDatas();
			if(i==0)
				jobDatas[i].name = "murabito";
			else
				jobDatas[i].name = "job"+String.valueOf(i+1);
		}
		
		this.boo_init = true;
		
		this.use_zero_day = false;
		
		restartInitialization();
	}
	////////////////////
	
	//get date method//////////////
	public static String getNowDate(){
		final DateFormat df = new SimpleDateFormat("HH:mm:ss");
		final Date date = new Date(System.currentTimeMillis());
		return df.format(date);
	}
	//////////////////////////////

	//player relations method////
	///////////initilazetion/////////////
	public void initializeRelations(){
		for(int i=0; i<this.p_num; i++){
			this.playerDatas[i].player_relations = new int[this.p_num];
			
			for(int j=0; j<this.p_num; j++){
				this.playerDatas[i].player_relations[j] = RELATION_NONE;
			}
		}
	}
	///////////////////////////////////
	
	/////////set Relation//////////////
	public void setPlayerRelations(int currentplayer, int targetplayer, int relation){
		switch(relation){
		case RELATION_NONE :
			this.playerDatas[currentplayer].player_relations[targetplayer] = RELATION_NONE;		
			break;
		case RELATION_FRIENDLY :
			this.playerDatas[currentplayer].player_relations[targetplayer] = RELATION_FRIENDLY;		
			break;
		case RELATION_HATE :
			this.playerDatas[currentplayer].player_relations[targetplayer] = RELATION_HATE;
			break;
		}
	}
	///////////////////////////////////
	
	///////////////////////
	
	
	//job judgement method////
	///////////initilazetion/////////////
	public void initializeJjudgement(){
		for(int i=0; i<this.p_num; i++){
			this.playerDatas[i].job_judgement = new int[this.p_num];

			for(int j=0; j<this.p_num; j++){
				this.playerDatas[i].job_judgement[j] = JJUDGE_NONE;
			}
		}
	}
	///////////////////////////////////

	/////////set job judgement//////////////
	public void setJobJudgement(int currentplayer, int targetplayer, int judgement){
		switch(judgement){
			case JJUDGE_NONE :
				this.playerDatas[currentplayer].job_judgement[targetplayer] = JJUDGE_NONE;		
				break;
			case JJUDGE_WHITE :
				this.playerDatas[currentplayer].job_judgement[targetplayer] = JJUDGE_WHITE;		
				break;
			case JJUDGE_BLACK :
				this.playerDatas[currentplayer].job_judgement[targetplayer] = JJUDGE_BLACK;		
				break;
		}
	}
	///////////////////////////////////

	///////////////////////
	
	
	//event datas method/////////
	public void addEventdatas(String input_log){
		EventDatas edata = new EventDatas();
		edata.eventDate = this.getNowDate();
		edata.eventLog = input_log;
		
		this.e_datas.add(edata);
	}
	public void addEventdatas(String input_log, String zenhan){
		EventDatas edata = new EventDatas();
		edata.eventDate = zenhan;
		edata.eventLog = input_log;

		this.e_datas.add(edata);
	}
	/////////////////////////////
		
	//day method////
	public void setDayText(TextView tv){
		tv.setText((String)getText(R.string.daytext_01) + " " 
		+ String.valueOf(this.day) + " " 
		+ (String)getText(R.string.daytext_02));
	}
	////////////////
	
	//day datas method/////
	public void calcToday(){
		int currentToday = getDay();
		int elapsedDays = 0;
		for(int i=0; i<maxplayer; i++){
			if(dayDatas[i].bited_player != NO_SELECTED)
				elapsedDays++;
		}
		setDay(FIRST_DAY + elapsedDays);
		if(getDay() > currentToday)
			addEventdatas(
			(String)getText(R.string.daychanged_01) + " "
			+ getDay() + " " + (String)getText(R.string.daychanged_02)
			, "");
	}
	///////////////////////
	
	//life method////
	public void initializeLife(){
		for(int i=0; i<maxplayer; i++)
			playerDatas[i].life=LIFE_LIVING;
	}
	public void calcLife(){
		initializeLife();
		for(int i=0; i<maxplayer; i++){
			if(dayDatas[i].bited_player != NO_SELECTED && dayDatas[i].bited_player != NOT_KILLED){
				playerDatas[dayDatas[i].bited_player].life = LIFE_DEAD;
			}
			if(dayDatas[i].hanged_player != NO_SELECTED && dayDatas[i].hanged_player != NOT_KILLED){
				playerDatas[dayDatas[i].hanged_player].life = LIFE_DEAD;
			}
		}
	}
	/////////////////
	
	//file input and output method///////////
	//data kakunou class//

	
	public void openText(){
		InputStream in;
		String lineBuffer;
		
		try{
			in = openFileInput(LOCAL_FILE);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			while((lineBuffer = reader.readLine()) != null){
				Log.d("FleAccess", lineBuffer);
				//System.out.print(lineBuffer);
			}
		}
		catch(IOException e){
			e.printStackTrace();			
		}
	}
	
	public void saveText(){
		OutputStream out;
		try{
			out = openFileOutput(LOCAL_FILE,MODE_PRIVATE);
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
			
			//writer.println(FILE_START_PLAYER);
			writer.println(String.valueOf(p_num));
			for(int i=0; i<maxplayer; i++){
				writer.println(playerDatas[i].name);
			}
			//writer.println(FILE_END_PLAYER);

			//writer.println(FILE_START_JOB);
			writer.println(String.valueOf(j_num));
			for(int i=0; i<maxjobs; i++){
				writer.println(jobDatas[i].name);
			}
			//writer.println(FILE_END_JOB);

			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	/////////////////////////////////////////
}
