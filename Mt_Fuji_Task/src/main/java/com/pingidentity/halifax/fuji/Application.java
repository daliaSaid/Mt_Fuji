package com.pingidentity.halifax.fuji;

import com.pingidentity.halifax.fuji.domain.Person;
import com.pingidentity.halifax.fuji.domain.PersonRepository;
import com.pingidentity.halifax.fuji.services.FileIO;
import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootApplication
public class Application implements CommandLineRunner {


	@Autowired
	private PersonRepository repository;

	public static void main(final String[] args) {
	SpringApplication.run(Application.class, args);
	}

	/*
	 * This method rund at initialization to add people from file to the database.
	 */
	@Override
	public void run(String... strings) throws Exception {
			ArrayList<Person> people = FileIO.getPeopleFromTextFile("people.txt");
			people.addAll(FileIO.getPeopleFromJSONFile("people.json"));
			for (int i = 0; i < people.size(); i++) {
				repository.save(people.get(i));
			}
	}

}
