import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Custom exception class for item not found
class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

public class GroceryShopping {
    public static void main(String[] args) {
        // Initialize arrays for items and prices
        String[] item = new String[10];
        float[] price = new float[10];
        // array for stock
        int[] stock = new int[10];

        // Populate the arrays with some sample data
        item[0] = "Apple";
        price[0] = 0.50f;
        stock[0] = 150;
        item[1] = "Banana";
        price[1] = 0.30f;
        stock[1] = 10;
        item[2] = "Bread";
        price[2] = 2.00f;
        stock[2] = 112;
        item[3] = "Milk";
        price[3] = 1.50f;
        stock[3] = 100;
        item[4] = "Eggs";
        price[4] = 2.50f;
        stock[4] = 87;
        item[5] = "Cheese";
        price[5] = 3.00f;
        stock[5] = 23;
        item[6] = "Chicken";
        price[6] = 5.00f;
        stock[6] = 40;
        item[7] = "Rice";
        price[7] = 1.00f;
        stock[7] = 5;
        item[8] = "Pasta";
        price[8] = 1.20f;
        stock[8] = 450;
        item[9] = "Tomato";
        price[9] = 0.80f;
        stock[9] = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 'search' to find an item or press Enter to go to shopping: ");
            String initialInput = scanner.nextLine();

            if (initialInput.equalsIgnoreCase("search")) {
                while (true) {
                    System.out.println("Enter the name of the item to search (or press Enter to go to shopping): ");
                    String inputItem = scanner.nextLine();

                    if (inputItem.isEmpty()) {
                        System.out.println("Shopping...");
                        break;
                    }
                    searchItem(item, inputItem);
                }
                break;
            } else if (initialInput.isEmpty()) {
                System.out.println("Shopping...");
                break;
            } else {
                System.out.println("Invalid input. Please type 'search' or press Enter to continue.");
            }
        }


        // Outer infinite loop for multiple users
        while (true) {

            // Inner infinite loop for a single user's shopping
            float totalBill = 0.0f;
            List<Float> purchasedItemPrices = new ArrayList<Float>();
            while (true) {
                try {
                    System.out.println("Enter the name of the item (or type 'finish' to end shopping):");
                    isInStock(item, stock);
                    String inputItem = scanner.nextLine();

                    // Check if the user wants to finish shopping
                    if (inputItem.equalsIgnoreCase("finish")) {

                        if (totalBill < 100) {
                            System.out.println("Your total bill is: $" + totalBill);
                        } else {
                            float discountPercentage = 10.0f;
                            System.out.println("You have a discount by 10%!");
                            System.out.println("Your original bill is: $" + totalBill);
                            totalBill = totalBill * (1.0f - (discountPercentage/100.0f));
                            System.out.println("Your total bill now is: $" + totalBill);
                        }

                        float[] pricesArr = new float[purchasedItemPrices.size()];
                        for (int i = 0; i < purchasedItemPrices.size(); i++) {
                            pricesArr[i] = purchasedItemPrices.get(i);
                        }

                        float averagePrice = calculateAveragePrice(pricesArr);
                        if (!purchasedItemPrices.isEmpty()) {
                            System.out.println("The average shopping is: $" + String.format("%.2f", averagePrice));
                        } else {
                            System.out.println("No items were purchased, average can't be calculated.");
                        }

                        System.out.println("\nArticles below $1.50");
                        filterItemsBelowPrice(item, price, 1.50f);
                        System.out.println("\nArticles below $1.00");
                        filterItemsBelowPrice(item, price, 1.00f);
                        System.out.println("\nArticles below $2.50");
                        filterItemsBelowPrice(item, price, 2.50f);
                        System.out.println("\nArticles below $5.00");
                        filterItemsBelowPrice(item, price, 5.00f);

                        System.out.println("Thank you for shopping with us!");
                        System.out.println("(To leave type 'exit' or press enter to continue)");

                        break; // Exit the inner loop
                    }

                    // Find the index of the item in the array
                    int itemIndex = getItemIndex(item, inputItem);

                    // Ask for the quantity of the item
                    System.out.println("Enter the quantity of " + item[itemIndex] + ":");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (stock[itemIndex] == 0) {
                        System.out.println("Sorry " + item[itemIndex] + " is currently out of stock");
                        continue;
                    }
                    if (stock[itemIndex] < quantity) {
                        System.out.println("Sorry, we only have " + stock[itemIndex] + " of " + item[itemIndex] + " in stock.");
                        continue;
                    }

                    // Calculate the cost for the item and add it to the total bill
                    float itemCost = price[itemIndex] * quantity;
                    totalBill += itemCost;

                    stock[itemIndex] -= quantity;

                    for (int i = 0; i < quantity; i++) {
                        purchasedItemPrices.add(price[itemIndex]);
                    }

                    System.out.println("Added " + quantity + " x " + item[itemIndex] + " to the bill. Current total: $" + totalBill);

                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage()); // Print the exception message
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Clear the invalid input
                }

            }
            String userInput = scanner.nextLine();
            // Exit the program if the user types "exit"
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the shopping cart. Goodbye!");
                break;
            }

        }

        scanner.close();
    }

    private static int getItemIndex(String[] item, String inputItem) throws ItemNotFoundException {
        int itemIndex = -1;
        for (int i = 0; i < item.length; i++) {
            if (item[i].equalsIgnoreCase(inputItem)) {
                itemIndex = i;
                break;
            }
        }

        // If the item is not found, throw the custom exception
        if (itemIndex == -1) {
            throw new ItemNotFoundException("Item '" + inputItem + "' not found. Please try again.");
        }
        return itemIndex;
    }

    private static void searchItem(String[] items, String articleName) {
        int foundIndex = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(articleName)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Article '" + articleName + "' found at index: " + foundIndex);
        } else {
            System.out.println("Article '" + articleName + "' not found.");
        }
    }

    private static float calculateAveragePrice(float[] prices) {
        if (prices.length == 0) {
            return 0.0f;
        }
        float sum = 0.0f;
        for (float price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    private static void filterItemsBelowPrice(String[] items, float[] prices, float priceThreshold) {
        for (int i = 0; i < items.length; i++) {
            if (prices[i] < priceThreshold) {
                System.out.println(" - " + items[i] + " ($" + String.format("%.2f", prices[i]) + ")");
            }
        }
    }

    private static void isInStock(String[] items, int[] stock) {
        System.out.println("\n------ Current Stock ------");
        System.out.printf("%-15s %10s%n", "Item", "Stock");
        System.out.println("----------------------------");

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (stock[i] > 0) {
                    System.out.printf("%-15s %10d%n", items[i], stock[i]);
                } else {
                    System.out.printf("%-15s %10s%n", items[i], "OUT OF STOCK");
                }
            }
        }
        System.out.println("----------------------------");
    }
}