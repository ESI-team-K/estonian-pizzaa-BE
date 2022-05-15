package com.example.estonianpizzaaBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@ServletComponentScan // TODO: Remove this line when auth is implemented
@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "Tere, Maailm!";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

