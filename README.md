# Grocery Shopping Console Application

## Project Overview

This project is a console-based Java application designed to simulate a grocery shopping experience. It allows users to browse items, add them to a shopping cart, calculate the total bill, apply discounts, and manage inventory. This application was developed as the final project for a Java development course, showcasing fundamental programming concepts.

## Features

The application includes the following key functionalities:

* **Item Management**: Handles a predefined list of grocery items with their respective prices and stock levels.
* **Shopping Cart**: Users can add multiple items and quantities to their shopping cart.
* **Total Bill Calculation**: Automatically calculates the total cost of all items in the cart.
* **Discount System**: Applies a 10% discount to the total bill if the total exceeds $100.
* **Inventory Management**:
    * Tracks the available stock for each item.
    * Prevents users from purchasing items that are out of stock or quantities exceeding the available stock.
    * Decreases the stock of an item after a successful purchase.
* **Item Search**: Allows users to search for specific items and view if they are available.
* **Average Price Calculation**: Computes and displays the average price of all items purchased in a shopping session.
* **Price-based Filtering**: Users can filter and display items whose prices are below a specified threshold.
* **Robust Error Handling**: Utilizes custom exceptions (e.g., `ItemNotFoundException`) and general exception handling for invalid inputs.

## Usage

Follow the on-screen prompts within the console.

* At the start, you can choose to `search` for an item or press Enter to proceed directly to shopping.
* During shopping, enter the item name and quantity.
* Type `finish` to complete your shopping session and view your total bill, average price, and filtered item lists.
* Type `exit` when prompted at the end of a session to quit the application.

## Technologies Used

* Java Development Kit (JDK) 24

## Learning Objectives Addressed

This project demonstrates proficiency in:

* Creating a Java program.
* Handling Strings and string operations.
* Working with operators and different data types.
* Handling exceptions and throwing new exceptions.
* Creating `for` loops and `while` loops.
* Verifying with conditional statements.
* Iterating through arrays.
* Creating basic methods and functions.

---
**Course:** Java Programming for Beginners by IBM on Coursera
