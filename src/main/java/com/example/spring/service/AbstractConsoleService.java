package com.example.spring.service;

import java.util.Scanner;

public abstract class AbstractConsoleService {
    protected ContactServiceImpl contactServiceImpl;
    protected  Scanner scanner;

    public AbstractConsoleService(ContactServiceImpl contactServiceImpl) {
        this.contactServiceImpl = contactServiceImpl;
        this.scanner=new Scanner(System.in);
    }

    protected static final String ADD_COMMAND = "add";
    protected static final String SHOW_COMMAND = "show";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String EXIT_COMMAND = "exit";

    protected void consoleCommand() {
        while (true) {
            System.out.println("Введите команду:\n" +
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
                    contactServiceImpl.showContact();
                    break;
                case (EXIT_COMMAND):
                    contactServiceImpl.exit();
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
        contactServiceImpl.addContact(command);
    }

    private void handleDeleteContact() {
        System.out.println("Введите email для удаления контакта");
        String email = scanner.nextLine();
        contactServiceImpl.removeContactByEmail(email);
    }
}
