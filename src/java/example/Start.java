package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Scanner;

@Component
public class Start {
    private final MyValidator validator;
    private final Dictionary dictionary;
    private final MessageSource messageSource;

    @Value("1")
    private String charDictionary;
    @Value("2")
    private String numberDictionary;

    private Scanner scanner;
    private String choice;


    @Autowired
    public Start(Dictionary dictionary, MyValidator validator, MessageSource messageSource){
        scanner = new Scanner(System.in);
        this.dictionary = dictionary;
        this.validator = validator;
        this.messageSource = messageSource;
    }


    public void startProgram(){
        do {
            System.out.println(getMessage("choice"));
            choice = scanner.nextLine();
            if (choice.equals(charDictionary)) {
                dictionary.loadDictionaryFromFile("dictionary.char");
                validator.setKeyMath(getMessage("keyMath.char.regexp"), 4);
                break;
            }else if (choice.equals(numberDictionary)) {
                dictionary.loadDictionaryFromFile("dictionary.number");
                validator.setKeyMath(getMessage("keyMath.number.regexp"), 5);
                break;
            }
        }while (!choice.equals(charDictionary) || !choice.equals(numberDictionary));


        do {
            System.out.println(getMessage("menu"));
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    dictionary.viewDictionary();
                    break;
                case "2":
                    System.out.println(getMessage("key.remove"));
                    dictionary.delete(scanner.nextLine());
                    System.out.println(getMessage("remove.entry"));
                    break;
                case "3":
                    System.out.println(getMessage("key.search"));
                    String keySearch = scanner.nextLine();

                    if (keySearch.isEmpty())
                        System.out.println(getMessage("key.search.error"));
                    else
                        System.out.println(dictionary.findForKey(keySearch));

                    break;
                case "4":
                    System.out.println(getMessage("key.add"));
                    String key = scanner.nextLine();
                    String value = scanner.nextLine();

                    if (validator.validateKey(key)){
                        dictionary.addToDictionary(key, value);
                        System.out.println(getMessage("successful.entry"));
                    }else {
                        System.out.println(getMessage("not.valid.key"));
                        System.out.println(validator.getError());
                    }
                    break;
            }

        }while (!choice.equals("Exit"));
        System.out.println(getMessage("exit"));
    }
    private String getMessage(String msg) {
        return messageSource.getMessage(msg, new Object[0], Locale.getDefault());
    }
}
