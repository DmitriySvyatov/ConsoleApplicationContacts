package com.example.spring;

import com.example.spring.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext contextDefault = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactsWorker contactsWorker = contextDefault.getBean(ContactsWorker.class);//как это ебала роаботает?
        ContactsApp contactsApp=new ContactsApp(contactsWorker);
        contactsApp.run();
    }
}



