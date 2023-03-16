package practice;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

// проверьте корректность формата имени и телефона
// (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
// если такой номер уже есть в списке, то перезаписать имя абонента
// для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
// это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
public class PhoneBook {
    HashMap <String,String> phoneBook = new HashMap<>();

    public static boolean inputNameOrNot (String str){
        // регекс, проверяющий является ли ввод именем
        String regexName = "[а-яА-Я]{2,}";
        return str.matches(regexName);}
    public static boolean inputPhoneOrNot (String str) {
        // регекс, проверяющий является ли ввод номером телефона
        String regexNumber = "[+]?[7,8][-(\s]?[0-9]{3}[-)\s]?[0-9]{3}[-]?[0-9]{2}[-]?[0-9]{2}";
        return str.matches(regexNumber);}
    public static String cleanPhone (String str){
        // регекс, очищающий номер телефона до дефолта
        String regexClean = "[^0-9]";
        str = str.replaceAll(regexClean,"");
        return str;
    }
    public boolean checkName (String str){
        return phoneBook.containsValue(str);}

    public boolean checkNumber (String str){
        return phoneBook.containsKey(str);}

    public void addContact(String phone, String name) {
        if (PhoneBook.inputPhoneOrNot(phone) & PhoneBook.inputNameOrNot(name)){
            phoneBook.put(phone,name);}}

    public String getContactByPhone(String phone) {
        String value = phoneBook.get(phone); // получение значения
        if (phoneBook.containsKey(phone)){
            return value + " - " + phone;
        } else return "";
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
    }

    public TreeSet<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet<String> contact = new TreeSet<>();
        if (phoneBook.containsValue(name)){
            for (HashMap.Entry<String, String> entry : phoneBook.entrySet()) {
                String key = entry.getKey(); // получение ключа
                String value = entry.getValue(); // получение значения
                if (value.equals(name)){contact.add(value + " - " + key);}
            }
            return contact;
        } else return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        if (!phoneBook.isEmpty()){ // если телефонная книга не пуста:
            TreeSet <String> allContacts = new TreeSet<>();
            HashMap <String,String> nameAggregator = new HashMap<>();

            // переворачиваем ключи и значения в другой HashMap для того, чтобы
            // можно было сконкатинировать номера телефонов в одну строчку
            // если конкатинировать номера телефонов в одну строчку в методе add
            // не будет работать поиск по номеру телефона
            for (HashMap.Entry<String, String> entry : phoneBook.entrySet()) {
                String number = entry.getKey(); // получение ключа
                String name = entry.getValue(); // получение значения
                if (!nameAggregator.containsKey(name)){
                    nameAggregator.put(name,number);
                } else nameAggregator.put(name,(nameAggregator.get(name) + ", " + number));
            }
            // переписываем перевернутый HashMap в TreeSet
            for (HashMap.Entry<String, String> entry : nameAggregator.entrySet()) {
                String name = entry.getKey(); // получение ключа
                String number = entry.getValue(); // получение значения
                allContacts.add(name + " - " + number);
            }
            return allContacts;
        } else return new TreeSet<>();
    }
}