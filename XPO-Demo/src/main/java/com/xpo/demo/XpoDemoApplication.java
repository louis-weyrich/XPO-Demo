package com.xpo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.xpo.demo.configure.FileStorageProperties;

@EnableConfigurationProperties({
    FileStorageProperties.class
})
@SpringBootApplication
public class XpoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XpoDemoApplication.class, args);
	}

}
