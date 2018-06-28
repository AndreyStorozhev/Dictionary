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
    private MyValidator validator;
    private final String charDictionary = "1";
    private final String numberDictionary = "2";

    public Start(){
        dictionary = new DictionaryImpl();
        scanner = new Scanner(System.in);
        properties = new Properties();
        validator = new MyValidator();
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
            if (choice.equals(charDictionary)) {
                dictionary.loadDictionaryFromFile("dictionary.char");
                validator.setKeyMath(properties.getProperty("keyMath.char.regexp"), 4);
                break;
            }else if (choice.equals(numberDictionary)) {
                dictionary.loadDictionaryFromFile("dictionary.number");
                validator.setKeyMath(properties.getProperty("keyMath.number.regexp"), 5);
                break;
            }
        }while (!choice.equals(charDictionary) || !choice.equals(numberDictionary));


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
                    System.out.println(properties.getProperty("remove.entry"));
                    break;
                case "3":
                    System.out.println(properties.getProperty("key.search"));
                    String keySearch = scanner.nextLine();

                    if (keySearch.isEmpty())
                        System.out.println(properties.getProperty("key.search.error"));
                    else
                        System.out.println(dictionary.findForKey(keySearch));

                    break;
                case "4":
                    System.out.println(properties.getProperty("key.add"));
                    String key = scanner.nextLine();
                    String value = scanner.nextLine();

                    if (validator.validateKey(key)){
                        dictionary.addToDictionary(key, value);
                        System.out.println(properties.getProperty("successful.entry"));
                    }else {
                        properties.getProperty("not.valid.key");
                        System.out.println(validator.getError());
                    }
                    break;
            }

        }while (!choice.equals("Exit"));
        System.out.println(properties.getProperty("exit"));
    }
}
