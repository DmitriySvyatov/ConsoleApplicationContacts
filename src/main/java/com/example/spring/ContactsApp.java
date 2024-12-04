package com.example.spring;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class ContactsApp {
    private final ContactsWorker contactsWorker;
    private final Scanner scanner;

    public ContactsApp(ContactsWorker contactsWorker) {
        this.contactsWorker = contactsWorker;
        this.scanner = new Scanner(System.in);
    }

    public void run() throws IOException {
        final String ADD_COMMAND = "add";
        final String SHOW_COMMAND = "show";
        final String DELETE_COMMAND = "delete";
        final String EXIT_COMMAND = "exit";
        contactsWorker.parsingFile();

        while (true) {

            System.out.println("Введите команду: \n" +
                    "add для добавления нового польщователя\n" +
                    "show для показа всех контактов\n" +
                    "delete для удаления контакта\n" +
                    "exit для выхода из программы\n");
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case (ADD_COMMAND):
                    handleAddContact();
                    break;
                case (SHOW_COMMAND):
                    contactsWorker.showContact();
                    break;
                case (EXIT_COMMAND):
                    /*contactsWorker.initFile();*/
                    contactsWorker.exit();
                    return;
                case (DELETE_COMMAND):
                    handleDeleteContact();
                    break;
                default:
                    System.out.println("Некорректная команда");
            }

        }
    }

    private void handleAddContact() {
        System.out.println("Введите данные контакта в формате : ФИО;номер телефон;email  ");
        String command = scanner.nextLine();
        contactsWorker.addContact(command);
    }

    private void handleDeleteContact() {
        System.out.println("Введите email для удаления контакта");
        String email = scanner.nextLine();
        contactsWorker.removeContactByEmail(email);
    }

}
