package com.example.WeBuyBusinesses;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String usern;
        String email;
        String pass, confirmPass;


        SQLCreateTable.createTable();



        System.out.println("Do you want to sign up or login?");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        String choice = in.nextLine();


        if (choice.equals("1")) {

            System.out.println("Enter a username of your choice: ");
            usern = in.nextLine();

            System.out.println("Enter your email address: ");
            email = in.nextLine();

            System.out.println("Enter your password: ");
            pass = in.nextLine();

            System.out.println("Confirm your password: ");
            confirmPass = in.nextLine();

            while (!pass.equals(confirmPass)) {
                System.out.println("Passwords do not match!!!");
                System.out.println("Enter your password: ");
                confirmPass = in.nextLine();
            }

            SignUp.signUpUser(usern, email, pass);

            System.out.println("Sign-up successful! You can now log in.");

        } else if (choice.equals("2")) {

            System.out.println("Enter your username: ");
            usern = in.nextLine();

            System.out.println("Enter your password: ");
            pass = in.nextLine();

            boolean isLoggedIn = Login.loginUser(usern, pass);
            if (isLoggedIn) {
                System.out.println("Welcome, " + usern);
            } else {
                System.out.println("Login failed. Invalid username or password.");
                return;
            }
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }


        System.out.println("Are you a Buyer or Seller?");
        System.out.println("1. Buyer");
        System.out.println("2. Seller");
        String roleChoice = in.nextLine();

        if (roleChoice.equals("1")) {
            System.out.println("Enter the business you want to buy: ");
            String businessName = in.nextLine();

            Sell business = Buy.checkBusinessExists(businessName);

            if (business != null) {

                System.out.println("What is your initial bid: ");
                double bid = in.nextDouble();

                Buy buyer = new Buy(usern, bid);
                buyer.attemptPurchase(business);
            }


        } else if (roleChoice.equals("2")) {
            
            System.out.println("You are a Seller.");

            String sellerN;
            while (true) {
                System.out.print("1. Name of seller: ");
                sellerN = in.nextLine();
                if (Sell.isValidName(sellerN)) {
                    break;
                } else {
                    System.out.println("Invalid input! Name should only contain letters, spaces, or apostrophes (''). Please try again.");
                }
            }

            System.out.println("Enter the name of your business: ");
            String businessName;

            while (true) {
                System.out.print("2. Name of business: ");
                businessName = in.nextLine();
                if (Sell.isValidName(businessName)) {
                    break;
                } else {
                    System.out.println("Invalid input! Business name should only contain letters, spaces, or apostrophes (''). Please try again.");
                }
            }

            String priceInput;
            double price = 0.0;
            while (true) {
                System.out.print("3. Enter price of business (e.g., R1000.50): ");
                priceInput = in.nextLine();
                if (Sell.isValidPrice(priceInput)) {
                    price = Double.parseDouble(priceInput.substring(1)); // Remove "R" and parse the price
                    break;
                } else {
                    System.out.println("Invalid price! It must start with 'R' followed by a valid number.");
                }
            }



            int employees;
            while (true) {
                System.out.print("4. Number of employees: ");
                employees = in.nextInt();
                if (Sell.isValidEmployees(employees)) {
                    break;
                } else {
                    System.out.println("Invalid input! Number of employees must be greater than zero.");
                }
            }



            String regCode;
            while (true) {
                System.out.print("5. Enter business registration code (starts with 'E' followed by 9 digits): ");
                regCode = in.nextLine();
                if (Sell.isValidRegCode(regCode)) {
                    break;
                } else {
                    System.out.println("Invalid regCode! It must start with 'E' and be followed by 9 digits.");
                }
            }



            int custStats;
            while (true) {
                System.out.print("6. Enter customer stats rate (0 to 100): ");
                custStats = in.nextInt();
                if (Sell.isValidCustomerStats(custStats)) {
                    break;
                } else {
                    System.out.println("Invalid input! Customer stats rate must be between 0 and 100.");
                }
            }

            String location;
            while (true) {
                System.out.print("7. Enter where your business is located: ");
                location = in.nextLine();
                if (Sell.isValidLocation(location)) {
                    break;
                } else {
                    System.out.println("Invalid input! Location cannot be empty.");
                }
            }


            System.out.println("8. Enter a description of your business: ");
            String description = in.nextLine();
            //description = in.nextLine();

            Sell businessForSale = new Sell(sellerN, businessName, price, employees, regCode, custStats, location, description);
            businessForSale.insertIntoDatabase();

            businessForSale.displayInfo();
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

        in.close();
    }
}

