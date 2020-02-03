# beernextdoor

## V0.1

### Launch Project
SpringBoot Project, with modules webstart, jpa, sql driver, thymeleaf.

Necessitate a database and a user to run it locally.  
In SQL, run CREATE DATABASE database_name@localhost.  
Create a user and grant him privileges. 
Update application.properties datasources with your user/database.  
JPA/HIBERNATE will create tables when you first run the app.

## API routes

Currently, you can request :

### BeerController
@GetMapping("/beers")  
@GetMapping("/beers/{id}")  
@PostMapping("/beers")  
@PutMapping("/beers/{id}")  
@DeleteMapping("/beers/{id}")  

### TypeController
@GetMapping("/types")  
@GetMapping("/types/{id}")  
@PostMapping("/types")  
@PutMapping("/types/{id}")  
@DeleteMapping("/types/{id}")  

### BrandController
@GetMapping("/brands")  
@GetMapping("/brands/{id}")  
@PostMapping("/brands")  
@PutMapping("/brands/{id}")  
@DeleteMapping("/brands/{id}")  

NB : to create a beer object, you first need to be sur that type and brand you assign to beer alreaydy exists.
