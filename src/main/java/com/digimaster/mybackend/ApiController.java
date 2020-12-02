package com.digimaster.mybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")

public class ApiController {
	@Autowired
	private PersonService personService;
	
//	public ApiController(PersonService personService) {
//		this.personService = personService;
//		
//	}
	
	
	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "hello World";
		
	}

	@GetMapping("/param")
	public String returnParam(@RequestParam String username) {
		return "ini param : "+username;
	}
	
	@PostMapping("/post")
	public String postParam(@RequestBody String text) {
		return "ini post param :"+text;
	}
	
	@PostMapping("/postBody")
	public String postBody(@RequestBody PersonModel personModel) {
		return "ini person : "+personModel.getName()+personModel.getCity();
	}
	
	@PostMapping("/create")
	public PersonModel createPerson(@RequestBody PersonModel personModel) {
		return personService.createPerson(personModel);
	}
	@PostMapping("/createpersons")
	public Iterable<PersonModel> createPersons(@RequestBody Iterable<PersonModel> personModel) {
		return personService.createPerson(personModel);
	}
	@GetMapping("/person/{id}")
	public PersonModel getPerson(@PathVariable int id) {
		return personService.getPerson(id);
	}
	@GetMapping("/persons")
	public BaseResponse<PersonModel> getPersonWithBaseResponse(@RequestParam int id) {
		PersonModel person = personService.getPersonWithValidation(id);
		BaseResponse<PersonModel> baseResponse =  new BaseResponse<>();
		if(person != null) {
			baseResponse.setCode(200);
			baseResponse.setSuccess(true);
			baseResponse.setMessage("user found");
			baseResponse.setData(person);
			
		} else {
			baseResponse.setCode(404);
			baseResponse.setSuccess(false);
			baseResponse.setMessage("user not found");
			baseResponse.setData(person);
		}
		return baseResponse;
	}
	
	@GetMapping("/person/name")
	public PersonModel getPerson(@RequestParam String name) {
		return personService.getPerson(name);
	}

	@GetMapping("/person/get")
	public PersonModel getPerson(@RequestParam String name, @RequestParam String city) {
		return personService.getPerson(name, city);
	}
	@GetMapping("/person/coba")
	public PersonModel getPerson(@RequestParam String city, @RequestParam int age) {
		return personService.getPerson(city, age);
	}
	@GetMapping("/person")
	public Iterable<PersonModel> getPersons(){
		return personService.getAllPersons();
	}
	@GetMapping("/personss")
	public PersonResponse<PersonModel> getPersonWithResponse(){
		PersonResponse<PersonModel> personResponse = new PersonResponse<>();
		personResponse.setCode(200);
		personResponse.setSuccess(true);
		personResponse.setMessage("success");
		personResponse.setData(personService.getAllPersons());
		
		return personResponse;
	}
	
	@GetMapping("/personcity")
	public Iterable<PersonModel> getPersonModelByCity(@RequestParam String city){
		return personService.getPersonByCity(city);
	}
	@PutMapping("/person/{id}")
	public PersonModel updatePerson(@PathVariable int id, @RequestBody PersonModel personModel) {
		return personService.updatePerson(personModel, id);
	}
	@DeleteMapping("/delete/{id}")
	public boolean deletePerson(@PathVariable int id) {
		return personService.deletePerson(id);
	}
	@DeleteMapping("/person/name/{name}")
	public boolean deletePersonModelByName(@PathVariable String name) {
		personService.deletePersonByName(name);
		return true;
	}
	@PostMapping("/file")
	public boolean uploadFile(@RequestParam("file")MultipartFile file) {
		personService.saveFile(file);
		return true;
	}
	@PostMapping("/files")
	public boolean uploadFile(@RequestParam("file")MultipartFile file, @RequestParam int id) {
		personService.saveFile(file, id);
		return true;
}
	//pagikuu cerah matahari bersinar 
}
