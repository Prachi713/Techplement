package com.example.currencyconverter;
import java.util.*;

public class CurrencyConverterApp {

    private static Map<String, Double> favCurrencies;

    private static void addFavoriteCurrencies(Scanner sc){
        System.out.println("USD");
        String currencyCode = sc.nextLine().toUpperCase();

        if(isValidcurrencyCode(currencyCode)) {
            favCurrencies.put(currencyCode, 0.0);
            System.out.println(currencyCode + " has been added to your favorite currency list.");
        } else {
            System.out.println("Invalid currency code. Enter a valid currency code.");
        }
    }
    private static boolean isValidcurrencyCode(String currencyCode) {
        String[] validCurrencyCodes = {"USD","CHF","EUR","AUD","JPY","CAD"};

        for(String validCode : validCurrencyCodes) {
            if(validCode.equals(currencyCode)) {
                return true;
            }
        }
        return  false;
    }
    private static void veiwFavoriteCurrencies() {
        if(favCurrencies.isEmpty()) {
            System.out.println("You have not added any favorite currencies yet.");
        } else {
            System.out.println("Your Favorite currencies : ");
            for(Map.Entry<String, Double> entry : favCurrencies.entrySet()) {
                String currencyCode = entry.getKey();
                double exchangeRate = entry.getValue();
                System.out.println(currencyCode +" : "+exchangeRate);
            }
        }
    }
    private static void updateFavoriteCurrencies(Scanner sc) {
        if(favCurrencies.isEmpty()) {
            System.out.println("You haven't added any favorite currencies yet.");
            return;
        }
        System.out.println("Select a currency to update : ");
        int index = 1;
        Map<Integer, String> indexToCurrency = new HashMap<>();
        for(String currencyCode : favCurrencies.keySet()){
            System.out.println(index+". "+currencyCode);
            indexToCurrency.put(index, currencyCode);
            index++;
        }
        System.out.println("Enter the id of the currency to update : ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(indexToCurrency.containsKey(choice)) {
            String selectedCurrency = indexToCurrency.get(choice);
            System.out.println("Enter the new exchange rate for "+selectedCurrency+" : ");
            double newRate = sc.nextDouble();
            sc.nextLine();

            favCurrencies.put(selectedCurrency, newRate);
            System.out.println(selectedCurrency+" exchange rate updated to "+ newRate);
        } else {
            System.out.println("Invalid selection. Please enter valid id.");
        }
    }

    private static void showExchangeRates() {
        try {
           Map<String , Double> exchangeRates = CurrencyConverter.getExchangeRates();

            System.out.println("Exchange Rates : ");
            for(Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
                String currencyCode = entry.getKey();
                double rate = entry.getValue();
                System.out.println(currencyCode +" : "+ rate);
            }
        } catch(Exception e) {
            System.out.println(" Filed to fetch exchange rates : "+ e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        favCurrencies = new HashMap<>();

        while(true) {
            System.out.println("Currency Converter Dashboard : ");
            System.out.println("1. Add Favorite Currencies");
            System.out.println("2. view Favorite Currencies");
            System.out.println("3. Update Favorite Currency Rate");
            System.out.println("4. Show Exchange Rates");
            System.out.println("5. Quit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    addFavoriteCurrencies(sc);
                    break;
                case 2:
                    veiwFavoriteCurrencies();
                    break;
                case 3:
                    updateFavoriteCurrencies(sc);
                    break;
                case 4:
                    showExchangeRates();
                    break;
                case 5:
                    System.out.println("Exit...Thank You!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Selection. Try again");
            }
        }
    }
}
