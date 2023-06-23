package com.dating.crow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dating.crow.profile.photo.FilesStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class CrowApplication implements CommandLineRunner{

	  @Resource
	  FilesStorageService storageService;
	  
	public static void main(String[] args) {
		SpringApplication.run(CrowApplication.class, args);
	}

	 @Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }
}
