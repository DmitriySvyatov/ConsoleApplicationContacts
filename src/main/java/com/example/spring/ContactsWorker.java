package com.example.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.regex.Pattern;


@Component
public class ContactsWorker {
    private final HashMap<String, Contact> contactHashMap = new HashMap<>();
    @Value("${file.path}")
    private String fileName;
    @Value("${spring.profiles.active}")
    private String env;

    public void addContact(String input) {
        String[] lines = input.split(";");
        Contact contact = new Contact(lines[0], lines[1], lines[2]);
        if (isValidContact(contact)) {
            if (!contactHashMap.containsKey(contact.getEmail())) {//обработка существующей записи прописать
                contactHashMap.put(contact.getEmail(), contact);
                System.out.println("Запись добавлена");
            } else {
                System.out.println("Такой контакт существует");
            }
        }
    }

    public void showContact() {
        if (contactHashMap.isEmpty()) {
            System.out.println("Список контактов пуст.");
            return;
        }
        System.out.println("Список контактов:");
        contactHashMap.values().forEach(System.out::println);
    }


    public void removeContactByEmail(String email) {
        if (isEmail(email)) {
            if (contactHashMap.remove(email) != null) {
                contactHashMap.remove(email);
                System.out.println("Контакт успешно удалён.");
            } else {
                System.out.println("Контакт с таким email не найден.");
            }
        }
    }

    public void exit() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            if (contactHashMap.isEmpty()) {
                System.out.println("Нет контактов для добавления в файл");
            }
            for (Contact contact : contactHashMap.values()) {
                writer.write(MessageFormat.format("{0};{1};{2}"
                        , contact.getFullName()
                        , contact.getPhoneNumber()
                        , contact.getEmail()));
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Ошибка при сохранении контактов: " + ex.getMessage());
        }
        System.out.println("Программа завершена");
    }

    public void parsingFile() {
        if (env.equals("init")) {
            try (BufferedReader buffer = Files.newBufferedReader(Paths.get(fileName))) {
                String line;
                while ((line = buffer.readLine()) != null) {
                    String[] arrString = line.split(";");
                    if (arrString.length == 3) {
                        Contact contact = new Contact(arrString[0], arrString[1], arrString[2]);
                        contactHashMap.put(contact.getEmail(), contact);
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка при загрузке контактов: " + e.getMessage());
            }
        }

    }

    public boolean isValidContact(Contact contact) {
        return isFullName(contact.getFullName())
                && isPhoneNumber(contact.getPhoneNumber())
                && isEmail(contact.getEmail());
    }

    public boolean isFullName(String fullName) {
        String fullNameRegex = "^[а-яА-ЯёЁa-zA-Z]+ [а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]*$";
        if (!Pattern.matches(fullNameRegex, fullName)) {
            System.out.println("Введено некорректное имя");
            return false;
        }
        return true;
    }

    public boolean isPhoneNumber(String phoneNumber) {
        String phoneNumberRegex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        if (!Pattern.matches(phoneNumberRegex, phoneNumber)) {
            System.out.println("Введен неккоретный номер телефона");
            return false;
        }
        return true;
    }

    public boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            System.out.println("Введен неверный email");
            return false;
        }
        return true;
    }
}