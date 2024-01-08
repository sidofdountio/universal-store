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
	@Bean
	CommandLineRunner runner(
			CustomerRepo customerRepo,
			ProductCategoryRepo productCategoryRepo,
			ProductRepo productRepo,
			SupplierRepo supplierRepo) {
		return args -> {


			customerRepo.save(new Customer(
					null,
					"John",
					"unknow",
					"unknow",
					"unknow",
					new ArrayList<>(),
					new ArrayList<>()));

			ProductCategory productCategory = productCategoryRepo.save(new ProductCategory(
					1L,
					"Meche",
					new ArrayList<>()
			));
			ProductCategory productCategory2 = productCategoryRepo.save(new ProductCategory(
					2L,
					"Savon",
					new ArrayList<Product>()
			));

			productRepo.save(new Product(1L,
					"Star Africa",
					"noir",
					0,
					1500,
					"Description",
					900,
					"codeSA",
					new ArrayList<Purchase>(),
					null,
					null,
					productCategory,
					""));
			productRepo.save(new Product(2L,
					"Jazz",
					"",
					0,
					500,
					"",
					250,
					"codeJa",
					new ArrayList<Purchase>(),
					null,
					null,
					productCategory2,
					""));

			supplierRepo.save(new Supplier(null, "Chinoi", "chinoi@gmail.com", "67828929", "Yaounde", new ArrayList<>()));
		};
	}
}
