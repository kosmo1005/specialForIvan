package practice;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBook {
    HashMap <String,String> phoneBook = new HashMap<>();
    String phone = "";
    String name = "";

   public static boolean inputNameOrNot (String str){
       // регекс, проверяющий является ли ввод именем
       String regexName = "[а-яА-Я]{2,}";
       if(str.matches(regexName)){return true;} else return false;}
    public static boolean inputPhoneOrNot (String str) {
        // регекс, проверяющий является ли ввод номером телефона
        String regexNumber = "[+]?[7,8][-(\s]?[0-9]{3}[-)\s]?[0-9]{3}[-]?[0-9]{2}[-]?[0-9]{2}";
        if(str.matches(regexNumber)){return true;} else return false;}
    public static String cleanPhone (String str){
        // регекс, очищающий номер телефона до дефолта
        String regexClean = "[^0-9]";
        str = str.replaceAll(regexClean,"");
        return str;
    }
    public String typePhoneBook () {
        String type = "";
        return type;
    }
    public boolean checkName (String str){
        if (phoneBook.containsValue(str)){return true;} else return false;}
    public boolean checkNumber (String str){
        if (phoneBook.containsKey(str)){return true;} else return false;}

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента
    }

    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        return "";
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        return new TreeSet<>();
    }

    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
}