package example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Start {
    private Scanner scanner;
    private String choice;
    private Dictionary dictionary;
    private Properties properties;

    public Start(){
        scanner = new Scanner(System.in);
        properties = new Properties();
        try {
            properties.load(new FileInputStream("console.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startProgram(){
        do {
            System.out.println(properties.getProperty("choice"));
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                dictionary = new DictionaryImpl();
                break;
            }else if (choice.equals("2")) {
                dictionary = new DictionaryNumber();
                break;
            }
        }while (!choice.equals("1") || !choice.equals("2"));


        do {
            System.out.println(properties.getProperty("menu"));
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    dictionary.viewDictionary();
                    break;
                case "2":
                    System.out.println(properties.getProperty("key.remove"));
                    dictionary.delete(scanner.nextLine());
                    break;
                case "3":

                    System.out.println(properties.getProperty("key.search"));
                    dictionary.findForKey(scanner.nextLine());
                    break;
                case "4":
                    System.out.println(properties.getProperty("key.add"));
                    dictionary.addToDictionary(scanner.nextLine(), scanner.nextLine());
                    break;
            }

        }while (!choice.equals("Exit"));
        System.out.println(properties.getProperty("exit"));
    }
}
