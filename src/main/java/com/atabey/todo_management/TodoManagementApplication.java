package com.atabey.todo_management;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Todo Management REST API Documentation",
				description = "Todo Management REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Gazi",
						email = "gaziatabey@windowslive.com",
						url= "https://gaziatabey.com"
				),
				license = @License(
						name = "Apache 2.0",
						url ="https://www.javaguides.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Todo Management REST API Documentation",
				url = "https://www.javaguides.net/external-doc.html"
		)

)



@SpringBootApplication
public class TodoManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoManagementApplication.class, args);
	}

}
