package com.sonderben.sdbvideoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.Pattern;

@SpringBootApplication
public class SdbvideoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdbvideoApiApplication.class, args);
		String a= "((http|https)://)(www.)?" +
				"[a-zA-Z0-9@:%._\\+~#?&///=]" +
				"{2,256}\\.[a-z]" +
				"{2,6}\\b([-a-zA-Z0-9@:%" +
				"._\\+~#?&//=]*)";
		System.out.println(a);
	}

}
