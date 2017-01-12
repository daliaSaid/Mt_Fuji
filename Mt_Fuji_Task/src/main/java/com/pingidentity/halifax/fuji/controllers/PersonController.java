package com.pingidentity.halifax.fuji.controllers;
import com.pingidentity.halifax.fuji.domain.Person;
import com.pingidentity.halifax.fuji.domain.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;


@Controller
public class PersonController{

	@Autowired
	private PersonRepository repository;

	/*
	 * Returns all people in the database and redirects to the listPeople view.
	 */
	@RequestMapping(value = { "/people", "/index", ""}, method = RequestMethod.GET)
	public String listPeople(Model model) {
		model.addAttribute("people", repository.findAll());
	    return "people/listPeople";
	}

	/*
	 * Saves the received person information to the database and updates the list of people.
	 * The first and last names of people are edited to ensure the names have an upper-case first letter
	 * with other letters lower case. 
	 */
    @RequestMapping(value="/people", method = RequestMethod.POST)
     public String addNewPerson(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName, 
     	@RequestParam("age") String age, @RequestParam("height") String height, Model model) {
     	firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase(); 
     	lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase(); 
     	repository.save(new Person(firstName, lastName, Integer.parseInt(age), Integer.parseInt(height)));
     	model.addAttribute("people", repository.findAll());
     	return "people/listPeople";
    }
}
