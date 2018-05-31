import java.util.Scanner;

public class Main {
    private static Dictionary dictionary;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Выберите словарь с которым хотите работать \n1. Латинский словарь \n2. Цифровой словарь");
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
            System.out.println("\nВыберите действие: \n1 - Вывести всё содержимое словаря \n2 - Удалить запись по ключу \n" +
                    "3 - Найти запись по ключу \n4 - Добавить ключ значние в словарь \nExit - выход из программы");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    dictionary.viewDictionary();
                    break;
                case "2":
                    System.out.println("Введите ключ по которому хотите удалить запись");
                    dictionary.delete(scanner.nextLine());
                    break;
                case "3":

                    System.out.println("Введите ключ для поиска");
                    dictionary.findForKey(scanner.nextLine());
                    break;
                case "4":
                    System.out.println("Введите ключ - значение для добавления в словарь \n");
                    dictionary.addToDictionary(scanner.nextLine(), scanner.nextLine());
                    break;
            }

        }while (!choice.equals("Exit"));
        System.out.println("Программа завершилась");
    }
}
