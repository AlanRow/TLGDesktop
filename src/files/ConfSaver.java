package files;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConfSaver {
	private String phonesFilePath;
	private List<String> recPhones;
	private List<String> newPhones;
	
	public ConfSaver()
	{
		phonesFilePath = "recently_phones.txt";
		initLists();
	}
	
	private void initLists()
	{
		recPhones = new LinkedList<String>();
		newPhones = new LinkedList<String>();
		File phones = new File(phonesFilePath);
		
		if (!phones.exists())
			return;
		
		try(FileReader reader = new FileReader(phones)){
			int code = 0;
			StringBuilder currPhone = new StringBuilder();;
			
			while ((code = reader.read()) > 0){
				if ((char) code == '\n'){
					reader.read();
					recPhones.add(currPhone.toString());
					currPhone.delete(0, currPhone.length() - 1);
				}
				else {
					currPhone.append((char) code);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public List<String> getRecentlyPhones(){
		if (recPhones.size() == 0){
			List<String> list = new ArrayList<String>();
			list.add("");
			return list;
		}
		
		return recPhones;
	}
	
	public void addPhone(String phone){
		if (!recPhones.contains(phone))
			newPhones.add(phone);
	}
	
	public void save(){
		try (FileWriter writer = new FileWriter(phonesFilePath)){
			for (String phone : newPhones){
				writer.append(phone + "\n\r");
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
