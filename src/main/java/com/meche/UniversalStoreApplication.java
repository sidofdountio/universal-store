package com.meche;

import com.meche.model.*;
import com.meche.repo.CustomerRepo;
import com.meche.repo.ProductCategoryRepo;
import com.meche.repo.ProductRepo;
import com.meche.repo.SupplierRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class UniversalStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversalStoreApplication.class, args);
	}
	
}
