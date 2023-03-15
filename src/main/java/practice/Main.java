package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        for (;;){
            System.out.println("Введите номер, имя или команду “LIST” для просмотра списка контактов:");
            String firstInput = scanner.nextLine();
            if (firstInput == "LIST"){
                phoneBook.typePhoneBook();
                continue;
            }

            // КОД ПО ВВОДУ ИМЕНИ__________________________________________
            if (PhoneBook.inputNameOrNot(firstInput)){ // если первый ввод - это имя и оно корректно:
                if (phoneBook.checkName(firstInput)){ // если оно уже есть в книге
                    System.out.println("Это имя уже есть в телефонной книге.");
                    System.out.println("Если хотите добавить номер телефона для абонента “" + firstInput +
                            "”, введите “ДОБАВИТЬ”");
                    System.out.println("Если не хотите добавлять, введите “НЕТ”");
                    for (;;) {
                        String command = scanner.nextLine(); // ждём ввода
                        if (command == "ДОБАВИТЬ") {
                            System.out.println("Введите другой номер телефона для абонента “" + firstInput + "”:");
                            for (; ; ) {
                                String secondInput = scanner.nextLine(); // просим ввести номер
                                if (PhoneBook.inputPhoneOrNot(secondInput)) { // если номер корректен:
                                    secondInput = PhoneBook.cleanPhone(secondInput); // приводим номер к дефолтному формату
                                    phoneBook.addContact(secondInput, firstInput); // добавляем в книгу
                                    System.out.println("Контакт сохранен!");
                                    break;
                                    // если номер некорректен:
                                } else {System.out.println("Неверный формат ввода");}
                            }break;
                        // если введена команда "нет" - возвращаемся к началу цикла
                        } else if (command == "НЕТ") {break;}
                                // если ввод не "ДОБАВИТЬ" и не "НЕТ":
                        else System.out.println("Введена неверная команда.");
                    }
                }
                System.out.println("Такого имени в телефонной книге нет.");
                System.out.println("Введите номер телефона для абонента “" + firstInput + "”:");
                for (;;) {
                    String secondInput = scanner.nextLine(); // просим ввести номер
                    if (PhoneBook.inputPhoneOrNot(secondInput)) { // если номер корректен:
                        secondInput = PhoneBook.cleanPhone(secondInput); // приводим номер к дефолтному формату
                        phoneBook.addContact(secondInput, firstInput); // добавляем в книгу
                        System.out.println("Контакт сохранен!");
                        break;
                        // если номер некорректен:
                    } else {System.out.println("Неверный формат ввода");}
                }

            // КОД ПО ВВОДУ НОМЕРА________________________________________________
            } else if (PhoneBook.inputPhoneOrNot(firstInput)){ // если первый ввод номер и он корректен
                if (phoneBook.checkNumber(firstInput)){ // если номер уже есть в книге
                    System.out.println("Этот номер уже есть в телефонной книге");
                    System.out.println("Если хотите перезаписать абонента для номера телефона “" + firstInput +
                            "”, введите “ПЕРЕЗАПИСАТЬ”");
                    System.out.println("Если не хотите перезаписывать, введите “НЕТ”");
                    String command = scanner.nextLine(); // ждём ввода
                    if (command == "ПЕРЕЗАПИСАТЬ"){
                        System.out.println("Введите другое имя абонента для номера “" + firstInput + "”:");
                        String secondInput = scanner.nextLine(); // просим ввести имя
                        if (PhoneBook.inputNameOrNot(secondInput)){ // если имя корректно:
                            phoneBook.addContact(firstInput,secondInput); // перезаписываем имя
                            System.out.println("Контакт сохранен!");
                            // если имя некорректно:
                        } else {System.out.println("Неверный формат ввода");}
                        // если введена команда "нет" - возвращаемся к началу цикла
                    } else if (command == "НЕТ"){
                        continue;
                        // если ввод не "ДОБАВИТЬ" и не "НЕТ":
                    } else System.out.println("Введена неверная команда.");continue;

                }
            } else {System.out.println("Неверный формат ввода");}
        }

    }
}
