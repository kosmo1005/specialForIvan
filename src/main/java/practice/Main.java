package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            System.out.println("Введите номер, имя, команду “LIST” для просмотра списка контактов" +
                    " или “SEARCH” дял поиска контакта:");
            String firstInput = scanner.nextLine();
            if (firstInput.equals("LIST")) {
                System.out.println(phoneBook.getAllContacts());
                continue;
            }
            if (firstInput.equals("SEARCH")) {
                System.out.println("Введите имя для поиска контакта:");
                for (;;) {
                    String searchName = scanner.nextLine();
                    if(PhoneBook.inputNameOrNot(searchName)){
                        System.out.println(phoneBook.getContactByName(searchName));
                        break;
                    } else {System.out.println("Неверный формат ввода");}
                }
                continue;
            }

            // КОД ПО ВВОДУ ИМЕНИ__________________________________________
            if (PhoneBook.inputNameOrNot(firstInput)) { // если первый ввод - это имя и оно корректно:
                if (phoneBook.checkName(firstInput)) { // если оно уже есть в книге
                    System.out.println("Это имя уже есть в телефонной книге.");
                    System.out.println("Если хотите добавить номер телефона для абонента “" + firstInput +
                            "”, введите “ДОБАВИТЬ”");
                    System.out.println("Если не хотите добавлять, введите “НЕТ”");
                    for (; ; ) {
                        String command = scanner.nextLine(); // ждём ввода
                        if (command.equals("ДОБАВИТЬ")) {
                            System.out.println("Введите другой номер телефона для абонента “" + firstInput + "”:");
                            for (; ; ) {
                                String secondInput = scanner.nextLine(); // просим ввести номер
                                if (PhoneBook.inputPhoneOrNot(secondInput)) { // если номер корректен:
                                    secondInput = PhoneBook.cleanPhone(secondInput); // приводим номер к формату
                                    // проверяем нет ли в книге такого номера
                                    if (phoneBook.checkNumber(secondInput)) { // если такой номер уже есть
                                        System.out.println("Упс. Этот номер уже занят. " +
                                                "Если хотите перезаписать абонента для номера “" + secondInput +
                                                "“, введите “ПЕРЕЗАПИСАТЬ”");
                                        System.out.println("Если не хотите перезаписывать, введите “НЕТ”");
                                        for (; ; ) {
                                            String command2 = scanner.nextLine(); // ждём ввода
                                            if (command2.equals("ПЕРЕЗАПИСАТЬ")) {
                                                phoneBook.addContact(secondInput, firstInput); // добавляем в книгу
                                                System.out.println("Контакт сохранен!");
                                                break;
                                            } else if (command2.equals("НЕТ")) {
                                                break;
                                            } else {System.out.println("Неверный формат ввода");}
                                        }
                                    } else {phoneBook.addContact(secondInput, firstInput); // добавляем в книгу
                                        System.out.println("Контакт сохранен!");}
                                    break;
                                } else // если номер некорректен:
                                {System.out.println("Неверный формат ввода");}
                            } break;
                            // если введена команда "нет" - возвращаемся к началу цикла
                        } else if (command.equals("НЕТ")) {
                            break;
                        } else // если ввод не "ДОБАВИТЬ" и не "НЕТ":
                        {System.out.println("Введена неверная команда.");}
                    }
                    continue;
                }
                System.out.println("Такого имени в телефонной книге нет.");
                System.out.println("Введите номер телефона для абонента “" + firstInput + "”:");
                for (; ; ) {
                    String secondInput = scanner.nextLine(); // просим ввести номер
                    if (PhoneBook.inputPhoneOrNot(secondInput)) { // если номер корректен:
                        secondInput = PhoneBook.cleanPhone(secondInput); // приводим номер к дефолтному формату
                        // проверяем нет ли в книге такого номера
                        if (phoneBook.checkNumber(secondInput)) { // если такой номер уже есть
                            System.out.println("Упс. Этот номер уже занят. " +
                                    "Если хотите перезаписать абонента для номера “" + secondInput +
                                    "“, введите “ПЕРЕЗАПИСАТЬ”");
                            System.out.println("Если не хотите перезаписывать, введите “НЕТ”");
                            for (; ; ) {
                                String command2 = scanner.nextLine(); // ждём ввода
                                if (command2.equals("ПЕРЕЗАПИСАТЬ")) {
                                    phoneBook.addContact(secondInput, firstInput); // добавляем в книгу
                                    System.out.println("Контакт сохранен!");
                                    break;
                                } else if (command2.equals("НЕТ")) {
                                    break;
                                } else {System.out.println("Неверный формат ввода");}
                            }
                        } else {phoneBook.addContact(secondInput, firstInput); // добавляем в книгу
                            System.out.println("Контакт сохранен!");}
                        break;
                        // если номер некорректен:
                    } else {System.out.println("Неверный формат ввода");}
                }

                // КОД ПО ВВОДУ НОМЕРА________________________________________________
            } else if (PhoneBook.inputPhoneOrNot(firstInput)) { // если первый ввод номер и он корректен
                if (phoneBook.checkNumber(firstInput)) { // если номер уже есть в книге
                    System.out.println("Этот номер уже есть в телефонной книге");
                    System.out.println("Если хотите перезаписать абонента для номера телефона “" + firstInput +
                            "”, введите “ПЕРЕЗАПИСАТЬ”");
                    System.out.println("Если не хотите перезаписывать, введите “НЕТ”");
                    for (; ; ) {
                        String command = scanner.nextLine(); // ждём ввода
                        if (command.equals("ПЕРЕЗАПИСАТЬ")) {
                            System.out.println("Введите другое имя абонента для номера “" + firstInput + "”:");
                            for (; ; ) {
                                String secondInput = scanner.nextLine(); // просим ввести имя
                                if (PhoneBook.inputNameOrNot(secondInput)){ // если имя корректно:
                                    phoneBook.addContact(firstInput, secondInput); // перезаписываем имя
                                    System.out.println("Контакт сохранен!");
                                    break;
                                    // если имя некорректно:
                                } else {System.out.println("Неверный формат ввода");}
                            }
                            break;
                            // если введена команда "нет" - возвращаемся к началу цикла
                        } else if (command.equals("НЕТ")){
                            break;
                            // если ввод не "ДОБАВИТЬ" и не "НЕТ":
                        } else {System.out.println("Введена неверная команда.");}
                    }
                }
            } else {System.out.println("Неверный формат ввода");}
        }
    }
}
