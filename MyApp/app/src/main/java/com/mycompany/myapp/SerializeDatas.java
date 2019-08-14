package com.mycompany.myapp;
import java.io.*;

public class SerializeDatas 
{
	//data kakunou class
	public class Sweets implements Serializable{
		int sir_p_num;
		int sir_j_num;
		String[] sir_p_names;
		String[] sir_j_names;
	}
	////////

	//output
	public void saveData(GrobalDatas gdatas){
		try{
			ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(gdatas.LOCAL_FILE));
			Sweets sweets = new Sweets();
			sweets.sir_p_num = gdatas.getP_num();
			sweets.sir_j_num = gdatas.getJ_num();
			sweets.sir_p_names = new String[gdatas.maxplayer];
			for(int i=0; i<gdatas.maxplayer; i++){
				sweets.sir_p_names[i] = gdatas.playerDatas[i].name;				
			}
			sweets.sir_j_names = new String[gdatas.maxjobs];
			for(int i=0; i<gdatas.maxjobs; i++){
				sweets.sir_j_names[i] = gdatas.jobDatas[i].name;				
			}

			objOutStream.writeObject(sweets);
			objOutStream.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//get datas
	public String getDatas(GrobalDatas gdatas){
		try{
			ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(gdatas.LOCAL_FILE));
			
			Sweets sweets = (Sweets) objInStream.readObject();
			objInStream.close();		
			
			return sweets.sir_p_names[0];
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "error";
	}
}
