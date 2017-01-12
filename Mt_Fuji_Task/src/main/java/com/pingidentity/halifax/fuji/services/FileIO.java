package com.pingidentity.halifax.fuji.services;

import com.pingidentity.halifax.fuji.domain.Person;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.springframework.core.io.ClassPathResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

public class FileIO{

	/*
	 * The input String is parsed to extract person details and each person is entered in the database.
	 */
	public static ArrayList<Person> parseString(String input){
		ArrayList<Person> parsedPeople = new ArrayList<Person>();
		String[] splitContents = input.split("\n");
		for (int i = 0; i < splitContents.length; i++) {
			String[] splitEntry = splitContents[i].split(":");
			if(splitEntry.length == 4){
				parsedPeople.add(new Person(splitEntry[0], splitEntry[1], Integer.parseInt(splitEntry[2]), Integer.parseInt(splitEntry[3])));
			}
		}
		return parsedPeople;
	}

	/*
	 * This method reads the input .txt file and provides it, as is, as input to the parseString method.
	 */
	public static ArrayList<Person> getPeopleFromTextFile(String fileName){
		String str = "";
		try {
			File file = new ClassPathResource(fileName).getFile();
			FileInputStream fis = new FileInputStream(file);
			int content;
			while ((content = fis.read()) != -1) {
				char ch = (char) content;
				str += ch;
			}
		// This catch block swallows the exception. Was not sure if it should have been logged/handled or left as is so as not to interrupt execution.
		}catch (FileNotFoundException e) {
			return new ArrayList<Person>();
		} catch (IOException e) {
			return new ArrayList<Person>();
		}
		return parseString(str);
	}

	/*
	 * This method reads the contents of the JSON input file and reformats it into a format similar to the one found in the .txt file
	 * so that it can be processed by the same parseString method.
	 */
	public static ArrayList<Person> getPeopleFromJSONFile(String fileName){
		String str = "";
		JSONParser parser = new JSONParser();

		try {     
			File file = new ClassPathResource(fileName).getFile();
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray people = (JSONArray) jsonObject.get("people");
            Iterator<JSONObject> iterator = people.iterator();
            while (iterator.hasNext()) {
             	JSONObject personJSON = (JSONObject) iterator.next();
 	            String firstName = (String) personJSON.get("firstName");
 	            String lastName = (String) personJSON.get("lastName");
	            String age = (String) personJSON.get("age");
	            String height = (String) personJSON.get("height");
	            str += firstName + ":" + lastName + ":" + age + ":" + height + "\n";
        	}
    	// This catch block swallows the exception. Was not sure if it should have been logged/handled or left as is so as not to interrupt execution.
        } catch (FileNotFoundException e) {
            return new ArrayList<Person>();
        } catch (IOException e) {
            return new ArrayList<Person>();
        } catch (ParseException e) {
            return new ArrayList<Person>();
        }

        return parseString(str);
    }	
}