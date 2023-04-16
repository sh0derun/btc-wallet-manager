package com.shaderun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@SpringBootApplication
public class BtcWalletManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtcWalletManagerApplication.class, args);
	}

	@Bean
	public DateTimeFormatter dateTimeFormatter() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");
	}

	@Bean
	public void conf(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
