# BillingManagementSystem
## Problem statement-- Standalone Billing Mangement Program for a Dress Retail store using JDBC.Once user enter product code, price has to fetch from DB Table.     After the Bill Produced, it has to be stored in DB Table.
## Models-:
•	User.
•	Admin.
•	Products.
•	Bill.
## Functionalities-:
### User
•	User will be able to register.
•	User will be able to login.
•	User will be able delete his account.
•	User will be able to update the account.
After register/login-:
•	Select the product (to do that view the product the and select among the products which are being viewed).
•	Generate the bill.
•	Store the bill in db.
### Admin
•	Validate the bills stored in the db.
•	Will be able to view all users.
•	Delete the users.
•	Add the products.
•	delete the products.
•	Update the products.
•	View the product.
### For creating the products entity-:
•	Product id (primary key).
•	Product name.
•	Product price.
### For creating the products entity-:
•	Bill number (primary key) .
•	Bill price.
•	Bill date.
•	Product id (foreign key references products->product id).
### For creating the customer entity-:
•	Customer id (Auto-generated) (primary key).
•	Customer password.
•	Customer name.
•	Customer number (Optional).
•	Customer address.
•	Products purchased.
•	Bill numbers (foreign key references table Bill->bill id).
### For creating admin entity-:
•	Admin name.
•	Admin password.

### At the starting-> Two options will be shown 
   - 1) Register new user.
   - 2) login User.
### If register new user chosen-> go to registration page (once registered)-> go to login page.
### If login user chosen-> Give two options 
    -1) Admin Login.
    -2) Customer Login.
### if admin login chosen, go to admin login page-> verify the credentials-> provide all the functionalities.
•	View details.
•	Update details.
•	Delete details.
•	Add products to cart.
•	View the total price.
•	Generate the bill.
•	View all unpaid bills.
•	View all the bills.

### If customer login chosen, go to customer page-> verify the credentials -> provide all the functionalities.
•	Validate all customers’ bills.
•	View all the customers.
•	Delete customers.
•	Add products.
•	Delete products.
•	Update products.
•	View all products.
•	View all the bills.
