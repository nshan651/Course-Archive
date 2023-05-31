/*
# COMP 353 Project 2
# DDL and Queries

Customer(CustomerID, cName, cEmail, cPassword, cAddress)  
Orders(OrderID, CustomerID, TotalPrice)  
Product(ProductID, pPrice, pName, pImage)  
Payment(PaymentID, OrderID, pCardNumber, pBank, pCVV, pEmail)  
Contains(OrderID, ProductID, Quantity)  
*/

--==========================================================================

-- DDL Statements

drop table if exists customer cascade;
drop table if exists orders cascade;
drop table if exists product cascade;
drop table if exists payment cascade;
drop table if exists contains cascade;

CREATE TABLE Customer( 
    CustomerID varchar(10) PRIMARY KEY, 
    cName varchar(40), 
    cEmail varchar(50), 
    cAddress varchar(50),  
    cPassword varchar(40) 
); 
INSERT INTO Customer (customerid, cName, cEmail, cAddress, cPassword)  
VALUES  
('C01', 'Jose', 'Jose44@outlook.com','8112 Skokie Blvd', 'Arcade786'), 
('C02', 'Hose', 'Hose442@gmail.com','815 Skokie Blvd', 'Arcade786'), 
('C03', 'Ross', 'Rose@gmail.com','223 Skokie Blvd', 'Arcade786');

-- Note: Table name must be plural to avoid conflict with "order" keyword
CREATE TABLE Orders (
    OrderID varchar(10) PRIMARY KEY, 
    TotalPrice float, 
    CustomerId varchar(10) references Customer(CustomerId) 
); 
INSERT INTO Orders(OrderID, TotalPrice, CustomerId)  
VALUES 
('R01', 24.99,'C01'), 
('R02', 5.99,'C01'), 
('R03', 13.99,'C02'), 
('R04', 3.99,'C03'); 

CREATE TABLE Product ( 
    ProductID varchar(10) PRIMARY KEY, 
    pName varchar(40), 
    pPrice float, 
    pImage varchar(40) -- Link of the image 
); 
INSERT INTO Product (ProductID, pName, pPrice, pImage)   
VALUES   
('P01', 'Apple', 0.35, 'www.goodfood.com/apple.jpg'),  
('P02', 'Pizza', 8.99, 'www.goodfood.com/pizza.jpg'),  
('P03', 'Fries', 2.99, 'www.goodfood.com/Fries.jpg'),  
('P04', 'Burger', 4.22, 'www.goodfood.com/Burger.jpg'),  
('P05', 'Falafel', 8.99, 'www.goodfood.com/Falafel.jpg');

CREATE TABLE Payment (  
	PaymentID varchar(10) PRIMARY KEY,
	OrderID varchar(10),
	FOREIGN KEY (OrderID) 
	  REFERENCES Orders(OrderID),  
  pCardNumber varchar(16),
    --CONSTRAINT cardCheck CHECK (char_len(pCardNumber) = 16),  
  pBank varchar(40),  
  pCVV varchar(3),  
    -- CONSTRAINT cvvCheck CHECK (char_len(pCardNumber) = 3), 
  pEmail varchar(40)  
); 
INSERT INTO Payment(PaymentID, OrderID, pCardNumber, pBank, pCVV, pEmail)  
VALUES  
('Z01', 'R01', 4370422307678197, 'Chase Bank', 354, 'burneremail@hotmail.com'), 
('Z02', 'R02', 433422307678197, 'US Bank', 456, 'hellomail@hotmail.com'), 
('Z03', 'R03', 4340422307678197, 'Community Bank', 789, 'safrneremail@gmail.com'); 

CREATE TABLE Contains( 
	OrderID varchar(10) NOT NULL, 
	ProductID varchar(10) NOT NULL, 
	PRIMARY KEY (OrderID, ProductID),
	FOREIGN KEY (OrderID)  
		REFERENCES Orders(OrderID),  
	FOREIGN KEY (ProductID)  
		REFERENCES Product(ProductID),  
	Quantity int  
); 
INSERT INTO Contains(OrderID, ProductID, Quantity)  
VALUES  
('R01', 'P01', 1), 
('R01', 'P02', 2), 
('R02', 'P03', 1), 
('R02', 'P04', 1), 
('R03', 'P02', 1); 

--==========================================================================

-- Queries

/* Query by: Nick Shannon
Select the customers who ordered more than $10 worth of produce and who 
ordered using gmail in the payment information. 
(cName, pEmail)
*/
select C.cName, C.cEmail, O.totalPrice
from orders O, customer C 
where C.customerID = O.customerID and O.totalPrice > 10.00
and cEmail like '%@gmail.com';
group by C.customerID
