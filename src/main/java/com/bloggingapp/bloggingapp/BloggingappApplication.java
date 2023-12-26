package com.bloggingapp.bloggingapp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bloggingapp.bloggingapp.config.Constants;
import com.bloggingapp.bloggingapp.entity.Role;
import com.bloggingapp.bloggingapp.repository.RoleRepo;

@SpringBootApplication
public class BloggingappApplication implements CommandLineRunner{

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BloggingappApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			Role adminRole = new Role(Constants.ADMIN_USER, "ADMIN_USER");
			Role normalRole = new Role(Constants.NORMAL_USER, "NORMAL_USER");
			List<Role> roles = List.of(adminRole, normalRole);
			roleRepo.saveAll(roles);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
